<?php

namespace App\Indexation;

use App\Repository\WaldecAssociationRepository;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;

class WaldecAssociationIndex extends Indexer
{
    private $associationRepo;

    public function __construct(WaldecAssociationRepository $associationRepo,
                                AssociationClient $client,
                                UrlGeneratorInterface $router)
    {
        parent::__construct($client, $router);
        $this->associationRepo = $associationRepo;
    }

    public function indexAllAssociation()
    {
        $allAssociation = $this->associationRepo->findAllWaldecAssociation();
        $this->indexAll('waldec_association', $allAssociation);
    }
}