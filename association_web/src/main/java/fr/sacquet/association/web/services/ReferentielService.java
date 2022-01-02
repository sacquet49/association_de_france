package fr.sacquet.association.web.services;

import fr.sacquet.association.web.bean.Categorie;
import fr.sacquet.association.web.dto.CategorieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReferentielService {

    private CategorieRepository repository;

    public Iterable<Categorie> getAllCategorie() {
        return repository.findAll();
    }
}
