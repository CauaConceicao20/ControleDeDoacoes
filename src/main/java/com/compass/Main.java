package com.compass;

import com.compass.entities.*;
import com.compass.enums.Genero;
import com.compass.enums.PedidoStatus;
import com.compass.enums.TamanhoRoupa;
import com.compass.service.AbrigoService;
import com.compass.service.DistribuidoraService;
import com.compass.service.ItemService;
import com.compass.util.CsvReader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        CsvReader csvReader = new CsvReader();
        DistribuidoraService distribuidoraService = new DistribuidoraService();
        ItemService itemService = new ItemService();
        AbrigoService abrigoService = new AbrigoService();

        distribuidoraService.adicionaDistribuidorasCsv(csvReader.lerDadosDeDistribuidora());
        itemService.addItemsCsv(csvReader.lerDadosDeItems(distribuidoraService.listaDistribuidorasCsv()));

        Endereco endereco = new Endereco();
        Pessoa pessoa = new Pessoa(null, "João", "222.333.555.15");
        Pessoa pessoa1 = new Pessoa(null, "Pedro", "822.333.777-14");
        Abrigo abrigo1 = new Abrigo(null  ,"Abrigo1", endereco, pessoa, "(71) 8244-6140", "abrigo1@hotmail.com", 500);
        Abrigo abrigo2 = new Abrigo(null  ,"Abrigo2", endereco, pessoa1, "(24) 7198=2310", "abrigo2@gmail.com", 600);

        abrigoService.addAbrigo(abrigo1);
        abrigoService.addAbrigo(abrigo2);




        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex-jpa");
        EntityManager em = emf.createEntityManager();

        Distribuidora distribuidora = new Distribuidora(null, "JJJJJJJFM", endereco);


        Pedido pedido = new Pedido(null, Instant.now(), PedidoStatus.EM_ABERTO);


        Roupa roupa = new Roupa(null, "Roupa", "agasalho", Genero.M, TamanhoRoupa.GG, distribuidora);
        Alimento alimento = new Alimento(null, "Alimento", "Arroz", 2, "KG", dateFormat.parse("2024-06-21"), distribuidora);
        ProdutoHigiene produtoHigiene = new ProdutoHigiene(null, "ProdutoHigiene", "Escova de Dentes", distribuidora);



        //Alimento alimento2 = new Alimento(null, "Alimento2", "Feijão", 2, "KG", dateFormat.parse("2025-06-30"), distribuidora);

        em.getTransaction().begin();
        em.persist(pedido);
        em.persist(distribuidora);
        em.persist(roupa);
        //em.persist(produtoHigiene);
        //em.persist(alimento);
        PedidoItem pedidoItem = new PedidoItem(pedido, roupa.getId(), 1);
        em.persist(pedidoItem);
        em.getTransaction().commit();

        System.out.println("Hello world!");

    }
}