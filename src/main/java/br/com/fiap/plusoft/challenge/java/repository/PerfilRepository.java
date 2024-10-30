package br.com.fiap.plusoft.challenge.java.repository;

import br.com.fiap.plusoft.challenge.java.infrastructure.security.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Perfil findByNome(String nome);
}
