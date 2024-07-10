package com.compass.dao;

import com.compass.entities.Distribuidora;
import com.compass.entities.Item;
import com.compass.entities.Roupa;
import jakarta.persistence.*;

import java.util.List;


public class ItemDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex-jpa");
    EntityManager em = emf.createEntityManager();

    public void adiciona(Item item) {
        try {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        }catch(Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Ocorreu um erro ao tentar adicionar o item" + e.getMessage());
        }finally {
            if(item == null) {
                close();
            }
        }
    }

    public List<Item> buscaTodos() {
        try {
            String hql = "SELECT e FROM Item e";
            TypedQuery<Item> query = em.createQuery(hql, Item.class);
            return query.getResultList();
        }finally {
            close();
        }
    }

    public Item buscaPorId(Long id) {
        try {
            return em.find(Item.class, id);
        }finally {
            close();
        }
    }

    public void alterar(Item item) {
        try{
            em.getTransaction().begin();
            em.merge(item);
            em.getTransaction().commit();
        }catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Ocorreu um erro ao tentar alterar dados do item" + e.getMessage());
        }finally {
            close();
        }
    }

    public void remove(Long id) {
        try {
            em.getTransaction().begin();
            Item item = buscaPorId(id);
            if (item != null) {
                em.remove(item);
            }
            em.getTransaction().commit();
        }catch(Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Ocorreu um erro ao tentar remover o item" + e.getMessage());
        }finally {
            close();
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
