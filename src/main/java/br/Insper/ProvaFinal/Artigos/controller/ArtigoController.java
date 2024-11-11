package br.Insper.ProvaFinal.Artigos.controller;

import br.Insper.ProvaFinal.Artigos.model.Artigo;
import br.Insper.ProvaFinal.Artigos.service.ArtigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArtigoController {

    @Autowired
    private ArtigoService artigoService;

    @PostMapping
    public Artigo criarArtigo(@RequestHeader("Authorization") String token, @RequestBody Artigo artigo) {
        return artigoService.criarArtigo(token, artigo);
    }

    @DeleteMapping("/{id}")
    public void deletarArtigo(@RequestHeader("Authorization") String token, @PathVariable String id) {
        artigoService.deletarArtigo(token, id);
    }

    @GetMapping
    public List<Artigo> listarTodosOsArtigos(@RequestHeader("Authorization") String token) {
        return artigoService.listarTodosOsArtigos(token);
    }

    @GetMapping("/{id}")
    public Artigo buscarArtigoPorId(@RequestHeader("Authorization") String token, @PathVariable String id) {
        return artigoService.buscarArtigoPorId(token, id);
    }
}