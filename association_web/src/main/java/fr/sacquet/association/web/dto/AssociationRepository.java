package fr.sacquet.association.web.dto;

import fr.sacquet.association.web.bean.Association;
import fr.sacquet.association.web.model.AssociationStat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociationRepository extends CrudRepository<Association, String> {

    @Query(value = "SELECT new fr.sacquet.association.web.model.AssociationStat(SUBSTRING(a.adrsCodepostal,1,2), count(a)) " +
            "FROM Association a " +
            "WHERE SUBSTRING(a.adrsCodepostal,1,2) IS NOT NULL " +
            "AND SUBSTRING(a.adrsCodepostal,1,2) NOT IN ('', '**', '0', '00', '-1', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'AU', 'C.', 'In', 'L ', 'PO') " +
            "GROUP BY SUBSTRING(a.adrsCodepostal,1,2) " +
            "ORDER BY SUBSTRING(a.adrsCodepostal,1,2) ")
    List<AssociationStat> statAssociation();
}
