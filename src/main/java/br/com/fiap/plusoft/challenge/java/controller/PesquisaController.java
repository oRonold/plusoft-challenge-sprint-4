package br.com.fiap.plusoft.challenge.java.controller;

import br.com.fiap.plusoft.challenge.java.model.pesquisa.Pesquisa;
import br.com.fiap.plusoft.challenge.java.model.pesquisa.dto.CriarPesquisaDTO;
import br.com.fiap.plusoft.challenge.java.service.PesquisaService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pesquisas")
public class PesquisaController {

    @Autowired
    private PesquisaService service;

    @GetMapping("cadastrar")
    public String criarPesquisa(Model model){
        model.addAttribute("pesquisa", new CriarPesquisaDTO());
        return "pesquisa/cadastrar";
    }

    @PostMapping("cadastrar")
    @Transactional
    public String novaPesquisa(@ModelAttribute @Valid CriarPesquisaDTO dto, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "pesquisa/cadastrar";
        }
        return service.criarPesquisa(dto, redirectAttributes);
    }
}