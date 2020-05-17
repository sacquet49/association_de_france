<?php

namespace App\Command;

use App\Indexation\AssociationIndex;
use Symfony\Component\Console\Command\Command;
use Symfony\Component\Console\Input\InputInterface;
use Symfony\Component\Console\Output\OutputInterface;
use Symfony\Component\Console\Style\SymfonyStyle;

class ElasticReindexCommand extends Command
{
    protected static $defaultName = 'elastic:reindex';

    private $associationIndex;

    public function __construct(AssociationIndex $associationIndex)
    {
        $this->associationIndex = $associationIndex;
        parent::__construct();
    }

    protected function configure()
    {
        $this->setDescription('Rebuild the Index and populate it.');
    }

    protected function execute(InputInterface $input, OutputInterface $output)
    {
        $io = new SymfonyStyle($input, $output);

        $this->associationIndex->indexAllAssociation();
        $io->success('Index association populated and ready!');
    }
}
