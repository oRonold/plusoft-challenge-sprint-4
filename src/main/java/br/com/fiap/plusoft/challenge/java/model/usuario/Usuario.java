package br.com.fiap.plusoft.challenge.java.model.usuario;

import br.com.fiap.plusoft.challenge.java.infrastructure.security.Perfil;
import br.com.fiap.plusoft.challenge.java.model.cliente.Cliente;
import br.com.fiap.plusoft.challenge.java.model.pesquisa.Pesquisa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_USUARIO")
@SequenceGenerator(name = "inov_usuario_seq", sequenceName = "inov_tb_usuario_seq", allocationSize = 1, initialValue = 1)
public class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_usuario_seq")
    @Column(name = "cd_usuario")
    private Long codigo;
    @Column(name = "nm_usuario", length = 100, nullable = false)
    private String nome;
    @Column(name = "ds_email", length = 100, nullable = false, unique = true)
    private String email;
    @Column(name = "ds_senha", length = 100, nullable = false)
    private String senha;

    @OneToOne(mappedBy = "usuario")
    private Cliente cliente;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pesquisa> pesquisas;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "INOV_TB_USUARIO_PERFIL",
        joinColumns = @JoinColumn(name = "cd_usuario"),
        inverseJoinColumns = @JoinColumn(name = "cd_perfil"))
    private Set<Perfil> perfis;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfis.stream()
                .map(perfil -> new SimpleGrantedAuthority("ROLE_" + perfil.getNome()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
