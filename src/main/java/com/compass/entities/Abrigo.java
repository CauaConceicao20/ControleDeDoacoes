package com.compass.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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

    private double porcentagemOcupacao;

    @OneToMany(mappedBy = "abrigo")
    private List<Pedido> pedidos;

    public Abrigo() {

    }

    public Abrigo(Long id, String nome, Endereco endereco, Pessoa responsavel, String telefone, String email, int capacidade) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.responsavel = responsavel;
        this.telefone = telefone;
        this.email = email;
        this.capacidade = capacidade;
        this.porcentagemOcupacao = retornaPorcentagem();
    }

    public double retornaPorcentagem() {
        Random random = new Random();
        int numeroDeAbrigados = random.nextInt(capacidade - 1) + 1;
        double porcentagem = (double) numeroDeAbrigados / capacidade * 100;
        return  Math.round(porcentagem * 10.0) / 10.0;
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
        return porcentagemOcupacao;
    }

    public void setOcupacao(double ocupacao) {
        this.porcentagemOcupacao = ocupacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Abrigo abrigo)) return false;
        return Objects.equals(getId(), abrigo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Abrigo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco=" + endereco +
                ", responsavel=" + responsavel +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", capacidade=" + capacidade +
                ", ocupacao=" + porcentagemOcupacao + "%" +
                '}';
    }
}
