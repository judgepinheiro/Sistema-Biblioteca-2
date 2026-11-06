
package com.Biblioteca.controller;

import com.Biblioteca.model.Autor;
import com.Biblioteca.repository.AutorRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AutorController {

    private final AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @GetMapping("/autores/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("autor", new Autor());
        return "Autor/cadastro-autor";
    }

    @PostMapping("/autores/salvar")
    public String salvarAutor(@ModelAttribute Autor autor) {
        autorRepository.save(autor);

        return "redirect:/autores";
    }

    @GetMapping("/autores")
    public String listarAutores(Model model) {
        model.addAttribute("autores", autorRepository.findAll());

        return "Autor/lista-autores";
    }

    @PostMapping("/autores/excluir/{id}")
    public String excluirAutor(@PathVariable Long id) {
        autorRepository.deleteById(id);

        return "redirect:/autores";
    }
}
