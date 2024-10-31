package br.com.fiap.plusoft.challenge.java.controller;

import br.com.fiap.plusoft.challenge.java.model.usuario.Usuario;
import br.com.fiap.plusoft.challenge.java.model.usuario.dto.UsuarioLoginDTO;
import br.com.fiap.plusoft.challenge.java.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginLogoutController {

    @Autowired
    private UsuarioService service;

    @GetMapping("login")
    public String login(Model model){
        model.addAttribute("usuario", new UsuarioLoginDTO());
        return "cliente/login";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        service.logout(request, response);
        return "redirect:/login";
    }

    @GetMapping("principal")
    public String home(){
        return "index";
    }

}
