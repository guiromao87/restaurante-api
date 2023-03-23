package br.com.tex.restauranteapi.repository;

import br.com.tex.restauranteapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
