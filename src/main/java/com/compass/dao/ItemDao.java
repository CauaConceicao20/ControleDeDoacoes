package com.compass.dao;

import com.compass.entities.*;
import com.compass.enums.Genero;
import com.compass.enums.TamanhoRoupa;
import com.compass.enums.UnidadeDeMedida;
import jakarta.persistence.*;
import org.apache.commons.collections.functors.ExceptionPredicate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.module.Configuration;
import java.util.List;


public class ItemDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex-jpa");
    private EntityManager em = emf.createEntityManager();


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
        }
    }

    public List<Item> buscaTodos() {
            String hql = "SELECT e FROM Item e";
            TypedQuery<Item> query = em.createQuery(hql, Item.class);
            return query.getResultList();

    }

    public Item buscaPorId(Long id) {
            return em.find(Item.class, id);

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
        }
    }

    public List<Roupa> buscaPersonalizadaDeRoupa(String descricao, TamanhoRoupa tamanho, Genero genero) {
        List<Roupa> roupas = null;

        try {
            em.getTransaction().begin();
            String hql = "FROM Roupa  WHERE descricao = :descricao AND tamanho = :tamanho AND genero = :genero";
            TypedQuery<Roupa> query = em.createQuery(hql, Roupa.class);
            query.setParameter("descricao", descricao);
            query.setParameter("tamanho", tamanho);
            query.setParameter("genero", genero);
            roupas = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }

        return roupas;
    }

    public List<ProdutoHigiene> buscaPersonalizadaDeProdutoHigiene(String descricao) {
        List<ProdutoHigiene> produtoHigienes = null;
        try{
            em.getTransaction().begin();
            String hql = "FROM ProdutoHigiene WHERE descricao = :descricao";
            TypedQuery<ProdutoHigiene> query = em.createQuery(hql, ProdutoHigiene.class);
            query.setParameter("descricao",descricao);
            produtoHigienes = query.getResultList();
            em.getTransaction().commit();
        }catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        e.printStackTrace();
        }
        return produtoHigienes;
    }

    public List<Alimento> buscaPersonalizadaDeAlimentos(String descricao, UnidadeDeMedida unidadeDeMedida, int quantidade) {
        List<Alimento> alimentos = null;
        try{
            em.getTransaction().begin();
            String hql = "FROM Alimento WHERE descricao = :descricao AND quantidade = :quantidade AND unidadeDeMedida = :unidadeDeMedida";
            TypedQuery<Alimento> query = em.createQuery(hql, Alimento.class);
            query.setParameter("descricao", descricao);
            query.setParameter("quantidade", quantidade);
            query.setParameter("unidadeDeMedida", unidadeDeMedida);
            alimentos = query.getResultList();
            em.getTransaction().commit();
        }catch(Exception e){
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return alimentos;
    }

    public List<Roupa> buscaPersonalizadaDeAlimento(String descricao, int quantidade, UnidadeDeMedida unidadeDeMedida) {
        List<Roupa> roupas = null;

        try {
            em.getTransaction().begin();
            String hql = "FROM Roupa  WHERE descricao = :descricao AND quantidade = :quantidade AND unidadeDeMedida = :unidadeDeMedida";
            TypedQuery<Roupa> query = em.createQuery(hql, Roupa.class);
            query.setParameter("descricao", descricao);
            query.setParameter("quantidade", quantidade);
            query.setParameter("unidadeDeMedida", unidadeDeMedida);
            roupas = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }

        return roupas;
    }

    public List<ProdutoHigiene> buscaPersonalizadaDeRoupa(String descricao) {
        List<ProdutoHigiene> ProdutosHigiene = null;
        try {
            em.getTransaction().begin();
            String hql = "FROM Roupa  WHERE descricao = :descricao";
            TypedQuery<ProdutoHigiene> query = em.createQuery(hql, ProdutoHigiene.class);
            query.setParameter("descricao", descricao);
            ProdutosHigiene = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }

        return ProdutosHigiene;
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
