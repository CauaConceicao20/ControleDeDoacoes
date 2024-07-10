package com.compass.util;


import com.compass.entities.*;
import com.compass.enums.Genero;
import com.compass.enums.TamanhoRoupa;
import com.compass.enums.TipoItem;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final String DISTRIBUIDORA_PATH = "C:/Users/user/OneDrive/Documentos/desafio/controlededoacoes/dados_distribuidora.csv";
    private static final String ITEMS_PATH = "C:/Users/user/OneDrive/Documentos/desafio/controlededoacoes/dados_items.csv";

    public List<Distribuidora> lerDadosDeDistribuidora() {
        List<Distribuidora> distribuidoraList = new ArrayList<>();
        try(CSVReader leia = new CSVReader(new FileReader(DISTRIBUIDORA_PATH))) {
            String[] linha;
            leia.skip(1);
            while((linha = leia.readNext()) != null) {
                    Endereco endereco = new Endereco(linha[1], Integer.parseInt(linha[2]), linha[3], linha[4], linha[5], linha[6]);
                    Distribuidora distribuidora = new Distribuidora(null, linha[0], endereco);
                    distribuidoraList.add(distribuidora);
            }
        }catch(IOException | CsvValidationException e) {
            System.out.println(e.getMessage());
        }
        return distribuidoraList;
    }

    public List<Item> lerDadosDeItems(List<Distribuidora> distribuidoras) {

        List<Item> items = new ArrayList<>();
        try(CSVReader leia = new CSVReader(new FileReader(ITEMS_PATH))) {
            String[] linha;
            leia.skip(1);
            while((linha = leia.readNext()) != null) {
                    filtraDistribuidoras(linha, distribuidoras, items);
            }
        }catch(IOException | CsvValidationException | ParseException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }

    public void filtraDistribuidoras(String[] linha, List<Distribuidora> distribuidoras, List<Item> items) throws ParseException, IllegalArgumentException {
        switch (linha[0].toUpperCase()) {
            case "ROUPA":
                for(Distribuidora distribuidoraAssociada : distribuidoras) {
                    if (distribuidoraAssociada.getId() == Integer.parseInt(linha[4])) {
                        Roupa roupa = new Roupa(null, TipoItem.valueOf(linha[0].toUpperCase()), linha[1], Genero.valueOf(linha[2].toUpperCase()),
                                TamanhoRoupa.valueOf(linha[3].toUpperCase()), distribuidoraAssociada);
                        items.add(roupa);
                    }
                }
                break;
            case "PRODUTO_HIGIENE" :
                for(Distribuidora distribuidoraAssociada : distribuidoras) {
                    if (distribuidoraAssociada.getId() == Integer.parseInt(linha[2])) {
                        ProdutoHigiene produtoHigiene = new ProdutoHigiene(null, TipoItem.valueOf(linha[0].toUpperCase()), linha[1], distribuidoraAssociada);
                        items.add(produtoHigiene);
                    }
                }
                break;
            case "ALIMENTO" :
                for(Distribuidora distribuidoraAssociada : distribuidoras) {
                    if (distribuidoraAssociada.getId() == Integer.parseInt(linha[5])) {
                        Alimento alimento = new Alimento(null, TipoItem.valueOf(linha[0].toUpperCase()), linha[1], Integer.parseInt(linha[2]), linha[3], dateFormat.parse(linha[4]), distribuidoraAssociada);
                        items.add(alimento);
                    }
                }
                break;
            default :
                throw new IllegalArgumentException("Tipo de Item inexistente" + linha[0]);
        }
    }
}
