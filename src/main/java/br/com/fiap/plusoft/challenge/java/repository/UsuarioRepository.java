package br.com.fiap.plusoft.challenge.java.repository;

import br.com.fiap.plusoft.challenge.java.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
