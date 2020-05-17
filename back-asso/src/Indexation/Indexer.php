<?php

namespace App\Indexation;

use Elastica\Document;
use JMS\Serializer\SerializerBuilder;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;

abstract class Indexer
{
    protected $client;
    protected $router;
    protected $sizeOfIndex = 10000;
    protected $indexer;
    protected $serializer;

    public function __construct(AssociationClient $client,
                                UrlGeneratorInterface $router)    {
        $this->router = $router;
        $this->client = $client;
        $this->serializer = SerializerBuilder::create()->build();
        $this->indexer = $this->client->getIndexer();
    }

    public function buildDocument($index, $document)    {
        $this->indexer->scheduleIndex($index, new Document(
            $document->getId(), $this->serializer->serialize($document, 'json')
        ));
    }

    public function indexAll($indexName, $documents)    {
        $index = $this->client->getIndexBuilder()->createIndex($indexName);
        $loop = count($documents) / $this->sizeOfIndex;

        for ($i = 0; $i <= $loop; $i++) {
            $documentsPart = array_slice($documents, $i * $this->sizeOfIndex, ($i + 1) * $this->sizeOfIndex);
            if (!empty($documentsPart)) {
                foreach ($documentsPart as $document) {
                    $this->buildDocument($index, $document);
                }
            }
            $this->indexer->flush();
            echo date("Y-m-d H:i:s") . " : Indexation des documents index " . $indexName . " : " . ($i + 1) * $this->sizeOfIndex . "/" . ($loop * $this->sizeOfIndex) . "\n";
        }
    }
}