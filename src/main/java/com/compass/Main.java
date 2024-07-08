package com.compass;

import com.compass.dao.ItemDao;
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

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws ParseException {

        Scanner teclado = new Scanner(System.in);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        CsvReader csvReader = new CsvReader();
        DistribuidoraService distribuidoraService = new DistribuidoraService();
        ItemService itemService = new ItemService();
        AbrigoService abrigoService = new AbrigoService();


        distribuidoraService.adicionaDistribuidorasCsv(csvReader.lerDadosDeDistribuidora());
        itemService.addItemsCsv(csvReader.lerDadosDeItems(distribuidoraService.listaDistribuidorasCsv()));

        List<Item> list = itemService.retornaItems();

        for(Item item : list) {
            if(item.getClass().getSimpleName().equals("Roupa")) {
                System.out.println("É do tipo roupa: " + item.getClass().getSimpleName());
            }else {
                System.out.println("Não é do tipo roupa: " + item.getClass().getSimpleName());
            }
        }

        System.out.println("Hello world!");

        System.out.println("Fazendo pergunta");
        String text = teclado.nextLine();

    }
}