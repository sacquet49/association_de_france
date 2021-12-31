package fr.sacquet.association.web.bean;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "catergorie")
@Data
public class Categorie {

    @SequenceGenerator(name = "catergorie_id_seq",
            sequenceName = "catergorie_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="catergorie_id_seq")
    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "titre")
    private String titre;
}
