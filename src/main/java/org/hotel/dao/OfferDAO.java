package org.hotel.dao;

import com.mysql.cj.xdevapi.SessionFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hotel.entity.Agency;
import org.hotel.entity.Hotel;
import org.hotel.entity.Offer;
import org.hotel.entity.Room;
import org.hotel.enums.OfferState;
import org.hotel.grpc.OfferItem;
import org.hotel.grpc.RoomItem;
import org.hotel.utils.DateParser;
import org.hotel.utils.Rounder;

import javax.inject.Singleton;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Singleton
public class OfferDAO extends BaseDAO {
    public RoomDAO roomDAO = new RoomDAO();
    public AgencyDAO agencyDAO = new AgencyDAO();

    @Transactional
    public List<OfferItem> findAvailableOffers(Long hotelId, Long agencyId, int peopleCount, Date fromDate, Date toDate) throws Exception {

        List<Room> availableRooms = this.roomDAO.findAvailableByDateInterval(hotelId, fromDate, toDate);

        long nightCount = ChronoUnit
                .DAYS
                .between(DateParser.toLocalDate(fromDate), DateParser.toLocalDate(toDate));

        int availableBedCount = availableRooms
                .stream()
                .reduce(0, (subtotal, element) -> subtotal + element.getBedCount(), Integer::sum);

        if (availableRooms.isEmpty())
            throw new Exception("Aucune offre ne correspond à ces critères.");

        if (availableBedCount < peopleCount)
            throw new Exception("Aucune offre ne correspond à ces critères (Pas assez de place disponible)");

        Set<Offer> offers = new LinkedHashSet<>();
        Set<Room> bindedRooms = new LinkedHashSet<>();
        List<OfferItem> offerItems = new ArrayList<>();
        List<RoomItem> currentRoomItems;

        final Agency agency = this.agencyDAO.findById(agencyId);
        EntityTransaction transaction = this.entityManager.getTransaction();

        transaction.begin();

        int bedCount = 0;
        for (Room room : availableRooms) {

            bedCount += room.getBedCount();
            bindedRooms.add(room);

            if (bedCount >= peopleCount) {

                Offer offer = new Offer();
                offer.setFromDate(fromDate);
                offer.setToDate(toDate);
                offer.setLodgedPeopleCount(peopleCount);
                offer.setState(OfferState.PENDING);
                offer.setAgency(agency);
                offer.setRooms(bindedRooms);

                this.entityManager.persist(offer);

                offers.add(offer);

                bindedRooms = new LinkedHashSet<>();
                bedCount = 0;
            }
        }

        this.entityManager.flush();

        transaction.commit();

        for (Offer offer : offers) {
            currentRoomItems = new ArrayList<>();

            for (Room room : offer.getRooms()) {

                currentRoomItems.add(RoomItem
                        .newBuilder()
                        .setPrice(room.getPrice())
                        .setSuperficie(room.getArea())
                        .setBedCount(room.getBedCount())
                        .setImage(room.getImage()).build());
            }

            final Double globalPrice = currentRoomItems
                    .stream()
                    .reduce(0.0, (accumulator, current) -> accumulator + (current.getPrice() * nightCount), Double::sum);

            final Integer bed = currentRoomItems
                    .stream()
                    .reduce(0, (accumulator, current) -> accumulator + (current.getBedCount()), Integer::sum);

            OfferItem offerItem = OfferItem.newBuilder()
                    .setOfferId(offer.getId())
                    .setPrice(Rounder.round(globalPrice, 2))
                    .setBedCount(bed)
                    .setFromDate((new SimpleDateFormat("yyyy-MM-dd")).format(fromDate))
                    .setToDate((new SimpleDateFormat("yyyy-MM-dd")).format(toDate))
                    .addAllRooms(currentRoomItems)
                    .build();

            offerItems.add(offerItem);
        }

        return offerItems;

    }

    public boolean exists(Long id, Long agencyId) {
        return !this
                .entityManager
                .createQuery("SELECT o FROM Offer o WHERE o.id=:id AND o.agency.id = :agencyId", Offer.class)
                .setParameter("id", id)
                .setParameter("agencyId", agencyId)
                .getResultList()
                .isEmpty();
    }

    public Offer findById(Long id) {
        return this.entityManager.find(Offer.class, id);
    }
}
