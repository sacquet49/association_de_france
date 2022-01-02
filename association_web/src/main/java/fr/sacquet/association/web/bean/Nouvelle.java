package fr.sacquet.association.web.bean;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "nouvelle")
@Data
public class Nouvelle {

    @SequenceGenerator(name = "nouvelle_id_seq",
            sequenceName = "nouvelle_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="nouvelle_id_seq")
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "description")
    private String description;

    @Column(name = "titre")
    private String titre;
}
