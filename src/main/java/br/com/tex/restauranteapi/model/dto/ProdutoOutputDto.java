package br.com.tex.restauranteapi.model.dto;

import br.com.tex.restauranteapi.model.Produto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
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
}
