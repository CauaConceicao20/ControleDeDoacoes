package com.compass.dao;

import com.compass.entities.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PedidoDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex-jpa");
    private EntityManager em = emf.createEntityManager();

    public void adiciona(Pedido pedido) {
        em.getTransaction().begin();
        em.persist(pedido);
        em.getTransaction().commit();
    }

}
