package com.compass.entities;

import com.compass.enums.TipoItem;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@DiscriminatorValue("ProdutoHigiene")
public class ProdutoHigiene extends Item implements Serializable {
    private static final long serialVersionUID= 1L;


    public ProdutoHigiene() {

    }

    public ProdutoHigiene(Long id, TipoItem tipo, String descricao, Distribuidora distribuidora) {
        super(id, tipo, descricao, distribuidora);
    }



}
