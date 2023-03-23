package br.com.tex.restauranteapi.model.dto;

import javax.validation.constraints.NotBlank;

public record CategoriaInputDto(@NotBlank String nome) { }
