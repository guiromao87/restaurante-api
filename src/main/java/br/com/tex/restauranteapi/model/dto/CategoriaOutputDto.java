package br.com.tex.restauranteapi.model.dto;

import br.com.tex.restauranteapi.model.Categoria;
import lombok.Getter;

@Getter
public class CategoriaOutputDto {

    private int id;
    private String nome;


    public CategoriaOutputDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }
}
