package fr.sacquet.association.web.bean;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "statistique")
@Data
public class Statistique {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "departement")
    private Long departement;
    
    @Column(name = "nombre_association")
    private Long nombreAssociation;
}
