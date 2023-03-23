package br.com.tex.restauranteapi.controller;

import br.com.tex.restauranteapi.model.Categoria;
import br.com.tex.restauranteapi.model.Produto;
import br.com.tex.restauranteapi.model.dto.ProdutoInputDto;
import br.com.tex.restauranteapi.model.dto.ProdutoOutputDto;
import br.com.tex.restauranteapi.repository.CategoriaRepository;
import br.com.tex.restauranteapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity lista() {
        List<Produto> produtos =  produtoRepository.findAll();

        if(produtos.size() == 0)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(produtos.stream().map(produto -> new ProdutoOutputDto(produto)).toList());
    }

    @PostMapping
    public ResponseEntity cadastra(@RequestBody ProdutoInputDto produtoDto, UriComponentsBuilder uriBuilder) {
        Categoria categoria = this.categoriaRepository.getReferenceById(produtoDto.getCategoriaId());
        Produto produto = produtoDto.toProduto(categoria);
        Produto salvo = this.produtoRepository.save(produto);

        return ResponseEntity
                .created(uriBuilder.path("/produtos/{id}").buildAndExpand(salvo.getId()).toUri())
                .body(new ProdutoOutputDto(salvo));
    }
}
