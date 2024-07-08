package com.compass.entities.pk;


import jakarta.persistence.Embeddable;

import java.io.Serializable;


@Embeddable
public class PedidoItemPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long pedidoId;

    private Long itemId;

    public Long getPedido() {
        return pedidoId;
    }

    public void setPedido(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getItem() {
        return itemId;
    }

    public void setItem(Long itemId) {
        this.itemId = itemId;
    }

}
