package fr.sacquet.association.web.controller;

import fr.sacquet.association.web.bean.Categorie;
import fr.sacquet.association.web.services.ReferentielService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static fr.sacquet.association.web.conf.Constante.PUBLIC_API;

@RestController
@RequestMapping(PUBLIC_API)
@AllArgsConstructor
public class ReferentielController {

    private ReferentielService service;

    @GetMapping(value = "/categories")
    public Iterable<Categorie> getWaldecAssociation() {
        return service.getAllCategorie();
    }
}
