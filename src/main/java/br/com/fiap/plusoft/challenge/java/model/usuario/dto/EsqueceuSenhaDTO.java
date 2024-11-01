package br.com.fiap.plusoft.challenge.java.model.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EsqueceuSenhaDTO {

    @NotEmpty(message = "O email não pode estar vazio")
    @Email
    private String email;

    @NotEmpty(message = "A senha não pode estar vazia")
    private String senha;
}
