package com.compass.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "tb_produtos_de_higiene")
public class ProdutosHigiene extends Item implements Serializable {
    private static final Long serialVersionUID= 1L;


}
