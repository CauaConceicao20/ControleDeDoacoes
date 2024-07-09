package com.compass.service;

import com.compass.dao.AbrigoDao;
import com.compass.entities.Abrigo;
import com.compass.entities.Item;

public class AbrigoService {

    AbrigoDao abrigoDao = new AbrigoDao();

    public void addAbrigo(Abrigo abrigo) {
        abrigoDao.addAbrigoBd(abrigo);
    }

    //switch case vai ser feito na main pra alterar abrigo
    public void alterarAbrigo(Abrigo abrigo) {
        abrigoDao.altera(abrigo);
    }

    public Abrigo buscaAbrigoPorId(Long id) {
        return abrigoDao.buscaPorId(id);
    }

    public void removeAbrigo(Long id) {
        abrigoDao.remove(id);
    }

}
