package br.com.tex.restauranteapi.model.dto;

import br.com.tex.restauranteapi.model.Produto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoPorCategoriaOutputDto {
    private String nome;
    private BigDecimal preco;

    public ProdutoPorCategoriaOutputDto(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
    }
}
