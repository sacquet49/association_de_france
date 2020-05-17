<?php

namespace App\Indexation;

use Elastica\Exception\InvalidException;
use Elastica\Index;
use JoliCode\Elastically\Client;
use JoliCode\Elastically\IndexBuilder;
use Symfony\Component\Yaml\Yaml;

class AssociationIndexBuilder extends IndexBuilder
{
    private $client;
    private $configurationDirectory;

    public function __construct(Client $client, $configurationDirectory)
    {
        $this->client = $client;
        $this->configurationDirectory = $configurationDirectory;
    }

    public function createIndex($indexName): Index
    {
        $mappingFilePath = $this->configurationDirectory.DIRECTORY_SEPARATOR.$indexName.'_mapping.yaml';
        if (!is_file($mappingFilePath)) {
            throw new InvalidException(sprintf('Mapping file "%s" not found.', $mappingFilePath));
        }
        $mapping = Yaml::parseFile($mappingFilePath);

        $analyzerFilePath = $this->configurationDirectory.'/analyzers.yaml';
        if ($mapping && is_file($analyzerFilePath)) {
            $analyzer = Yaml::parseFile($analyzerFilePath);
            $mapping['settings']['analysis'] = array_merge_recursive($mapping['settings']['analysis'] ?? [], $analyzer);
        }

        $index = $this->client->getIndex($indexName);

        if (!$index->exists()) {
            $index->create($mapping ?? []);
        }
        return $index;
    }
}