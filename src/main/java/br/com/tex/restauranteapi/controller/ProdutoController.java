package br.com.tex.restauranteapi.controller;

import br.com.tex.restauranteapi.model.Categoria;
import br.com.tex.restauranteapi.model.Produto;
import br.com.tex.restauranteapi.model.dto.ProdutoInputDto;
import br.com.tex.restauranteapi.repository.CategoriaRepository;
import br.com.tex.restauranteapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Produto> lista() {
        return produtoRepository.findAll();
    }

    @PostMapping
    public Produto cadastra(@RequestBody ProdutoInputDto produtoDto) {
        System.out.println("Descricao: " + produtoDto.getDescricao());
        Categoria categoria = this.categoriaRepository.getReferenceById(produtoDto.getCategoriaId());

        System.out.println("Categoria: " + categoria);

        Produto produto = new Produto();
        produto.setNome(produtoDto.getNome());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setPreco(produtoDto.getPreco());
        produto.setCategoria(categoria);

        System.out.println("produto: " + produto);

        return this.produtoRepository.save(produto);
    }
}
