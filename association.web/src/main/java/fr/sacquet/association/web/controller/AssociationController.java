package fr.sacquet.association.web.controller;

import fr.sacquet.association.web.bean.Association;
import fr.sacquet.association.web.services.AssociationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open/api/association")
@AllArgsConstructor
public class AssociationController {

    private AssociationService service;

    @GetMapping(value = "/{id}")
    public Association getAssociation(@PathVariable String id) {
        return service.getAssociation(id);
    }
}
