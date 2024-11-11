package br.Insper.ProvaFinal.Artigos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private String nome;
    private String cpf;
    private String email;
    private String password;
    private String papel;
}
