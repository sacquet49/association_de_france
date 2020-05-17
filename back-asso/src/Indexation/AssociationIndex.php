<?php

namespace App\Indexation;

use App\Repository\AssociationRepository;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;

class AssociationIndex extends Indexer
{
    private $associationRepo;

    public function __construct(AssociationRepository $associationRepo,
                                AssociationClient $client,
                                UrlGeneratorInterface $router)
    {
        parent::__construct($client, $router);
        $this->associationRepo = $associationRepo;
    }

    public function indexAllAssociation() {
        $allAssociation = $this->associationRepo->findAllAssociation();
        $this->indexAll('associations', $allAssociation);
    }
}