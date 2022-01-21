package fr.sacquet.association.web.services;

import fr.sacquet.association.web.bean.WaldecAssociation;
import fr.sacquet.association.web.bean.WaldecAssociationStat;
import fr.sacquet.association.web.dto.WaldecAssociationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WaldecAssociationService {

    private WaldecAssociationRepository repository;

    public WaldecAssociation getWaldecAssociation(String id) {
        Optional<WaldecAssociation> opAsso = repository.findById(id);
        if (opAsso.isPresent()) {
            return opAsso.get();
        } else {
            return new WaldecAssociation();
        }
    }

    public List<WaldecAssociationStat> statWaldecAssociation() {
        return repository.statWaldecAssociation();
    }
}
