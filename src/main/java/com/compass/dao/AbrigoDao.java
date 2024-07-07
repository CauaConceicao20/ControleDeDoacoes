package com.compass.dao;

import com.compass.entities.Abrigo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AbrigoDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex-jpa");
    EntityManager em = emf.createEntityManager();

    public void addAbrigoBd(Abrigo abrigo) {
        em.getTransaction().begin();
        em.persist(abrigo);
        em.getTransaction().commit();
    }

}
