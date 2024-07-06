package com.compass.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_abrigos")
public class Abrigo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @Embedded
    private Endereco endereco;

    @OneToOne
    @MapsId
    private Pessoa responsavel;
    private String telefone;

    private String email;

    private int capacidade;

    private double ocupacao;

    public Abrigo() {

    }

    public Abrigo(Long id, String nome, Endereco endereco, Pessoa responsavel, String telefone, String email, int capacidade, double ocupacao) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.responsavel = responsavel;
        this.telefone = telefone;
        this.email = email;
        this.capacidade = capacidade;
        this.ocupacao = ocupacao;
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

    public Pessoa getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Pessoa responsavel) {
        this.responsavel = responsavel;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public double getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(double ocupacao) {
        this.ocupacao = ocupacao;
    }
}
