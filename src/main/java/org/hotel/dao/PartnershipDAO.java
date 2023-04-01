package org.hotel.dao;

import org.hotel.entity.Partnership;

import javax.inject.Singleton;
import javax.management.Query;
import java.util.List;

@Singleton
public class PartnershipDAO extends BaseDAO {

    public boolean arePartners(Long agencyId, Long hotelId) {
        List partnerships =  this
                .entityManager
                .createQuery("SELECT p FROM Partnership p WHERE p.agency.id = :agencyId AND p.hotel.id = :hotelId")
                .setParameter("agencyId", agencyId)
                .setParameter("hotelId", hotelId)
                .getResultList();

        return !(partnerships.isEmpty());
    }
}
