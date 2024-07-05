package com.compass.entities;

import com.compass.enums.Genero;
import com.compass.enums.TamanhoRoupa;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "tb_roupas")
public class Roupa extends Item implements Serializable {
    private static final Long serialVersionUID= 1L;

    private Genero genero;
    private TamanhoRoupa tamanho;
}
