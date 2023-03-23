package br.com.tex.restauranteapi.model.dto;

import br.com.tex.restauranteapi.model.Categoria;
import br.com.tex.restauranteapi.model.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProdutoInputDto {

    @NotBlank(message = "O campo nome não pode ser vazio nem nulo")
    private String nome;
    @NotBlank(message = "O campo descricao não pode ser vazio nem nulo")
    private String descricao;
    private BigDecimal preco;

    private Integer categoriaId;

    public Produto toProduto(Categoria categoria) {
        Produto produto = new Produto();
        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setPreco(this.preco);
        produto.setCategoria(categoria);

        return produto;
    }
}
