package fr.sacquet.association.web.controller;


import fr.sacquet.association.web.bean.WaldecAssociation;
import fr.sacquet.association.web.services.WaldecAssociationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open/api/association_waldec")
@AllArgsConstructor
public class WaldecAssociationController {

    private WaldecAssociationService service;

    @GetMapping(value = "/{id}")
    public WaldecAssociation getWaldecAssociation(@PathVariable String id) {
        return service.getWaldecAssociation(id);
    }
}
