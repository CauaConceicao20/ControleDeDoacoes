package com.compass.util.menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorMenu {

    public void validaEntrada(int digito, int limiteMin, int limiteMax) {
        if(digito <= limiteMin || digito > limiteMax) {
            System.out.println("Opção Invalida! Digite Novamente");
        }
    }

    public int validaEntrada(int max) {
        Scanner teclado = new Scanner(System.in);
        int opcao = teclado.nextInt();
        teclado.nextLine();
      if(opcao <= 0 || opcao > max) {
          System.out.println("Digito invalido");
      }
      return opcao;
    }

    public int validaEntrada() {
        Scanner teclado = new Scanner(System.in);
        int opcao = teclado.nextInt();
        teclado.nextLine();
        if(opcao <= 0) {
            System.out.println("Digito invalido");
        }
        return opcao;
    }

}

