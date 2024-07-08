package com.compass.entities;

import com.compass.entities.pk.PedidoItemPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_pedido_item")
public class PedidoItem implements Serializable {
    private static final long serialVersionUID= 1L;

    @EmbeddedId
    private PedidoItemPK id = new PedidoItemPK();;

    private Integer quantidade;

    public PedidoItem() {

    }
    public PedidoItem(Pedido pedido, Long itemId, Integer quantidade) {
        id.setPedido(pedido.getId());
        id.setItem(itemId);
        this.quantidade = quantidade;
    }

    public Long getPedido() {
        return id.getPedido();
    }

    public void setPedido(Long pedidoId) {
        id.setPedido(pedidoId);
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoItem that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(getQuantidade(), that.getQuantidade());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getQuantidade());
    }
}


