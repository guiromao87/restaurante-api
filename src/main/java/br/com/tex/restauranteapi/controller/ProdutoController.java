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

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity cadastra(@RequestBody @Valid ProdutoInputDto produtoDto, UriComponentsBuilder uriBuilder) {
        System.out.println("DTO: " + produtoDto);



//        Categoria categoria = this.categoriaRepository.getReferenceById(produtoDto.getCategoriaId());
//        Produto produto = produtoDto.toProduto(categoria);
//        Produto salvo = this.produtoRepository.save(produto);

        return ResponseEntity.ok().build();
//                ResponseEntity
//                .created(uriBuilder.path("/produtos/{id}").buildAndExpand(salvo.getId()).toUri())
//                .body(new ProdutoOutputDto(salvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity buscaPor(@PathVariable int id) {
        Produto produto = this.produtoRepository.getReferenceById(id);
        return ResponseEntity.ok(new ProdutoOutputDto(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletaPor(@PathVariable int id) {
        Produto produto = this.produtoRepository.getReferenceById(id);
        this.produtoRepository.deleteById(produto.getId());

        return ResponseEntity.ok(new ProdutoOutputDto(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity altera(@PathVariable int id, @RequestBody ProdutoInputDto dto) {
        this.produtoRepository.getReferenceById(id);

        Produto produto = dto.toProduto(this.categoriaRepository.findById(dto.getCategoriaId()).get());
        produto.setId(id);

        Produto alterado = this.produtoRepository.save(produto);
        return ResponseEntity.ok(new ProdutoOutputDto(alterado));
    }













}
