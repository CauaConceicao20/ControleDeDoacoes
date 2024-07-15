package com.compass.service;

import com.compass.dao.DistribuidoraDao;
import com.compass.entities.Distribuidora;

import java.util.Comparator;
import java.util.List;

public class DistribuidoraService {

    DistribuidoraDao distribuidoraDao = new DistribuidoraDao();

    public void adicionaDistribuidorasCsv(List<Distribuidora> distribuidoras) {
        for (Distribuidora distribuidora : distribuidoras) {
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


    public List<Distribuidora> adicionaQuantidade(List<Distribuidora> distribuidoras, int quantidadeDistribuidora1, int quantidadeDistribuidora2, int quantidadeDistribuidora3) {
        for (Distribuidora distribuidora : distribuidoras) {
            try {
                Long distribuidoraId = distribuidora.getId();
                if (distribuidoraId != null) {
                    if (distribuidoraId == 1) {
                        distribuidora.setQuantidade(quantidadeDistribuidora1);
                    } else if (distribuidoraId == 2) {
                        distribuidora.setQuantidade(quantidadeDistribuidora2);
                    } else if (distribuidoraId == 3) {
                        distribuidora.setQuantidade(quantidadeDistribuidora3);
                    }
                }
            } catch (NullPointerException e) {

            } catch (Exception e) {
            }
        }
        return distribuidoras;
    }

    public List<Distribuidora> organizaDistribuidoraPorQuantidade(List<Distribuidora> distribuidoras, int quantidadeSolicitada) {
        Comparator<Distribuidora> comparator = new Comparator<Distribuidora>() {
            @Override
            public int compare(Distribuidora distribuidora1, Distribuidora distribuidora2) {

                if (distribuidora1.getQuantidade() >= quantidadeSolicitada && distribuidora2.getQuantidade() >= quantidadeSolicitada) {
                    return Integer.compare(distribuidora1.getQuantidade(), distribuidora2.getQuantidade());
                } else if (distribuidora1.getQuantidade() >= quantidadeSolicitada) {
                    return -1;
                } else if (distribuidora2.getQuantidade() >= quantidadeSolicitada) {
                    return 1;
                } else {
                    return Integer.compare(Math.abs(distribuidora1.getQuantidade() - quantidadeSolicitada),
                            Math.abs(distribuidora2.getQuantidade() - quantidadeSolicitada));
                }
            }

        };
        distribuidoras.sort(comparator);
        return distribuidoras;
    }


}
