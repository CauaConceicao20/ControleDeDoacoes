package com.compass.dao;

import com.compass.entities.Item;
import com.compass.entities.Roupa;
import jakarta.persistence.*;

import java.util.List;


public class ItemDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex-jpa");
    EntityManager em = emf.createEntityManager();

    public void addItemBd(Item item) {
        em.getTransaction().begin();
        em.persist(item);
        em.flush();
        em.getTransaction().commit();
    }

    public List<Item> retornaItemBd() {
        String hql = "SELECT e FROM Item e";
        TypedQuery<Item> query = em.createQuery(hql, Item.class);
        return query.getResultList();
    }
}
