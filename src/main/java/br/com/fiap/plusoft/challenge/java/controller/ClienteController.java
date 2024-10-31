package br.com.fiap.plusoft.challenge.java.controller;

import br.com.fiap.plusoft.challenge.java.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.plusoft.challenge.java.model.usuario.dto.UsuarioLoginDTO;
import br.com.fiap.plusoft.challenge.java.repository.PerfilRepository;
import br.com.fiap.plusoft.challenge.java.service.ClienteService;
import br.com.fiap.plusoft.challenge.java.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping("principal")
    public String home(){
        return "index";
    }

    @GetMapping("cadastrar")
    public String retornarFormularioCliente(Model model){
        model.addAttribute("usuario", new CadastrarClienteDTO());
        model.addAttribute("perfil", perfilRepository.findAll());
        return "cliente/registrar";
    }

    @PostMapping("cadastrar")
    @Transactional
    public String cadastrarCliente(@ModelAttribute @Valid CadastrarClienteDTO dto, BindingResult result){
        if(result.hasErrors()){
            return "cliente/registrar";
        }
        usuarioService.salvarUsuario(dto, dto.getPerfil());
        return "redirect:/login";
    }

    @GetMapping("detalhes")
    public String detalhes(Model model){
        var cliente = clienteService.detalhesConta();
        model.addAttribute("cliente", cliente);
        model.addAttribute("enderecoCliente", cliente.getEnderecoClientes());
        return "cliente/detalhes-conta";
    }

}
