package br.Insper.ProvaFinal.Carros.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "carros")
@Getter
@Setter
public class Carro {
    @MongoId
    private String id;
    private String marca;
    private String modelo;
    private String cor;
    private Integer ano;
    private String comprador_cpf;
    private String comprador_email;
}
