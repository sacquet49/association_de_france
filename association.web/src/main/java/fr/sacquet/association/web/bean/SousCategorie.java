package fr.sacquet.association.web.bean;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sous_catergorie")
@Data
public class SousCategorie {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "titre")
    private String titre;
    
    @Column(name = "id_cat")
    private Long idCat;
}
