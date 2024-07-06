package com.compass.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "tb_produtos_de_higiene")
public class ProdutoHigiene extends Item implements Serializable {
    private static final long serialVersionUID= 1L;

    @ManyToOne
    @JoinColumn(name = "distribuidora_id")
    private Distribuidora distribuidora;

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
