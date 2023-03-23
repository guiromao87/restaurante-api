package br.com.tex.restauranteapi.controller.advice;

import br.com.tex.restauranteapi.model.dto.erro.ErroOutputDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity erroNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity erroNotFoundDelete() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> erroBadRequest(MethodArgumentNotValidException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getFieldErrors().stream().map(e -> new ErroOutputDto(e.getField(), e.getDefaultMessage())).toList());
    }



}
