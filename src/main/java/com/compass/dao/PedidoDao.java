package com.compass.dao;

import com.compass.entities.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PedidoDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex-jpa");
    private EntityManager em = emf.createEntityManager();

    public void adiciona(Pedido pedido) {
        em.getTransaction().begin();
        em.persist(pedido);
        em.getTransaction().commit();
    }

    public List<Pedido> buscaTodos() {
        String hql = "SELECT e FROM Pedido e";
        TypedQuery<Pedido> query = em.createQuery(hql, Pedido.class);
        return query.getResultList();
    }

    public Pedido buscaPorId(long id) {
        return em.find(Pedido.class, id);
    }

    public void alterar(Pedido pedido) {
        em.getTransaction().begin();
        em.merge(pedido);
        em.getTransaction().commit();
    }

 }
