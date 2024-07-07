package com.compass;

import com.compass.entities.Abrigo;
import com.compass.entities.Endereco;
import com.compass.entities.Pessoa;
import com.compass.service.AbrigoService;
import com.compass.service.DistribuidoraService;
import com.compass.service.ItemService;
import com.compass.util.CsvReader;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
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

        System.out.println("Hello world!");


    }
}