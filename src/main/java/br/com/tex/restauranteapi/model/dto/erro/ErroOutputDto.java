package br.com.tex.restauranteapi.model.dto.erro;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErroOutputDto {
    private String campo;
    private String descricao;
}
