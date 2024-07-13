package com.compass.entities;

import com.compass.enums.TipoItem;
import com.compass.enums.UnidadeDeMedida;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@DiscriminatorValue("Alimento")
public class Alimento extends Item implements Serializable {

    private static final long serialVersionUID= 1L;

    private int quantidade;

    @Enumerated(EnumType.STRING)
    private UnidadeDeMedida unidadeDeMedida;

    @Temporal(TemporalType.DATE)
    private Date validade;


    public Alimento() {
    }

    public Alimento(Long id, TipoItem tipo, String descricao, int quantidade, UnidadeDeMedida unidadeDeMedida, Date validade, Distribuidora distribuidora) {
        super(id, tipo, descricao, distribuidora);
        this.quantidade = quantidade;
        this.unidadeDeMedida = unidadeDeMedida;
        this.validade = validade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public UnidadeDeMedida getUnidadeDeMedida() {
        return unidadeDeMedida;
    }

    public void setUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
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
