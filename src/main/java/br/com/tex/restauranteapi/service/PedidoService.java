package br.com.tex.restauranteapi.service;

import br.com.tex.restauranteapi.model.ItemDePedido;
import br.com.tex.restauranteapi.model.dto.ItemDePedidoOutputDto;
import br.com.tex.restauranteapi.model.dto.PedidoOutputDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {
    public PedidoOutputDto toPedidoDto(List<ItemDePedido> itensDePedido) {
        var total = BigDecimal.ZERO;

        for(ItemDePedido item : itensDePedido) {
            var preco = item.getProduto().getPreco();
            var quantidade = item.getQuantidade();

            total = total.add(preco.multiply(new BigDecimal(quantidade)));
        }

        var itensDePedidoDto = itensDePedido.stream().map(pedido -> new ItemDePedidoOutputDto(pedido)).toList();

        return new PedidoOutputDto(itensDePedidoDto, total);
    }
}
