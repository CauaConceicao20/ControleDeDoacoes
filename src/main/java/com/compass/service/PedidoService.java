package com.compass.service;

import com.compass.dao.PedidoDao;
import com.compass.entities.Pedido;

public class PedidoService {

    PedidoDao pedidoDao = new PedidoDao();

    public void adicionaPedido(Pedido pedido) {
        pedidoDao.adiciona(pedido);
    }
}
