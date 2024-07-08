package com.compass.entities.pk;


import com.compass.entities.Item;
import com.compass.entities.Pedido;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;


@Embeddable
public class PedidoItemPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedidoId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item itemId;

    public Pedido getPedido() {
        return pedidoId;
    }

    public void setPedido(Pedido pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Item getItem() {
        return itemId;
    }

    public void setItem(Item itemId) {
        this.itemId = itemId;
    }

}
