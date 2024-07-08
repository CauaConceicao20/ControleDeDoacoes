package com.compass.service;

import com.compass.dao.ItemDao;
import com.compass.entities.Item;
import com.compass.entities.Roupa;

import java.util.List;

public class ItemService {

    ItemDao itemDao = new ItemDao();

    public void addItemsCsv(List<Item> items) {
        for(Item item : items) {
            itemDao.addItemBd(item);
        }
    }

    public List<Item> retornaItems() {
        return itemDao.retornaItemBd();
    }



}
