package br.com.alexandre.domain.entity;

import java.math.BigDecimal;

public class Produto {
    private Integer id;
    private String descricao;
    private Double preco_unitario;

    public Produto() {
    }

    public Produto(String descricao, Double preco_unitario) {
        this.descricao = descricao;
        this.preco_unitario = preco_unitario;
    }

    public Produto(Integer id, String descricao, Double preco_unitario) {
        this.id = id;
        this.descricao = descricao;
        this.preco_unitario = preco_unitario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(Double preco_unitario) {
        this.preco_unitario = preco_unitario;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", preco_unitario=" + preco_unitario +
                '}';
    }
}
