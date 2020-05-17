<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Catergorie
 *
 * @ORM\Table(name="catergorie")
 * @ORM\Entity
 */
class Catergorie
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="bigint", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="SEQUENCE")
     * @ORM\SequenceGenerator(sequenceName="catergorie_id_seq", allocationSize=1, initialValue=1)
     */
    private $id;

    /**
     * @var string|null
     *
     * @ORM\Column(name="titre", type="string", nullable=true)
     */
    private $titre;
    
    public function getId() {
        return $this->id;
    }

    public function getTitre() {
        return $this->titre;
    }

    public function setId($id) {
        $this->id = $id;
    }

    public function setTitre($titre) {
        $this->titre = $titre;
    }



}
