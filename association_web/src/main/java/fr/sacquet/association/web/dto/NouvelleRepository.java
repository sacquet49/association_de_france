package fr.sacquet.association.web.dto;

import fr.sacquet.association.web.bean.Nouvelle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NouvelleRepository extends CrudRepository<Nouvelle, Long> {
}
