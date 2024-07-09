package com.compass.dao;

import com.compass.entities.Item;
import com.compass.entities.Roupa;
import jakarta.persistence.*;

import java.util.List;


public class ItemDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex-jpa");
    EntityManager em = emf.createEntityManager();

    public void adiciona(Item item) {
        em.getTransaction().begin();
        em.persist(item);
        em.flush();
        em.getTransaction().commit();
    }

    public List<Item> buscaTodos() {
        String hql = "SELECT e FROM Item e";
        TypedQuery<Item> query = em.createQuery(hql, Item.class);
        return query.getResultList();
    }

    public Item buscaPorId(Long id) {
        return em.find(Item.class, id);
    }

    public void alterar(Long id, String descricao) {
        Item item = buscaPorId(id);
        item.setDescricao(descricao);
        adiciona(item);
    }

    public void remove(Long id) {
        Item item = buscaPorId(id);
        if(item != null) {
            em.getTransaction().begin();
            em.remove(item);
            em.getTransaction().commit();
        }
    }
}
