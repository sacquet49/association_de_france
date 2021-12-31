package fr.sacquet.association.web.dto;

import fr.sacquet.association.web.bean.SousCategorie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SousCategorieRepository extends CrudRepository<SousCategorie, Long> {
}
