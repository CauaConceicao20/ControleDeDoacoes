package com.compass.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="tb_pessoas")
public class Pessoa implements Serializable {

    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;
    @OneToOne(mappedBy = "responsavel", cascade = CascadeType.ALL)
    private Abrigo abrigo;

    public Pessoa() {

    }

    public Pessoa(Long id, String nome, String cpf, Abrigo abrigo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.abrigo = abrigo;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Abrigo getAbrigo() {
        return abrigo;
    }

    public void setAbrigo(Abrigo abrigo) {
        this.abrigo = abrigo;
    }
}
