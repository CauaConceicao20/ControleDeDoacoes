package com.compass.dao;

import com.compass.entities.Abrigo;
import com.compass.entities.Item;
import jakarta.persistence.*;
import org.apache.commons.lang3.ObjectUtils;

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
            System.out.println("Ocorreu um erro ao tentar adicionar o abrigo" + e.getMessage());
        }
    }

    public List<Abrigo> buscaTodos() {
            String hql = "SELECT e FROM Abrigo e";
            TypedQuery<Abrigo> query = em.createQuery(hql, Abrigo.class);
            return query.getResultList();

    }

    public Abrigo buscaPorId(Long id) {
        try {
            return em.find(Abrigo.class, id);
        }catch (Exception e){
            System.out.println("Ocorreu um erro ao buscar abrigos" + e.getMessage());
            return null;
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
            System.out.println("Ocorreu um erro ao tentar alterar dados do abrigo" + e.getMessage());
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
            System.out.println("Ocorreu um erro ao tentar remover o abrigo" + e.getMessage());
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
