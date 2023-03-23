package br.com.tex.restauranteapi.controller;

import br.com.tex.restauranteapi.model.Categoria;
import br.com.tex.restauranteapi.model.dto.*;
import br.com.tex.restauranteapi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity lista() {
        var categorias = this.categoriaRepository.findAll();

        if(categorias.size() == 0)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(categorias.stream().map(c -> new CategoriaOutputDto(c)).toList());
    }

    @GetMapping("/{id}/produtos")
    public ResponseEntity buscaProdutoPorCategoria(@PathVariable int id) {
        Categoria categoria = this.categoriaRepository.getReferenceById(id);
        return ResponseEntity.ok(new CategoriaComProdutosOutputDto(categoria));
    }

    @PostMapping
    public ResponseEntity cadastra(@RequestBody @Valid CategoriaInputDto categoriaInputDto, UriComponentsBuilder uriBuilder) {
        var categoria = this.categoriaRepository.save(new Categoria(categoriaInputDto.nome()));
        URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaOutputDto(categoria));
    }



}
