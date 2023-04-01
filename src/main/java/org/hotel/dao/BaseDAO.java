package org.hotel.dao;

import org.hibernate.SessionFactory;

import jakarta.persistence.*;


public class BaseDAO {
    protected EntityManager entityManager;

    public BaseDAO() {
        this.entityManager = Persistence
                .createEntityManagerFactory("booking-system")
                .createEntityManager();
    }
}
