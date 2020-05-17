<?php

namespace App\Repository;

use App\Entity\WaldecAssociation;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

class WaldecAssociationRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, WaldecAssociation::class);
    }

    public function findAssociationByCriteria($codesPostaux, $nom, $categorie): array
    {
        $qb = $this->getEntityManager()->createQueryBuilder();
        $qb->select('a.id', 'a.titre', 'a.adrsLibcommune AS adrs_libcommune', 'a.adrsNumvoie AS adrs_numvoie'
            , 'a.adrsTypevoie AS adrs_typevoie', 'a.adrsLibvoie AS adrs_libvoie')
            ->from('App:WaldecAssociation', 'a');

        if ($codesPostaux !== null && !empty($codesPostaux) && $codesPostaux !== 'undefined') {
            $codesPostaux = explode(",", $codesPostaux);
            $qb->andWhere('a.adrsCodepostal in (:codesPostaux)')
                ->setParameter('codesPostaux', $codesPostaux);
        }

        if ($nom !== null && !empty($nom) && $nom !== 'undefined') {
            $qb->andWhere('lower(a.titre) like :nom')
                ->setParameter('nom', '%' . strtolower($nom) . '%');
        }

        if ($categorie !== null && !empty($categorie) && $categorie !== 'undefined') {
            if (strlen($categorie) === 5) {
                $categorie = '0' . $categorie;
            }

            if (strlen($categorie) === 4) {
                $categorie = '00' . $categorie;
            }

            $categorie = substr($categorie, 0, -2);

            $qb->andWhere(
                $qb->expr()->orX(
                    $qb->expr()->like('a.objetSocial1', ':categorie'),
                    $qb->expr()->like('a.objetSocial2', ':categorie2'))
            )->setParameter('categorie', $categorie . '%')
                ->setParameter('categorie2', $categorie . '%');
        }

        return $qb->orderBy('a.titre', 'ASC')
            ->setMaxResults(250)
            ->getQuery()
            ->getResult();
    }

    public function statAssociation(): array
    {
        $qb = $this->getEntityManager()->createQueryBuilder();
        $qb->select( $qb->expr()->substring('a.adrsCodepostal', 1, 2).' AS cpg', 'COUNT(a) AS nb')
            ->from('App:WaldecAssociation', 'a')
            ->where($qb->expr()->isNotNull('a.adrsCodepostal'))
            ->distinct()
            ->orderBy('cpg')
            ->groupBy('cpg');

        return $qb->getQuery()->getResult();
    }

    public function findAllWaldecAssociation(): array
    {
        return $this->getEntityManager()
            ->createQueryBuilder()
            ->select('NEW App:WaldecAssociation(a.id, a.titre, a.objet, a.adrsCodepostal, a.adrsLibvoie,
             a.adrsLibcommune, a.adrgCodepostal, a.adrgLibvoie, a.adrsNumvoie, a.adrsTypevoie)')
            ->from('App:WaldecAssociation', 'a')->getQuery()->getResult();
    }
}