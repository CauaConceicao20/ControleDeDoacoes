package com.compass.dao;

import com.compass.entities.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PessoaDao {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex-jpa");
    private EntityManager em = emf.createEntityManager();

    public void addPessoa(Pessoa pessoa) {
        em.getTransaction().begin();
        em.persist(pessoa);
        em.getTransaction().commit();
    }


}
