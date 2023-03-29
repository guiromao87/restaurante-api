package br.com.tex.restauranteapi.repository;

import br.com.tex.restauranteapi.model.ItemDePedido;
import br.com.tex.restauranteapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<ItemDePedido, Integer> {
    List<ItemDePedido> findByUsuario(Usuario usuario);
}
