package br.com.tex.restauranteapi.model.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PedidoOutputDto {
    private BigDecimal total;
    private List<ItemDePedidoOutputDto> itens = new ArrayList<>();

    public PedidoOutputDto(List<ItemDePedidoOutputDto> itensDTO, BigDecimal total) {
        this.itens = itensDTO;
        this.total = total;
    }
}
