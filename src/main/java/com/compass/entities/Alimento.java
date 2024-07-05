package com.compass.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_alimentos")
public class Alimento extends Item implements Serializable {

    private String unidadeDeMedida;

    private Date validade;
}
