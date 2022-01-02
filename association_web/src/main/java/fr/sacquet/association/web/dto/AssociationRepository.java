package fr.sacquet.association.web.dto;

import fr.sacquet.association.web.bean.Association;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociationRepository extends CrudRepository<Association, String> {
}
