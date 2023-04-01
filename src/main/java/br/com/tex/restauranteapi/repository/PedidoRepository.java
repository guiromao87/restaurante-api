package br.com.tex.restauranteapi.repository;

import br.com.tex.restauranteapi.model.ItemDePedido;
import br.com.tex.restauranteapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.math.BigDecimal;
import java.util.List;

public interface PedidoRepository extends JpaRepository<ItemDePedido, Integer> {
    List<ItemDePedido> findByUsuario(Usuario usuario);

    @Query(value = "select round(avg(prod.preco * p.quantidade),2) as media from pedidos p inner join produtos prod on p.produto_id = prod.id", nativeQuery = true)
    BigDecimal media();
}
