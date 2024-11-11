package br.Insper.ProvaFinal.Carros.repository;

import br.Insper.ProvaFinal.Carros.model.Carro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends MongoRepository<Carro, String> {

}
