package br.com.fiap.plusoft.challenge.java.service;

import br.com.fiap.plusoft.challenge.java.model.figuraPublica.FiguraPublica;
import br.com.fiap.plusoft.challenge.java.model.pesquisa.Pesquisa;
import br.com.fiap.plusoft.challenge.java.model.pesquisa.dto.CriarPesquisaDTO;
import br.com.fiap.plusoft.challenge.java.model.usuario.Usuario;
import br.com.fiap.plusoft.challenge.java.repository.PesquisaRepository;
import br.com.fiap.plusoft.challenge.java.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Service
public class PesquisaService {

    @Autowired
    private PesquisaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String criarPesquisa(CriarPesquisaDTO dto, RedirectAttributes redirectAttributes){
        Pesquisa pesquisa = new Pesquisa(dto);
        FiguraPublica figuraPublica = new FiguraPublica(dto);
        pesquisa.getFiguraPublica().add(figuraPublica);
        pesquisa.setUsuario(usuarioAutenticado());

        redirectAttributes.addFlashAttribute("msg", "Pesquisa criada com sucesso!");
        repository.save(pesquisa);
        return "redirect:/clientes/principal";
    }

    public List<Pesquisa> todasPesquisas(){
        return repository.findAllByUsuarioEmail(usuarioAutenticado().getEmail());
    }

    private Usuario usuarioAutenticado(){
        var usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepository.findByEmail(usuario);
    }
}
