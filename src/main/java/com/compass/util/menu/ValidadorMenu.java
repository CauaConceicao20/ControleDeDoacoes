package com.compass.util.menu;

public class ValidadorMenu {

    public void validaEntrada(int digito, int limiteMin, int limiteMax) {
        if(digito <= limiteMin || digito > limiteMax) {
            System.out.println("Opção Invalida! Digite Novamente");
        }
    }
}
