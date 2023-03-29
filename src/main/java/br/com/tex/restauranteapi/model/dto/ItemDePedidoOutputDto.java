package br.com.tex.restauranteapi.model.dto;

import br.com.tex.restauranteapi.model.ItemDePedido;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ItemDePedidoOutputDto {
    private String produto;
    private String usuario;
    private Integer quantidade;

    public ItemDePedidoOutputDto(ItemDePedido item) {
        this.produto = item.getProduto().getNome();
        this.usuario = item.getUsuario().getLogin();
        this.quantidade = item.getQuantidade();
    }
}
