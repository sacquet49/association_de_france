#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "$0" )" && pwd )"

compilation () {
  cd $SCRIPT_DIR/..
  rm -f $SCRIPT_DIR/../angular/package-lock.json
  docker-compose up --build
  mkdir -p $SCRIPT_DIR/nginx/front/public
  cp -rf $SCRIPT_DIR/../angular/dist/* $SCRIPT_DIR/nginx/front/public
  cp -f $SCRIPT_DIR/../elastic/target/elastic-*.jar $SCRIPT_DIR/import-association/indexer/elastic_indexer.jar
}

deplacementFichierPhp () {
  cp -rf $SCRIPT_DIR/../back-asso/bin $SCRIPT_DIR/php/back-asso
  cp -rf $SCRIPT_DIR/../back-asso/config $SCRIPT_DIR/php/back-asso
  cp -rf $SCRIPT_DIR/../back-asso/public $SCRIPT_DIR/php/back-asso
  cp -rf $SCRIPT_DIR/../back-asso/src $SCRIPT_DIR/php/back-asso
  cp -f $SCRIPT_DIR/../back-asso/composer.json $SCRIPT_DIR/php/back-asso
  cp -f $SCRIPT_DIR/../back-asso/composer.lock $SCRIPT_DIR/php/back-asso
  cp -f $SCRIPT_DIR/../back-asso/symfony.lock $SCRIPT_DIR/php/back-asso
  cp -f $SCRIPT_DIR/php/.env $SCRIPT_DIR/php/back-asso
}

downloadZipAssociationFile () {
	wget -O $SCRIPT_DIR/import-association/import/rna_import_.zip https://www.data.gouv.fr/fr/datasets/r/e2ec0ffa-dbf0-4c0a-ae3e-a0e76a6dec63
	wget -O $SCRIPT_DIR/import-association/import/rna_waldec_.zip https://www.data.gouv.fr/fr/datasets/r/8c338cff-561e-4bbe-8973-7636a00282cc
}

date
echo $SCRIPT_DIR
date
compilation
date
deplacementFichierPhp
date
downloadZipAssociationFile
date
cd $SCRIPT_DIR
docker-compose up --build
