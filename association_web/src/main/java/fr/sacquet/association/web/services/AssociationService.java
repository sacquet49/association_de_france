package fr.sacquet.association.web.services;

import fr.sacquet.association.web.bean.Association;
import fr.sacquet.association.web.dto.AssociationRepository;
import fr.sacquet.association.web.model.AssociationStat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AssociationService {

    private AssociationRepository repository;

    public Association getAssociation(String id) {
        Optional<Association> opAsso = repository.findById(id);
        if (opAsso.isPresent()) {
            return opAsso.get();
        } else {
            return new Association();
        }
    }

    public List<AssociationStat> statAssociation() {
        return repository.statAssociation();
    }
}
