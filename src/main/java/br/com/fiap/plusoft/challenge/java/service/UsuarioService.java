package br.com.fiap.plusoft.challenge.java.service;

import br.com.fiap.plusoft.challenge.java.infrastructure.security.Perfil;
import br.com.fiap.plusoft.challenge.java.model.cliente.Cliente;
import br.com.fiap.plusoft.challenge.java.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.plusoft.challenge.java.model.endereco.EnderecoCliente;
import br.com.fiap.plusoft.challenge.java.model.endereco.bairro.Bairro;
import br.com.fiap.plusoft.challenge.java.model.endereco.cidade.Cidade;
import br.com.fiap.plusoft.challenge.java.model.endereco.estado.Estado;
import br.com.fiap.plusoft.challenge.java.model.endereco.logradouro.Logradouro;
import br.com.fiap.plusoft.challenge.java.model.endereco.pais.Pais;
import br.com.fiap.plusoft.challenge.java.model.ramo.Ramo;
import br.com.fiap.plusoft.challenge.java.model.usuario.Usuario;
import br.com.fiap.plusoft.challenge.java.repository.ClienteRepository;
import br.com.fiap.plusoft.challenge.java.repository.PerfilRepository;
import br.com.fiap.plusoft.challenge.java.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("Usuario não encontrado");
        }

        Set<SimpleGrantedAuthority> grantedAuthorities = usuario.getPerfis().stream()
                .map(perfil -> new SimpleGrantedAuthority(perfil.getNome())).collect(Collectors.toSet());

        return new User(usuario.getEmail(), usuario.getSenha(), grantedAuthorities);
    }

    public String logout(HttpServletRequest request, HttpServletResponse response) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    public void salvarUsuario(CadastrarClienteDTO dto, List<String> roles) {
        Usuario user = new Usuario();
        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setSenha(passwordEncoder.encode(dto.getSenha()));

        HashSet<Perfil> perfilUsuario = new HashSet<>();
        for (String roleName : roles) {
            Perfil role = perfilRepository.findByNome(roleName);
            if (role != null) {
                perfilUsuario.add(role);
            }
        }
        user.setPerfis(perfilUsuario);

        Cliente cliente = new Cliente(dto);
        cliente.setUsuario(user);

        var ramo = new Ramo(dto);
        cliente.setRamo(ramo);
        ramo.getCliente().add(cliente);

        var enderecoCliente = new EnderecoCliente(dto);
        var logradouro = new Logradouro(dto);
        var bairro = new Bairro(dto);
        var cidade = new Cidade(dto);
        var estado = new Estado(dto);
        var pais = new Pais(dto);

        enderecoCliente.setCliente(cliente);
        enderecoCliente.setLogradouro(logradouro);

        cliente.getEnderecoClientes().add(enderecoCliente);
        logradouro.getEnderecoClientes().add(enderecoCliente);

        logradouro.setBairro(bairro);
        bairro.getLogradouros().add(logradouro);

        bairro.setCidade(cidade);
        cidade.getBairros().add(bairro);

        cidade.setEstado(estado);
        estado.getCidades().add(cidade);

        estado.setPais(pais);
        pais.getEstados().add(estado);

        clienteRepository.save(cliente);
    }

    public Usuario pacienteAutenticado(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.getPrincipal() instanceof UserDetails){
            String username = ((UserDetails) auth.getPrincipal()).getUsername();
            return usuarioRepository.findByEmail(username);
        }
        throw new IllegalStateException("Usuário não autenticado ou inválido");
    }

}
