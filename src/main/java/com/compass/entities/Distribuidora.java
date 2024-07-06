package com.compass.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_distribuidora")
public class Distribuidora implements Serializable {

    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "distribuidora")
    private List<Roupa> roupas = new ArrayList<>();

    @OneToMany(mappedBy = "distribuidora")
    private List<ProdutoHigiene> produtosHigiene = new ArrayList<>();

    @OneToMany(mappedBy = "distribuidora")
    private List<Alimento> alimentos = new ArrayList<>();


    public Distribuidora() {

    }

    public Distribuidora(Long id, String nome, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Roupa> getRoupa() {
        return roupas;
    }

    public List<ProdutoHigiene> getProdutoHigiene() {
        return produtosHigiene;
    }

    public List<Alimento> getAlimento() {
        return alimentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Distribuidora that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
