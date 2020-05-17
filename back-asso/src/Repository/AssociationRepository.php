<?php

namespace App\Repository;

use App\Entity\Association;
use App\Entity\AssociationDTO;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;
use Doctrine\ORM\Query;

class AssociationRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Association::class);
    }

    public function findAssociationByCriteria($codesPostaux, $nom, $categorie): array
    {
        $qb = $this->getEntityManager()->createQueryBuilder();
        $qb->select('a.id', 'a.titre', 'a.libcom', 'a.adr1')
            ->from('App:Association', 'a');

        if($codesPostaux !== null && !empty($codesPostaux) && $codesPostaux !== 'undefined'){
            $qb->andWhere('a.adrsCodepostal in (:codesPostaux)')
                ->setParameter('codesPostaux', $codesPostaux);
        }

        if($nom !== null && !empty($nom) && $nom !== 'undefined'){
            $qb->andWhere('lower(a.titre) like :nom')
                ->setParameter('nom', '%'.strtolower($nom).'%');
        }

        if($categorie !== null && !empty($categorie) && $categorie !== 'undefined') {
            if (strlen($categorie) === 5) {
                $categorie = '0' . $categorie;
            }

            if (strlen($categorie) === 4) {
                $categorie = '00' . $categorie;
            }

            $categorie = substr($categorie, 0,-2);

            $qb->andWhere(
                $qb->expr()->orX(
                    $qb->expr()->like('a.objetSocial1',':categorie'),
                    $qb->expr()->like('a.objetSocial2', ':categorie2'))
            )->setParameter('categorie', $categorie.'%')
             ->setParameter('categorie2', $categorie.'%');
        }

        return $qb->orderBy('a.titre', 'ASC')
            ->setMaxResults( 250 )
            ->getQuery()
            ->getResult();
    }

    public function findAllAssociation(): array
    {
        return $this->getEntityManager()
            ->createQueryBuilder()
            ->select('NEW App:Association(a.id, a.titre, a.objet, a.adr1, a.adrsCodepostal, a.libcom)')
            ->from('App:Association', 'a')->getQuery()->getResult();
    }



}