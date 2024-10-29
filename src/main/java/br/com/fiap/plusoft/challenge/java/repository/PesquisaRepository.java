package br.com.fiap.plusoft.challenge.java.repository;

import br.com.fiap.plusoft.challenge.java.model.pesquisa.Pesquisa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PesquisaRepository extends JpaRepository<Pesquisa, Long> {
}
