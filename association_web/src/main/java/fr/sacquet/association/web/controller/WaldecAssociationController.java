package fr.sacquet.association.web.controller;


import fr.sacquet.association.web.bean.WaldecAssociation;
import fr.sacquet.association.web.bean.WaldecAssociationStat;
import fr.sacquet.association.web.services.WaldecAssociationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static fr.sacquet.association.web.conf.Constante.PRIVATE_API;
import static fr.sacquet.association.web.conf.Constante.PUBLIC_API;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class WaldecAssociationController {

    private WaldecAssociationService service;

    @GetMapping(value = PUBLIC_API + "/association_waldec/{id}")
    public WaldecAssociation getWaldecAssociation(@PathVariable String id) {
        return service.getWaldecAssociation(id);
    }

    @GetMapping(value = PRIVATE_API + "/association_waldec/statistique")
    public Iterable<WaldecAssociationStat> getAllStatistique() {
        return service.statWaldecAssociation();
    }
}
