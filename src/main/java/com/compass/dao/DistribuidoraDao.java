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
            System.out.println("Ocorreu um erro ao tentar adicionar a distribuidora" + e.getMessage());
        }
    }

    public List<Distribuidora> buscaTodos() {
            String hql = "SELECT e FROM Distribuidora e";
            TypedQuery<Distribuidora> query = em.createQuery(hql, Distribuidora.class);
            return query.getResultList();

    }

    public Distribuidora buscaPorId(Long id) {
            return em.find(Distribuidora.class, id);

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
            System.out.println("Ocorreu um erro ao tentar alterar dados da distribuidora" + e.getMessage());
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
            System.out.println("Ocorreu um erro ao tentar remover a distribuidora" + e.getMessage());
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
