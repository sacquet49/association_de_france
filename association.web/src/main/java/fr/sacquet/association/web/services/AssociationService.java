package fr.sacquet.association.web.services;

import fr.sacquet.association.web.bean.Association;
import fr.sacquet.association.web.dto.AssociationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AssociationService {

    private AssociationRepository repository;

    public Association getAssociation(String id) {
        return repository.findById(id).get();
    }
}
