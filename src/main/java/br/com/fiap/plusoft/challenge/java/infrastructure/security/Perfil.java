package br.com.fiap.plusoft.challenge.java.infrastructure.security;

import br.com.fiap.plusoft.challenge.java.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "INOV_TB_PERFIL")
public class Perfil {

    @Id
    @GeneratedValue
    @Column(name = "cd_perfil")
    private Long id;

    @Column(name = "nm_perfil", nullable = false, unique = true)
    private String nome;

    @Column(name = "label_perfil", nullable = false)
    private String label;

    @ManyToMany(mappedBy = "perfis", fetch = FetchType.EAGER)
    private Set<Usuario> usuarios = new HashSet<>();
}
