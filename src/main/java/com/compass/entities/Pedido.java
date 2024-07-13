package com.compass.entities;

import com.compass.enums.PedidoStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PedidoStatus pedidoStatus;

    @ManyToOne
    @JoinColumn(name = "abrigo_id")
    private Abrigo abrigo;

    @ManyToOne
    @JoinColumn(name = "distribuidora_id")
    private Distribuidora distribuidora;

    @ManyToMany(mappedBy = "pedidos")
    private List<Item> items = new ArrayList<>();

    public Pedido() {

    }


    public Pedido(Long id, PedidoStatus pedidoStatus, Abrigo abrigo ,Distribuidora distribuidora) {
        this.id = id;
        this.pedidoStatus = pedidoStatus;
        this.abrigo = abrigo;
        this.distribuidora = distribuidora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoStatus getPedidoStatus() {
        return pedidoStatus;
    }

    public void setPedidoStatus(PedidoStatus pedidoStatus) {
        this.pedidoStatus = pedidoStatus;
    }

    public List<Item> getItems() {
        return items;
    }


    public Abrigo getAbrigo() {
        return abrigo;
    }

    public Distribuidora getDistribuidora() {
        return distribuidora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido pedido)) return false;
        return Objects.equals(getId(), pedido.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
