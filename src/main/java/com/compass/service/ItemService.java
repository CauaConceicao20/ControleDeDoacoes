package com.compass.service;

import com.compass.dao.ItemDao;
import com.compass.entities.Item;

import java.util.List;

public class ItemService {

    ItemDao itemDao = new ItemDao();

    public void addItemsCsv(List<Item> items) {
        for(Item item : items) {
            itemDao.addItem(item);
        }
    }


}
