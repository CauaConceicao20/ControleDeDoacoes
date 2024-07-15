package com.compass.service;

import com.compass.dao.AbrigoDao;
import com.compass.entities.Abrigo;
import com.compass.entities.Item;

import java.util.List;

public class AbrigoService {

    AbrigoDao abrigoDao = new AbrigoDao();

    public void addAbrigo(Abrigo abrigo) {
        abrigoDao.addAbrigoBd(abrigo);
    }

    public List<Abrigo> buscaTodosAbrigos() {
        return abrigoDao.buscaTodos();
    }

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
