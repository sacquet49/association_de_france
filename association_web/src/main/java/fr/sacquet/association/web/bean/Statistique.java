package fr.sacquet.association.web.bean;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "statistique")
@Data
public class Statistique {

    @SequenceGenerator(name = "seq_statistique",
            sequenceName = "seq_statistique",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="seq_statistique")
    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "departement")
    private Long departement;
    
    @Column(name = "nombre_association")
    private Long nombreAssociation;
}
