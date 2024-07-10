package com.compass.service;

import com.compass.dao.DistribuidoraDao;
import com.compass.entities.Distribuidora;

import java.util.List;

public class DistribuidoraService {

    DistribuidoraDao distribuidoraDao = new DistribuidoraDao();

    public void adicionaDistribuidorasCsv(List<Distribuidora> distribuidoras) {
        for(Distribuidora distribuidora : distribuidoras) {
            distribuidoraDao.adiciona(distribuidora);
        }
    }

    public void adicionaDistribuidora(Distribuidora distribuidora) {
        distribuidoraDao.adiciona(distribuidora);
    }

    public List<Distribuidora> retornaDistribuidoras() {
        return distribuidoraDao.buscaTodos();
    }

    public Distribuidora buscaDistribuidoraPorId(long id) {
       return distribuidoraDao.buscaPorId(id);
    }

    public void alterarDistribuidora(Distribuidora distribuidora) {
        distribuidoraDao.alterar(distribuidora);
    }

    public void removeDistribuidroa(Long id) {
        distribuidoraDao.remove(id);
    }

}
