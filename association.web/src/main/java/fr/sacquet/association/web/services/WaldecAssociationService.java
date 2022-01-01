package fr.sacquet.association.web.services;

import fr.sacquet.association.web.bean.WaldecAssociation;
import fr.sacquet.association.web.dto.WaldecAssociationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WaldecAssociationService {

    private WaldecAssociationRepository repository;

    public WaldecAssociation getWaldecAssociation(String id) {
       return repository.findById(id).get();
    }
}
