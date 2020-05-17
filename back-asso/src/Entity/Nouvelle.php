<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Nouvelle
 *
 * @ORM\Table(name="nouvelle")
 * @ORM\Entity
 */
class Nouvelle
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="bigint", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="SEQUENCE")
     * @ORM\SequenceGenerator(sequenceName="nouvelle_id_seq", allocationSize=1, initialValue=1)
     */
    private $id;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="date_creation", type="date", nullable=true)
     */
    private $dateCreation;

    /**
     * @var string|null
     *
     * @ORM\Column(name="description", type="string", nullable=true)
     */
    private $description;

    /**
     * @var string|null
     *
     * @ORM\Column(name="titre", type="string", nullable=true)
     */
    private $titre;
    
    public function getId() {
        return $this->id;
    }

    public function getDateCreation(): \DateTime {
        return $this->dateCreation;
    }

    public function getDescription() {
        return $this->description;
    }

    public function getTitre() {
        return $this->titre;
    }

    public function setId($id) {
        $this->id = $id;
    }

    public function setDateCreation(\DateTime $dateCreation) {
        $this->dateCreation = $dateCreation;
    }

    public function setDescription($description) {
        $this->description = $description;
    }

    public function setTitre($titre) {
        $this->titre = $titre;
    }
}
