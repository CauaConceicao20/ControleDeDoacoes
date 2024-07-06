package com.compass;

import com.compass.service.DistribuidoraService;
import com.compass.service.ItemService;
import com.compass.util.CsvReader;


public class Main {
    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader();
        DistribuidoraService distribuidoraService = new DistribuidoraService();
        ItemService itemService = new ItemService();

        distribuidoraService.adicionaDistribuidorasCsv(csvReader.lerDadosDeDistribuidora());

        itemService.addItemsCsv(csvReader.lerDadosDeItems(distribuidoraService.listaDistribuidorasCsv()));



        System.out.println("Hello world!");


    }
}