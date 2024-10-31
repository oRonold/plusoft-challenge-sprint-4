package br.com.fiap.plusoft.challenge.java.service;

import br.com.fiap.plusoft.challenge.java.model.cliente.Cliente;
import br.com.fiap.plusoft.challenge.java.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente detalhesConta(){
        var usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        return repository.findByUsuarioEmail(usuario);
    }
}
