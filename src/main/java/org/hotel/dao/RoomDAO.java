package org.hotel.dao;

import org.hotel.entity.Room;

import javax.inject.Singleton;
import java.util.Date;
import java.util.List;

@Singleton
public class RoomDAO extends BaseDAO {
    public List<Room> findAvailableByDateInterval(Long hotelId, Date from, Date to) {
        String query = "SELECT r " +
                "FROM Room r INNER JOIN r.hotel h " +
                "WHERE h.id=:hotelId AND r NOT IN ( " +
                "SELECT rb " +
                "FROM Booking b INNER JOIN b.room rb " +
                "WHERE (b.fromDate >= :fromDate AND b.toDate <= :toDate) )";

        return this
                .entityManager
                .createQuery(query, Room.class)
                .setParameter("hotelId", hotelId)
                .setParameter("fromDate", from)
                .setParameter("toDate", to)
                .getResultList();
    }
}
