package com.compass.dao;

import com.compass.entities.Distribuidora;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

public class DistribuidoraDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex-jpa");
    EntityManager em = emf.createEntityManager();

    public void adiciona(Distribuidora distribuidora) {
        try {
            em.getTransaction().begin();
            em.persist(distribuidora);
            em.getTransaction().commit();
        }catch(Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally {
            if(distribuidora == null) {
                close();
            }
        }
    }

    public List<Distribuidora> buscaTodos() {
        try {
            String hql = "SELECT e FROM Distribuidora e";
            TypedQuery<Distribuidora> query = em.createQuery(hql, Distribuidora.class);
            return query.getResultList();
        }finally {
            close();
        }
    }

    public Distribuidora buscaPorId(Long id) {
        try {
            return em.find(Distribuidora.class, id);
        }finally {
            close();
        }
    }

    public void alterar(Distribuidora distribuidora) {
        try{
            em.getTransaction().begin();
            em.merge(distribuidora);
            em.getTransaction().commit();
        }catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally {
            close();
        }
    }

    public void remove(Long id){
        try {
            em.getTransaction().begin();
            Distribuidora distribuidora = buscaPorId(id);
            if (distribuidora != null) {
                em.remove(distribuidora);
            }
            em.getTransaction().commit();
        }catch(Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally {
            close();
        }
    }

    public void close() {
        if(em != null) {
            em.close();
        }
        if(emf != null) {
            emf.close();
        }
    }

}
