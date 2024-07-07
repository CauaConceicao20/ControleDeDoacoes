package com.compass.dao;

import com.compass.entities.Distribuidora;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Set;

public class DistribuidoraDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex-jpa");
    EntityManager em = emf.createEntityManager();

    public void addDistribuidoraBd(Distribuidora distribuidora) {
        em.getTransaction().begin();
        em.persist(distribuidora);
        em.getTransaction().commit();
    }

    public List<Distribuidora> listaDistribuidorasBd() {
        Query query = em.createQuery("SELECT e FROM Distribuidora e");
        return query.getResultList();
    }


}
