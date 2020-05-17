<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Statistique
 *
 * @ORM\Table(name="statistique")
 * @ORM\Entity
 */
class Statistique
{
    /**
     * @var string
     *
     * @ORM\Column(name="id", type="bigint", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="SEQUENCE")
     * @ORM\SequenceGenerator(sequenceName="seq_statistique", allocationSize=1, initialValue=1)
     */
    private $id;

    /**
     * @ORM\Column(name="departement", type="bigint")
     */
    private $departement;

    /**
     * @ORM\Column(name="nombre_association", type="bigint")
     */
    private $nombreAssociation;

    /**
     * @return string
     */
    public function getId(): string
    {
        return $this->id;
    }

    /**
     * @param string $id
     */
    public function setId(string $id): void
    {
        $this->id = $id;
    }

    /**
     * @return mixed
     */
    public function getDepartement()
    {
        return $this->departement;
    }

    /**
     * @param mixed $departement
     */
    public function setDepartement($departement): void
    {
        $this->departement = $departement;
    }

    /**
     * @return mixed
     */
    public function getNombreAssociation()
    {
        return $this->nombreAssociation;
    }

    /**
     * @param mixed $nombreAssociation
     */
    public function setNombreAssociation($nombreAssociation): void
    {
        $this->nombreAssociation = $nombreAssociation;
    }


}
