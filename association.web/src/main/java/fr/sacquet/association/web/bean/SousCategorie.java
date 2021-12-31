package fr.sacquet.association.web.bean;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sous_catergorie")
@Data
public class SousCategorie {

    @SequenceGenerator(name = "sous_catergorie_id_seq",
            sequenceName = "sous_catergorie_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="sous_catergorie_id_seq")
    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "titre")
    private String titre;
    
    @Column(name = "id_cat")
    private Long idCat;
}
