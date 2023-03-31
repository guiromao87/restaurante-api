package br.com.tex.restauranteapi.service;

import br.com.tex.restauranteapi.model.ItemDePedido;
import br.com.tex.restauranteapi.model.Usuario;
import br.com.tex.restauranteapi.model.dto.ItemDePedidoOutputDto;
import br.com.tex.restauranteapi.model.dto.PedidoOutputDto;
import br.com.tex.restauranteapi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PedidoService {


    private PedidoRepository pedidoRepository;
    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public PedidoOutputDto toPedidoDto(Usuario usuario) {

        var itensDePedido = this.pedidoRepository.findByUsuario(usuario);

        var total = BigDecimal.ZERO;

        for(ItemDePedido item : itensDePedido) {
            total = total.add(item.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade())));
        }

        var itensDePedidoDto = itensDePedido.stream().map(pedido -> new ItemDePedidoOutputDto(pedido)).toList();

        return new PedidoOutputDto(itensDePedidoDto, total);

    }






}
