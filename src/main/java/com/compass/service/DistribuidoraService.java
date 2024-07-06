package com.compass.service;

import com.compass.dao.DistribuidoraDao;
import com.compass.entities.Distribuidora;

import java.util.List;

public class DistribuidoraService {

    DistribuidoraDao distribuidoraDao = new DistribuidoraDao();

    public void adicionaDistribuidorasCsv(List<Distribuidora> distribuidoras) {
        for(Distribuidora distribuidora : distribuidoras) {
            distribuidoraDao.addDistribuidora(distribuidora);
        }
    }

    public List<Distribuidora> listaDistribuidorasCsv() {
        return distribuidoraDao.listaDistribuidoras();
    }

}
