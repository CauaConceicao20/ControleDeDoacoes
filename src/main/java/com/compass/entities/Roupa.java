package com.compass.entities;

import com.compass.enums.Genero;
import com.compass.enums.TamanhoRoupa;
import com.compass.enums.TipoItem;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@DiscriminatorValue("Roupa")
public class Roupa extends Item implements Serializable {
    private static final long serialVersionUID= 1L;

    @Enumerated(EnumType.STRING)
    private Genero genero;
    @Enumerated(EnumType.STRING)
    private TamanhoRoupa tamanho;


    public Roupa() {

    }

    public Roupa(Long id, TipoItem tipo, String descricao, Genero genero, TamanhoRoupa tamanho, Abrigo abrigo, Distribuidora distribuidora) {
        super(id, tipo ,descricao, abrigo, distribuidora);
        this.genero = genero;
        this.tamanho = tamanho;
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

}
