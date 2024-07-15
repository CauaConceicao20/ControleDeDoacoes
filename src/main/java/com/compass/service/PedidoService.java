package com.compass.service;

import com.compass.dao.PedidoDao;
import com.compass.entities.Pedido;

import java.util.List;

public class PedidoService {

    PedidoDao pedidoDao = new PedidoDao();

    public void adicionaPedido(Pedido pedido) {
        pedidoDao.adiciona(pedido);
    }

    public List<Pedido> buscaTodosPedidos() {
        return pedidoDao.buscaTodos();
    }

    public Pedido buscaPedidoPorId(long id) {
        return pedidoDao.buscaPorId(id);
    }

    public void alteraPedido(Pedido pedido) {
        pedidoDao.alterar(pedido);
    }

    public void removePedido(Long id) {
        pedidoDao.remove(id);
    }
}
