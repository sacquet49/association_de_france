package fr.sacquet.association.web.dto;

import fr.sacquet.association.web.bean.WaldecAssociation;
import fr.sacquet.association.web.model.AssociationStat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaldecAssociationRepository extends CrudRepository<WaldecAssociation, String> {

    @Query(value = "SELECT new fr.sacquet.association.web.model.AssociationStat(SUBSTRING(wa.adrsCodepostal,1,2), count(wa)) " +
            "FROM WaldecAssociation wa " +
            "WHERE SUBSTRING(wa.adrsCodepostal,1,2) IS NOT NULL AND SUBSTRING(wa.adrsCodepostal,1,2) != '' " +
            "GROUP BY SUBSTRING(wa.adrsCodepostal,1,2) " +
            "ORDER BY SUBSTRING(wa.adrsCodepostal,1,2) ")
    List<AssociationStat> statWaldecAssociation();
}
