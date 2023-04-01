package org.hotel.dao;

import org.hotel.entity.Agency;
import org.hotel.entity.Hotel;

import javax.inject.Singleton;
import java.util.List;
import java.util.NoSuchElementException;

@Singleton
public class AgencyDAO extends BaseDAO {
    public boolean isPasswordValid(Long agencyId, String agencyPassword) {
        List<Agency> agencies = this
                .entityManager
                .createQuery("SELECT a FROM Agency a WHERE a.id = :id AND a.password = :password", Agency.class)
                .setParameter("id", agencyId)
                .setParameter("password", agencyPassword)
                .getResultList();

        return !(agencies.isEmpty());
    }

    public boolean isPasswordValid(Long agencyId, String agencyLogin, String agencyPassword) {
        return !this
                .entityManager
                .createQuery("SELECT a FROM Agency a WHERE a.id = :id AND a.password = :password AND a.login = :login", Agency.class)
                .setParameter("id", agencyId)
                .setParameter("password", agencyPassword)
                .setParameter("login", agencyLogin)
                .getResultList()
                .isEmpty();
    }

    public Agency findById(Long id) {

        Agency result = this.entityManager.find(Agency.class, id);


        if (result == null)
            throw new NoSuchElementException("NO DATA FOUND WITH THE ID ");


        return result;
    }
}
