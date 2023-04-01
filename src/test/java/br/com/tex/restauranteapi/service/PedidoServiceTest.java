package br.com.tex.restauranteapi.service;

import br.com.tex.restauranteapi.model.ItemDePedido;
import br.com.tex.restauranteapi.model.Produto;
import br.com.tex.restauranteapi.model.Usuario;
import br.com.tex.restauranteapi.repository.PedidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PedidoServiceTest {

    private Usuario usuario;

    @Mock
    private PedidoRepository pedidoRepository;

    private List<ItemDePedido> listaDeItensDePedido;

    private PedidoService pedidoService;

    private Produto produto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        this.usuario = new Usuario(1);
        this.produto = new Produto(new BigDecimal(25));
        this.listaDeItensDePedido = new ArrayList<>();

        this.listaDeItensDePedido.add(new ItemDePedido(produto, usuario, 1)); // 25
        this.listaDeItensDePedido.add(new ItemDePedido(produto, usuario, 2)); // 50
        this.listaDeItensDePedido.add(new ItemDePedido(produto, usuario, 3)); // 75

        this.pedidoService = new PedidoService(pedidoRepository);
    }

    @Test
    public void deveConterTresPedidosNoCarrinho() {
        Mockito.when(pedidoRepository.findByUsuario(usuario)).thenReturn(listaDeItensDePedido);
        var pedido = pedidoService.toPedidoDto(usuario);

        Assertions.assertEquals(3, pedido.getItens().size());
    }

    @Test
    public void naoDeveConterNenhumPedidoNoCarrinho() {
        List<ItemDePedido> zeroItens = new ArrayList<>();

        Mockito.when(pedidoRepository.findByUsuario(usuario)).thenReturn(zeroItens);
        var pedido = this.pedidoService.toPedidoDto(usuario);

        Assertions.assertTrue(pedido.getItens().isEmpty());
    }

    @Test
    public void deveRetornarOTotalDe150() {
        Mockito.when(pedidoRepository.findByUsuario(usuario)).thenReturn(listaDeItensDePedido);
        var pedido = this.pedidoService.toPedidoDto(usuario);
        var total = pedido.getTotal();

        Assertions.assertEquals(new BigDecimal(150), total);
    }










}
