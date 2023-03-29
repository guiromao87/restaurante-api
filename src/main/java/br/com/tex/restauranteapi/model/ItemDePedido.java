package br.com.tex.restauranteapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class ItemDePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario usuario;
    private Integer quantidade;

    public ItemDePedido(Produto produto, Usuario usuario, Integer quantidade) {
        this.produto = produto;
        this.usuario = usuario;
        this.quantidade = quantidade;
    }
}
