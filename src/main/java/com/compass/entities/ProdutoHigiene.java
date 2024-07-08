package com.compass.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@DiscriminatorValue("ProdutoHigiene")
public class ProdutoHigiene extends Item implements Serializable {
    private static final long serialVersionUID= 1L;

    @ManyToOne
    @JoinColumn(name = "distribuidora_id")
    private Distribuidora distribuidora;

    public ProdutoHigiene() {

    }

    public ProdutoHigiene(Long id, String tipo, String descricao, Distribuidora distribuidora) {
        super(id, tipo, descricao);
        this.distribuidora = distribuidora;
    }

    public Distribuidora getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(Distribuidora distribuidora) {
        this.distribuidora = distribuidora;
    }


}
