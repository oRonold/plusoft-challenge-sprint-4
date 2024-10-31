package br.com.fiap.plusoft.challenge.java.model.pesquisa;

import lombok.Getter;

@Getter
public enum StatusPesquisa {

    EM_ANDAMENTO("Em Andamento"), CONCLUIDA("Concluída");

    private String label;

    StatusPesquisa(String label) {
        this.label = label;
    }
}
