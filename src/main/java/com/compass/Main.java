package com.compass;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex-jpa");
        EntityManager em = emf.createEntityManager();

        System.out.println("Hello world!");
    }
}