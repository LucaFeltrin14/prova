package br.Insper.ProvaFinal.Carros.controller;

import br.Insper.ProvaFinal.Carros.model.Carro;
import br.Insper.ProvaFinal.Carros.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @PostMapping
    public Carro criarCarro(@RequestBody Carro carro) {
        return carroService.criarCarro(carro);
    }

    @PostMapping("/{carroId}")
    public Carro registrarCompra(@PathVariable String carroId, @RequestHeader("Authorization") String token) {
        return carroService.registrarCarro(token, carroId);
    }

    @GetMapping("/{carroId}")
    public Carro buscarCarroPorId(@PathVariable String carroId, @RequestHeader("Authorization") String token) {
        return carroService.buscarCarroPorId(token, carroId);
    }

    @GetMapping
    public List<Carro> listarTodosOsCarros() {
        return carroService.listarTodosOsCarros();
    }
}