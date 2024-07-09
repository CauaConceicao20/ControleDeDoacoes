package com.compass.dao;

import com.compass.entities.Abrigo;
import com.compass.entities.Item;
import jakarta.persistence.*;

import java.util.List;

public class AbrigoDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex-jpa");
    EntityManager em = emf.createEntityManager();

    public void addAbrigoBd(Abrigo abrigo) {
        try {
            em.getTransaction().begin();
            em.persist(abrigo);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public List<Abrigo> buscaTodos() {
        try {
            String hql = "SELECT e FROM Abrigo e";
            TypedQuery<Abrigo> query = em.createQuery(hql, Abrigo.class);
            return query.getResultList();
        } finally {
            close();
        }
    }

    public Abrigo buscaPorId(Long id) {
        try {
            return em.find(Abrigo.class, id);
        }finally {
            close();
        }
    }

    public void altera(Abrigo abrigo) {
        try {
            em.getTransaction().begin();
            em.merge(abrigo);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally {
            close();
        }
    }

    public void remove(Long id) {
        try {
            em.getTransaction().begin();
            Abrigo abrigo = buscaPorId(id);
            if (abrigo != null) {
                em.remove(abrigo);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void close() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }
}
