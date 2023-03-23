package br.com.tex.restauranteapi.model.dto;

import br.com.tex.restauranteapi.model.Produto;

import java.math.BigDecimal;

public class ProdutoOutputDto {
    private int id;
    private String nome;
    private BigDecimal preco;
    private String categoria;

    public ProdutoOutputDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.categoria = produto.getCategoria().getNome();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getCategoria() {
        return categoria;
    }
}
