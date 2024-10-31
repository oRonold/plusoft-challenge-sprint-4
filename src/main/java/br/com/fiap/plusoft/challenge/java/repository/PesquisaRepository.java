package br.com.fiap.plusoft.challenge.java.repository;

import br.com.fiap.plusoft.challenge.java.model.pesquisa.Pesquisa;
import br.com.fiap.plusoft.challenge.java.model.usuario.Usuario;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PesquisaRepository extends JpaRepository<Pesquisa, Long> {

    @Query("select p from Pesquisa p where p.usuario.email = ?1")
    List<Pesquisa> findByUsuarioEmail(String email);

    @Modifying
    @Transactional
    @Query("delete from Pesquisa p where p.codigo = ?1")
    void deletePesquisaByById(Long id);
}
