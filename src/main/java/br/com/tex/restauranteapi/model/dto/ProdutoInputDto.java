package br.com.tex.restauranteapi.model.dto;

import br.com.tex.restauranteapi.model.Categoria;
import br.com.tex.restauranteapi.model.Produto;

import java.math.BigDecimal;

public class ProdutoInputDto {
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer categoriaId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Produto toProduto(Categoria categoria) {
        Produto produto = new Produto();
        produto.setNome(this.getNome());
        produto.setDescricao(this.getDescricao());
        produto.setPreco(this.getPreco());
        produto.setCategoria(categoria);

        return produto;
    }
}
