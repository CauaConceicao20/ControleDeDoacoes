package com.compass.service;

import com.compass.dao.ItemDao;
import com.compass.entities.Item;
import com.compass.entities.Roupa;

import java.util.List;

public class ItemService {

    ItemDao itemDao = new ItemDao();

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

    public void alteraItem(Long id, String descricao) {
        itemDao.alterar(id, descricao);
    }

    public void removeItem(Long id){
        itemDao.remove(id);
    }



}
