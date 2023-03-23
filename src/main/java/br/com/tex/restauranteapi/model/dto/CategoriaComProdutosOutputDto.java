package br.com.tex.restauranteapi.model.dto;

import br.com.tex.restauranteapi.model.Categoria;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoriaComProdutosOutputDto {
    private int id;
    private String nome;
    private List<ProdutoPorCategoriaOutputDto> produtos = new ArrayList<>();

    public CategoriaComProdutosOutputDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.produtos = categoria.getProdutos().stream().map(produto -> new ProdutoPorCategoriaOutputDto(produto)).toList();
    }
}
