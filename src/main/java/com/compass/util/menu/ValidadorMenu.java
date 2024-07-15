package com.compass.util.menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorMenu {

    public void validaEntrada(int digito, int limiteMin, int limiteMax) {
        if (digito < limiteMin || digito > limiteMax) {
            System.out.println("Opção Invalida! Digite Novamente");
        }
    }

    public int validaEntrada(int max) {
        Scanner teclado = new Scanner(System.in);
        int opcao = teclado.nextInt();
        teclado.nextLine();
        if (opcao <= 0 || opcao > max) {
            System.out.println("Digito invalido");
        }
        return opcao;
    }

    public int validaEntrada() {
        Scanner teclado = new Scanner(System.in);
        int opcao = teclado.nextInt();
        teclado.nextLine();
        if (opcao <= 0) {
            System.out.println("Digito invalido");
        }
        return opcao;
    }

    public String obterDataValidade(String validadeStr) throws ParseException {
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

        // Verifica se a data corresponde ao padrão regex
        Matcher matcher = pattern.matcher(validadeStr);
        if (!matcher.matches()) {
            throw new ParseException("Formato de data inválido. Use yyyy-MM-dd.", 0);
        }

        // Verifica se a data não é uma data passada
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Impede datas inválidas de serem aceitas
        Date inputDate = sdf.parse(validadeStr);
        Date currentDate = new Date(); // Obtém a data atual
        if (inputDate.before(currentDate)) {
            throw new ParseException("Data não pode ser uma data passada.", 0);
        }
        // Se passou por todas as validações, a data é considerada válida
        return validadeStr;
    }

}

