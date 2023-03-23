package br.com.tex.restauranteapi.model.dto.erro;

public class ErroOutputDto {
    private String campo;
    private String descricao;

    public ErroOutputDto(String campo, String descricao) {
        this.campo = campo;
        this.descricao = descricao;
    }

    public String getCampo() { return campo; }

    public String getDescricao() { return descricao; }
}
