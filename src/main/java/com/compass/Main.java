package com.compass;

import com.compass.util.menu.ValidadorMenu;
import com.compass.util.menu.Menu;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws ParseException {


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
/*
        CsvReader csvReader = new CsvReader();
        DistribuidoraService distribuidoraService = new DistribuidoraService();
        ItemService itemService = new ItemService();
        AbrigoService abrigoService = new AbrigoService();


        distribuidoraService.adicionaDistribuidorasCsv(csvReader.lerDadosDeDistribuidora());
        itemService.adicionaItemsCsv(csvReader.lerDadosDeItems(distribuidoraService.retornaDistribuidoras()));


 */

        Menu menu = new Menu();
        int opcaoMenu = 0;
        int opcaoSubMenu = 0;


        while (true) {
            menu.exibirMenuPrincipal();
            opcaoMenu = menu.lerOpcao(0, 6);

            if (opcaoMenu > 0 && opcaoMenu <= 5) {
                while (true) {
                    int subMenu = menu.chamarSubMenu(opcaoMenu, menu);
                    opcaoSubMenu = menu.lerOpcao(0, 5);

                    if (opcaoSubMenu == 5) {
                        break;
                    }

                    switch (subMenu) {
                        case 1:
                            switch (opcaoSubMenu) {
                                case 1:
                                    System.out.println("Inicia cadastro de item");
                                    break;
                                case 2:
                                    System.out.println("Lista de items");
                                    break;
                                case 3:
                                    System.out.println("Altera item");
                                    break;
                                case 4:
                                    System.out.println("Remove item");
                                    break;
                            }
                            break;
                        case 2:
                            switch (opcaoSubMenu) {
                                case 1:
                                    System.out.println("Cadastro de distribuidoras");
                                    break;
                                case 2:
                                    System.out.println("Lista de distribuidoras");
                                    break;
                                case 3:
                                    System.out.println("Altera distribuidora");
                                    break;
                                case 4:
                                    System.out.println("Remove distribuidora");
                                    break;
                            }
                            break;
                        case 3:
                            switch (opcaoSubMenu) {
                                case 1:
                                    System.out.println("Cadastro de abrigos");
                                    break;
                                case 2:
                                    System.out.println("Lista abrigos");
                                    break;
                                case 3:
                                    System.out.println("Altera abrigo");
                                    break;
                                case 4:
                                    System.out.println("Remove abrigo");
                                    break;
                            }
                            break;
                        case 4:
                            switch (opcaoSubMenu) {
                                case 1:
                                    System.out.println("fazer de Pedido");
                                    break;
                                case 2:
                                    System.out.println("Listar pedidos");
                                    break;
                                case 3:
                                    System.out.println("Altera pedido");
                                    break;
                                case 4:
                                    System.out.println("Remove pedido");
                                    break;
                            }
                            break;
                        case 5:
                            switch (opcaoSubMenu) {
                                case 1:
                                    System.out.println("Cadastra pessoa");
                                    break;
                                case 2:
                                    System.out.println("Listar pessoas");
                                    break;
                                case 3:
                                    System.out.println("Altera pessoa");
                                    break;
                                case 4:
                                    System.out.println("Remove pessoa");
                                    break;
                            }
                            break;
                    }
                }
            } else if (opcaoMenu == 6) {
                System.out.println("Programa finalizado");
                break;
            }
        }
    }
}


      /*
        Roupa roupa = new Roupa(null, TipoItem.ROUPA, "Camisa", Genero.M, TamanhoRoupa.P, distribuidoraService.buscaDistribuidoraPorId(1));
        try {
            itemService.adiciona(roupa);
        }catch (LimiteAlcancadoException | IllegalArgumentException e) {
             System.out.println(e.getMessage());
        }

        System.out.println("Hello world!");
         */