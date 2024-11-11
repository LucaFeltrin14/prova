package br.Insper.ProvaFinal.Artigos.repository;

import br.Insper.ProvaFinal.Artigos.model.Artigo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtigoRepository extends MongoRepository<Artigo, String> {

}
