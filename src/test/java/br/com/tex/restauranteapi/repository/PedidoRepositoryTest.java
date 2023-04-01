package br.com.tex.restauranteapi.repository;

import br.com.tex.restauranteapi.model.ItemDePedido;
import br.com.tex.restauranteapi.model.Produto;
import br.com.tex.restauranteapi.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.math.RoundingMode;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:mysql://localhost:3306/restauranteteste"
})
public class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository pedidoRepository;
    private Usuario usuario;

    @BeforeEach
    public void setup() {
        this.usuario = new Usuario(1);
    }

    @Test
    public void deveRetornar3PedidosParaUsuario1() {
        this.pedidoRepository.save(new ItemDePedido(new Produto(1), usuario, 2));
        this.pedidoRepository.save(new ItemDePedido(new Produto(2), usuario, 1));
        this.pedidoRepository.save(new ItemDePedido(new Produto(5), usuario, 3));

        var pedidos = this.pedidoRepository.findByUsuario(usuario);
        Assertions.assertEquals(3, pedidos.size());
    }

    @Test
    public void naoDeveRetornarNenhumPedidoParaUsuario2() {
        this.pedidoRepository.save(new ItemDePedido(new Produto(1), usuario, 2));
        this.pedidoRepository.save(new ItemDePedido(new Produto(2), usuario, 1));
        this.pedidoRepository.save(new ItemDePedido(new Produto(5), usuario, 3));

        var pedidos = this.pedidoRepository.findByUsuario(new Usuario(2));
        Assertions.assertEquals(0, pedidos.size());
    }

    @Test
    public void calculaMedia() {
        this.pedidoRepository.save(new ItemDePedido(new Produto(1), usuario, 2));
        this.pedidoRepository.save(new ItemDePedido(new Produto(2), usuario, 1));
        this.pedidoRepository.save(new ItemDePedido(new Produto(5), usuario, 3));

        var media = this.pedidoRepository.media();
        Assertions.assertEquals(new BigDecimal(69).setScale(2, RoundingMode.HALF_EVEN), media);
    }






}
