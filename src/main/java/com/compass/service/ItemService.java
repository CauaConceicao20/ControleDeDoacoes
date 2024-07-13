package com.compass.service;

import com.compass.dao.ItemDao;
import com.compass.entities.*;
import com.compass.enums.Genero;
import com.compass.enums.TamanhoRoupa;
import com.compass.enums.UnidadeDeMedida;
import com.compass.exception.LimiteAlcancadoException;

import java.util.List;

public class ItemService {

    ItemDao itemDao = new ItemDao();

    public void adiciona(Item item) throws LimiteAlcancadoException {
        int quantidadeItem = determinaQuantidadeDeItems(item);
            if (quantidadeItem < 1000 && quantidadeItem > 0) {
                itemDao.adiciona(item);
            }else if(quantidadeItem > 1000) {
                throw new LimiteAlcancadoException("Não é possivel doar!! Limite da distribuidora alcançado");
            }else {
                throw new LimiteAlcancadoException("Não é possivel doar!! items vazios");
            }
    }

    public void adicionaItemsCsv(List<Item> items) {
        for(Item item : items) {
            itemDao.adiciona(item);
        }
    }
    public List<Item> retornaItems() {
        return itemDao.buscaTodos();
    }

    public Item buscaItemPorId(Long id) {
        return itemDao.buscaPorId(id);
    }

    public List<Roupa> buscaPersonalizadaDeRoupas(String descricao, TamanhoRoupa tamanho, Genero genero) {
       return itemDao.buscaPersonalizadaDeRoupa(descricao, tamanho, genero);
    }

    public List<ProdutoHigiene> buscaPersonalizadaDeProdutosHigiene(String descricao) {
        return itemDao.buscaPersonalizadaDeProdutoHigiene(descricao);
    }

    public List<Alimento> buscaPersonalzadaDeAlimentos(String descricao, UnidadeDeMedida unidadeDeMedida, int quantidade) {
        return itemDao.buscaPersonalizadaDeAlimentos(descricao, unidadeDeMedida, quantidade);
    }

    public void alteraItem(Item item) {
        itemDao.alterar(item);
    }

    public void removeItem(Long id){
        itemDao.remove(id);
    }

    public int retornaQuantidadeDeRoupas(Item item) {
        List<Item> list = retornaItems();
        int quantidadeDeRoupas = 0;
        for (Item itemList : list) {
            if (item.getDistribuidora().getId() == itemList.getDistribuidora().getId() && itemList instanceof Roupa) {
                quantidadeDeRoupas++;
            }
        }
        return quantidadeDeRoupas;
    }

    public int retornaQuantidadeDeAlimentos(Item item) {
        List<Item> list = retornaItems();
        int quantidadeDeAlimentos = 0;
        for (Item itemList : list) {
            if (item.getDistribuidora().getId() == itemList.getDistribuidora().getId() && itemList instanceof Alimento) {
                quantidadeDeAlimentos++ ;
            }
        }
        return quantidadeDeAlimentos;
    }

    public int retornaQuantidadeDeProdutosDeHigiene(Item item) {
        List<Item> list = retornaItems();
        int quantidadeDeProdutosHigiene = 0;
        for (Item itemList : list) {
            if (item.getDistribuidora().getId() == itemList.getDistribuidora().getId() && itemList instanceof ProdutoHigiene) {
                quantidadeDeProdutosHigiene++;
            }
        }
        return quantidadeDeProdutosHigiene;
    }

    public int determinaQuantidadeDeItems(Item item) {
        int quantidadeItem = 0;

        switch (item.getTipo()) {
            case ROUPA:
                quantidadeItem = retornaQuantidadeDeRoupas(item);
                break;
            case ALIMENTO:
                quantidadeItem = retornaQuantidadeDeAlimentos(item);
                break;
            case PRODUTO_HIGIENE:
                quantidadeItem = retornaQuantidadeDeProdutosDeHigiene(item);
                break;
            default:
                throw new IllegalArgumentException("Tipo de item inexistente" + item.getTipo());
        }
        return quantidadeItem;
    }
}
