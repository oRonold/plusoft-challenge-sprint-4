package br.com.fiap.plusoft.challenge.java.model.pesquisa.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class CriarPesquisaDTO {

    @NotEmpty(message = "Descrição da pesquisa não pode ser nulo")
    private String descricao;

    @NotEmpty(message = "O nome da figura publica não pode ser nulo")
    private String nomeFigPublica;

    private String nomeArtistico;

    @NotEmpty(message = "A rede social é obrigátoria")
    private String nomeRedeSocial;

    @NotNull(message = "Score não pode ser nulo")
    private BigDecimal numeroScore;

    @NotEmpty(message = "Categoria não pode ser nulo")
    private String categoria;

    @NotEmpty(message = "Tipo serviço não pode ser nulo")
    private String tipoServico;

}
