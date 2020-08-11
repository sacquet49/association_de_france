#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "$0" )" && pwd )"

generateCertificat () {
	openssl req -x509 -sha256 -nodes -days 365 -newkey rsa:2048 -keyout ./nginx/certs/privateKey.key -out ./nginx/certs/certificate.crt \
	-subj "/C=FR/ST=Nantes/L=Nantes/O=Global Security/OU=IT Department/CN=localhost"
}

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
  wget https://www.data.gouv.fr/fr/datasets/repertoire-national-des-associations/
  RNA_IMPORT=$(grep "<article id=" index.html | sed 's|<article id="resource-||g' | sed 's|" class="card resource-card "||g' | sed -n '1p')
  RNA_WALDEC=$(grep "<article id=" index.html | sed 's|<article id="resource-||g' | sed 's|" class="card resource-card "||g' | sed -n '2p')
	wget -O $SCRIPT_DIR/import-association/import/rna_import_.zip https://www.data.gouv.fr/fr/datasets/r/$RNA_IMPORT
	wget -O $SCRIPT_DIR/import-association/import/rna_waldec_.zip https://www.data.gouv.fr/fr/datasets/r/$RNA_WALDEC
}

date
generateCertificat
date
compilation
date
deplacementFichierPhp
date
downloadZipAssociationFile
date
cd $SCRIPT_DIR
docker-compose up --build
