package org.hotel.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hotel.entity.*;
import org.hotel.enums.OfferState;
import org.hotel.grpc.BookingRequest;
import org.hotel.key.BookingKey;

import jakarta.persistence.*;

import javax.inject.Singleton;
import java.text.SimpleDateFormat;
import java.util.*;

@Singleton
public class BookingDAO extends BaseDAO {
    @Transactional
    public Map<String, String> createFromOffer(Offer offer, BookingRequest.Client rawClient) {

        String ref = UUID.randomUUID().toString().replaceAll("-", "");

        Set<Room> concernedRooms = offer.getRooms();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        BookingRequest.Client.PaymentInformation rawPaymentInfo = rawClient.getPaymentInformation();

        PaymentInformation paymentInformation = new PaymentInformation();

        paymentInformation.setCardNumber(rawPaymentInfo.getCardNumber());
        paymentInformation.setCvv(rawPaymentInfo.getCvv());

        this.entityManager.persist(paymentInformation);

        Client client = new Client();

        client.setPaymentInformation(paymentInformation);
        client.setFirstName(rawClient.getFirstName());
        client.setLastName(rawClient.getLastName());

        this.entityManager.persist(client);


        for (Room room : concernedRooms) {
            Booking booking = new Booking();
            BookingKey bookingKey = new BookingKey();

            bookingKey.setClientId(client.getId());
            bookingKey.setRoomId(room.getId());

            booking.setId(bookingKey);
            booking.setRef(ref);
            booking.setRoom(room);
            booking.setClient(client);
            booking.setFromDate(offer.getFromDate());
            booking.setToDate(offer.getToDate());

            this.entityManager.merge(booking);
        }

        offer.setState(OfferState.CONFIRMED);

        this.entityManager.flush();

        transaction.commit();

        Map<String, String> result = new LinkedHashMap<>();

        result.put("reference", ref);
        result.put("clientFullname", String.format("%s %s", client.getFirstName(), client.getLastName()));
        result.put("fromDate", (new SimpleDateFormat("yyyy-MM-dd")).format(offer.getFromDate()));
        result.put("toDate", (new SimpleDateFormat("yyyy-MM-dd")).format(offer.getToDate()));

        return result;
    }
}
