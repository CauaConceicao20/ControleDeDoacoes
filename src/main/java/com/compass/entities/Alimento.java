package com.compass.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@DiscriminatorValue("Alimento")
public class Alimento extends Item implements Serializable {

    private static final long serialVersionUID= 1L;

    private int quantidade;

    private String unidadeDeMedida;

    @Temporal(TemporalType.DATE)
    private Date validade;

    @ManyToOne
    @JoinColumn(name = "distribuidora_id")
    private Distribuidora distribuidora;

    public Alimento() {
    }

    public Alimento(Long id, String tipo, String descricao, int quantidade, String unidadeDeMedida, Date validade, Distribuidora distribuidora) {
        super(id, tipo, descricao);
        this.quantidade = quantidade;
        this.unidadeDeMedida = unidadeDeMedida;
        this.validade = validade;
        this.distribuidora = distribuidora;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidadeDeMedida() {
        return unidadeDeMedida;
    }

    public void setUnidadeDeMedida(String unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public Distribuidora getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(Distribuidora distribuidora) {
        this.distribuidora = distribuidora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alimento alimento)) return false;
        if (!super.equals(o)) return false;
        return getQuantidade() == alimento.getQuantidade();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getQuantidade());
    }
}
