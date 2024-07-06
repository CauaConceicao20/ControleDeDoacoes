package com.compass.dao;

import com.compass.entities.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class ItemDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex-jpa");
    EntityManager em = emf.createEntityManager();

    public void addItem(Item item) {
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
    }
}
