package br.com.alexandre.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name="produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descicao", length = 200)
    private String descricao;

    @Column(name = "preco_unitario")
    private BigDecimal preco_unitario;

//    @OneToMany(mappedBy = "produto")
//    private Set<ItemPedido> itens;

    public Produto() {
    }

    public Produto(String descricao, BigDecimal preco_unitario) {
        this.descricao = descricao;
        this.preco_unitario = preco_unitario;
    }

    public Produto(Integer id, String descricao, BigDecimal preco_unitario) {
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

    public BigDecimal getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(BigDecimal preco_unitario) {
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
