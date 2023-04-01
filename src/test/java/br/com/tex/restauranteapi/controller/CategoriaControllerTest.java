package br.com.tex.restauranteapi.controller;

import br.com.tex.restauranteapi.model.Categoria;
import br.com.tex.restauranteapi.model.dto.CategoriaInputDto;
import br.com.tex.restauranteapi.model.dto.CategoriaOutputDto;
import br.com.tex.restauranteapi.repository.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoriaRepository categoriaRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveRetornarOCodigoDeStatusForbidden() throws Exception {
        mockMvc.perform(get("/categorias")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void deveRetornarOCodigoDeStatusNoContent() throws Exception {
        mockMvc.perform(get("/categorias")).andExpect(status().isNoContent());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    public void deveRetornarOCodigoDeStatusOK() throws Exception {
        when(categoriaRepository.findAll())
                .thenReturn(Arrays
                        .asList(
                            new Categoria(1, "Comida"),
                            new Categoria(2, "Bebida")));

        mockMvc.perform(get("/categorias")).andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    public void deveRetornarTodasAsCategorias() throws Exception {
        when(categoriaRepository.findAll()).thenReturn(Arrays
                .asList(
                        new Categoria(1, "Comida"),
                        new Categoria(2, "Bebida"),
                        new Categoria(3, "Sobremesa")));

        var categoriasJson = mockMvc.perform(get("/categorias")).andReturn().getResponse().getContentAsString();

        var esperadoDTO = Arrays.asList(
                new CategoriaOutputDto(new Categoria(1, "Comida")),
                new CategoriaOutputDto(new Categoria(2, "Bebida")),
                new CategoriaOutputDto(new Categoria(3, "Sobremesa")));

        var esperadoJson = this.objectMapper.writeValueAsString(esperadoDTO);
        Assertions.assertEquals(esperadoJson, categoriasJson);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void deveRetornarOStatusCreated() throws Exception {
        when(categoriaRepository.save(any())).thenReturn(new Categoria(1, "Teste"));
        mockMvc.perform(
            post("/categorias")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(new CategoriaInputDto("Categoria"))))
                .andExpect(status().isCreated());
    }







}
