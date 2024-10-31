package br.com.fiap.plusoft.challenge.java.repository;

import br.com.fiap.plusoft.challenge.java.model.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByUsuarioEmail(String email);
}
