<?php

namespace App\Indexation;

use JoliCode\Elastically\Client;
use JoliCode\Elastically\IndexBuilder;

class AssociationClient extends Client
{
    private $indexBuilder;

    public function getIndexBuilder(): IndexBuilder
    {
        if (!$this->indexBuilder) {
            $this->indexBuilder = new AssociationIndexBuilder($this, $this->getConfig(self::CONFIG_MAPPINGS_DIRECTORY));
        }
        return $this->indexBuilder;
    }
}