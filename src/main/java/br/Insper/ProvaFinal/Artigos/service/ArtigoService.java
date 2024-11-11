package br.Insper.ProvaFinal.Artigos.service;

import br.Insper.ProvaFinal.Artigos.model.Artigo;
import br.Insper.ProvaFinal.Artigos.repository.ArtigoRepository;
import br.Insper.ProvaFinal.Carros.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ArtigoService {

    @Autowired
    private ArtigoRepository artigoRepository;

    private static final String VALIDATION_URL = "http://184.72.80.215/usuario/validate";

    public Artigo criarArtigo(String token, Artigo artigo) {
        UsuarioDTO userInfo = validarToken(token);
        if (!"ADMIN".equals(userInfo.getPapel())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado. Apenas ADMIN pode criar artigos.");
        }
        return artigoRepository.save(artigo);
    }

    public void deletarArtigo(String token, String artigoId) {
        UsuarioDTO userInfo = validarToken(token);
        if (!"ADMIN".equals(userInfo.getPapel())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado. Apenas ADMIN pode deletar artigos.");
        }
        Artigo artigo = artigoRepository.findById(artigoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artigo não encontrado com ID: " + artigoId));
        artigoRepository.delete(artigo);
    }

    public List<Artigo> listarTodosOsArtigos(String token) {
        UsuarioDTO userInfo = validarToken(token);
        if (!"ADMIN".equals(userInfo.getPapel()) && !"DEVELOPER".equals(userInfo.getPapel())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado. Apenas ADMIN e DEVELOPERS podem listar artigos.");
        }
        return artigoRepository.findAll();
    }

    public Artigo buscarArtigoPorId(String token, String id) {
        UsuarioDTO userInfo = validarToken(token);
        if (!"ADMIN".equals(userInfo.getPapel()) && !"DEVELOPER".equals(userInfo.getPapel())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado. Apenas ADMIN e DEVELOPERS podem visualizar artigos.");
        }
        return artigoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artigo não encontrado com ID: " + id));
    }

    private UsuarioDTO validarToken(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<UsuarioDTO> response = restTemplate.exchange(
                    VALIDATION_URL,
                    HttpMethod.GET,
                    entity,
                    UsuarioDTO.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                UsuarioDTO userInfo = response.getBody();

                if (userInfo != null) {
                    return userInfo;
                } else {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Resposta da validação de token está vazia.");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido. Status code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Erro ao validar token: " + e.getMessage());
        }
    }
}