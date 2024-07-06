package com.compass.entities;

import com.compass.enums.Genero;
import com.compass.enums.TamanhoRoupa;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_roupas")
public class Roupa extends Item implements Serializable {
    private static final long serialVersionUID= 1L;

    @Enumerated(EnumType.STRING)
    private Genero genero;
    @Enumerated(EnumType.STRING)
    private TamanhoRoupa tamanho;

    @ManyToOne
    @JoinColumn(name = "distribuidora_id")
    private Distribuidora distribuidora;

    public Roupa(Long id, String tipo, String descricao, Genero genero, TamanhoRoupa tamanho, Distribuidora distribuidora) {
        super(id, tipo ,descricao);
        this.genero = genero;
        this.tamanho = tamanho;
        this.distribuidora = distribuidora;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public TamanhoRoupa getTamanho() {
        return tamanho;
    }

    public void setTamanho(TamanhoRoupa tamanho) {
        this.tamanho = tamanho;
    }

    public Distribuidora getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(Distribuidora distribuidora) {
        this.distribuidora = distribuidora;
    }
}
