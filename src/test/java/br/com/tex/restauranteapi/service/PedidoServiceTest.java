package br.com.tex.restauranteapi.service;

import br.com.tex.restauranteapi.model.Usuario;
import br.com.tex.restauranteapi.repository.PedidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PedidoServiceTest {

    private Usuario usuario;
//    private PedidoService pedidoService;

    @BeforeEach
    public void setup() {
        this.usuario = new Usuario(1);
//        this.pedidoService = new PedidoService();
    }



    @Test
    public void teste() {
        PedidoRepository repositoryMock = Mockito.mock(PedidoRepository.class);
        var pedidos = repositoryMock.findByUsuario(usuario);

        System.out.println("Pedidos: " + pedidos.size());

    }










}
