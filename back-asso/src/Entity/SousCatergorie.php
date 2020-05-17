<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * SousCatergorie
 *
 * @ORM\Table(name="sous_catergorie")
 * @ORM\Entity
 */
class SousCatergorie
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="bigint", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="SEQUENCE")
     * @ORM\SequenceGenerator(sequenceName="sous_catergorie_id_seq", allocationSize=1, initialValue=1)
     */
    private $id;

    /**
     * @var string|null
     *
     * @ORM\Column(name="titre", type="string", nullable=true)
     */
    private $titre;

    /**
     * @var int|null
     *
     * @ORM\Column(name="id_cat", type="bigint", nullable=true)
     */
    private $idCat;

    public function getId() {
        return $this->id;
    }

    public function getTitre() {
        return $this->titre;
    }

    public function getIdCat() {
        return $this->idCat;
    }

    public function setId($id) {
        $this->id = $id;
    }

    public function setTitre($titre) {
        $this->titre = $titre;
    }

    public function setIdCat($idCat) {
        $this->idCat = $idCat;
    }
}
