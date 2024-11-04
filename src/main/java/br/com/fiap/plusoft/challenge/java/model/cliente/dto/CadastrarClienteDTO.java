package br.com.fiap.plusoft.challenge.java.model.cliente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class CadastrarClienteDTO {

    @NotEmpty
    private String nome;

    @NotNull
    @Past
    private LocalDate dataNascimento;

    @NotEmpty
    @Length(max = 15)
    private String cpf;

    @NotEmpty
    @Length(max = 15)
    private String telefone;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String senha;

    @NotEmpty
    private String nomeRamo;

    @NotEmpty
    private String descRamo;

    @NotEmpty
    private String numeroLogradouro;

    private String pontoReferencia;

    @NotEmpty
    private String nomeLogradouro;

    @NotEmpty
    @Length(max = 9)
    private String cep;

    @NotEmpty
    private String nomeBairro;

    private String zonaBairro;

    @NotEmpty
    private String nomeCidade;

    @NotEmpty
    private String numeroDDD;

    @NotEmpty
    private String nomeEstado;

    @NotEmpty
    private String nomePais;

    @NotEmpty
    private String codPais;

    @NotEmpty
    private List<String> perfil;
}
