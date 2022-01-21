package fr.sacquet.association.web.controller;

import fr.sacquet.association.web.bean.Association;
import fr.sacquet.association.web.model.AssociationStat;
import fr.sacquet.association.web.services.AssociationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static fr.sacquet.association.web.conf.Constante.PRIVATE_API;
import static fr.sacquet.association.web.conf.Constante.PUBLIC_API;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class AssociationController {

    private AssociationService service;

    @GetMapping(value = PUBLIC_API + "/association/{id}")
    public Association getAssociation(@PathVariable String id) {
        return service.getAssociation(id);
    }

    @GetMapping(value = PRIVATE_API + "/association/statistique")
    public List<AssociationStat> getAllStatistique() {
        return service.statAssociation();
    }
}
