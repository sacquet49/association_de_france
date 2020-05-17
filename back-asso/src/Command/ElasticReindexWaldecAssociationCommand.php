<?php

namespace App\Command;

use App\Indexation\IndexBuilder;
use App\Indexation\WaldecAssociationIndex;
use Symfony\Component\Console\Command\Command;
use Symfony\Component\Console\Input\InputInterface;
use Symfony\Component\Console\Output\OutputInterface;
use Symfony\Component\Console\Style\SymfonyStyle;

class ElasticReindexWaldecAssociationCommand extends Command
{
    protected static $defaultName = 'elastic:reindex:waldec:association';

    private $waldecAssociationIndex;

    public function __construct(WaldecAssociationIndex $waldecAssociationIndex)
    {
        $this->waldecAssociationIndex = $waldecAssociationIndex;
        parent::__construct();
    }

    protected function configure()
    {
        $this->setDescription('Rebuild the Index and populate it.');
    }

    protected function execute(InputInterface $input, OutputInterface $output)
    {
        $io = new SymfonyStyle($input, $output);

        $this->waldecAssociationIndex->indexAllAssociation();
        $io->success('Index association populated and ready!');
    }
}
