package com.compass.util.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private ValidadorMenu validadorMenu = new ValidadorMenu();

    public void exibirMenuPrincipal() {
        System.out.println("<-----MENU----->");
        System.out.println("1- Operações Item");
        System.out.println("2- Operações Distribuidoras");
        System.out.println("3- Operações Abrigo");
        System.out.println("4- Operações Pedido");
        System.out.println("5- Operações Pessoa");
        System.out.println("6- Finalizar Programa");
    }

    public void exibirMenuItem() {
        System.out.println("<-----Items----->");
        System.out.println("1- Fazer Doação");
        System.out.println("2- Listar Items");
        System.out.println("3- Alterar Item");
        System.out.println("4- Remover Item");
        System.out.println("5- Voltar ao Menu");

    }

    public void exibirMenuDistribuidora() {
        System.out.println("<-----Distribuidora----->");

        System.out.println("1- Cadastrar Distribuidora");
        System.out.println("2- Listar Distribuidora");
        System.out.println("3- Alterar Distribuidora");
        System.out.println("4- Remover Distribuidora");
        System.out.println("5- Voltar ao Menu");
    }

    public void exibirMenuAbrigo() {
        System.out.println("<-----Abrigo----->");

        System.out.println("1- Cadastrar Abrigo");
        System.out.println("2- Listar Abrigos");
        System.out.println("3- Alterar Abrigo");
        System.out.println("4- Remover Abrigo");
        System.out.println("5- Voltar ao Menu");
    }

    public void exibirMenuPedido() {
        System.out.println("<-----Pedidos----->");

        System.out.println("1- Fazer Pedidos");
        System.out.println("2- Listar Pedidos");
        System.out.println("3- Alterar Pedido");
        System.out.println("4- Remover Pedido");
        System.out.println("5- Voltar ao Menu");
    }

    public void exibirMenuPessoa() {
        System.out.println("<-----Pedidos----->");

        System.out.println("1- Cadastrar Pessoa");
        System.out.println("2- Listar Pessoas");
        System.out.println("3- Alterar Pessoa");
        System.out.println("4- Remover Pessoa");
        System.out.println("5- Voltar ao Menu");
    }

    public int lerOpcao(int min, int max) {
        int opcao = -1;
        while (opcao == -1) {
            Scanner teclado = new Scanner(System.in);
            try {
                opcao = teclado.nextInt();
                teclado.nextLine();
                validadorMenu.validaEntrada(opcao, min, max);
            } catch (InputMismatchException e) {
                System.out.println("Opção Inválida! Digite novamente.");
                opcao = -1;
                break;

            }
        }
        return opcao;
    }

    public int chamarSubMenu(int opcao, Menu menu) {
            switch (opcao) {
                case 1:
                    menu.exibirMenuItem();
                    break;
                case 2:
                    menu.exibirMenuDistribuidora();
                    break;
                case 3:
                    menu.exibirMenuAbrigo();
                    break;
                case 4:
                    menu.exibirMenuPedido();
                    break;
                case 5:
                    menu.exibirMenuPessoa();
                    break;
                default:
                    break;
            }
            return opcao;
    }
}
