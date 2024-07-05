package com.compass.entities;

import jakarta.persistence.*;

import java.io.Serializable;

//@Entity
@MappedSuperclass
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Item implements Serializable {

    private static final Long serialVersionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;

    private int quantidade;


}
