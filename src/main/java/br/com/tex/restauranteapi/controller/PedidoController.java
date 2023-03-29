package br.com.tex.restauranteapi.controller;

import br.com.tex.restauranteapi.model.ItemDePedido;
import br.com.tex.restauranteapi.model.Usuario;
import br.com.tex.restauranteapi.model.dto.ItemDePedidoOutputDto;
import br.com.tex.restauranteapi.model.dto.PedidoOutputDto;
import br.com.tex.restauranteapi.repository.PedidoRepository;
import br.com.tex.restauranteapi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity pedidos(@AuthenticationPrincipal Usuario usuario) {
        var itensDePedido = this.pedidoRepository.findByUsuario(usuario);
        return ResponseEntity.ok(this.pedidoService.toPedidoDto(itensDePedido));
    }
}
