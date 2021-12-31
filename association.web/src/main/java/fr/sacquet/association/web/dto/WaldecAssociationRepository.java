package fr.sacquet.association.web.dto;

import fr.sacquet.association.web.bean.WaldecAssociation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaldecAssociationRepository extends CrudRepository<WaldecAssociation, String> {
}
