package com.compass.service;

import com.compass.dao.AbrigoDao;
import com.compass.entities.Abrigo;

public class AbrigoService {

    AbrigoDao abrigoDao = new AbrigoDao();

    public void addAbrigo(Abrigo abrigo) {
        abrigoDao.addAbrigoBd(abrigo);
    }

}
