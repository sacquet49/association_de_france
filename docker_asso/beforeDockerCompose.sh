#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "$0" )" && pwd )"

installNpm() {
  if [ ! -d ~/.nvm ]; then
	  curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.35.2/install.sh | bash
	  export NVM_DIR="$HOME/.nvm"
	  [ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"
	  [ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"
	  command -v nvm
	  nvm ls-remote --lts
	  nvm install v12.14.1
  fi
}

compilationAngular () {
  cd $SCRIPT_DIR/../angular
  npm i
  npm i --save-dev typescript@3.8.3
  npm run build
  mkdir -p $SCRIPT_DIR/nginx/front/public
  cp -rf $SCRIPT_DIR/../angular/dist/* $SCRIPT_DIR/nginx/front/public
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

deplacementIndexerPython () {
  cp -rf $SCRIPT_DIR/../indexer_python/* $SCRIPT_DIR/import-association/indexer
  cp -rf $SCRIPT_DIR/import-association/conf.json $SCRIPT_DIR/import-association/indexer
}

downloadZipAssociationFile () {
	wget -O $SCRIPT_DIR/import-association/import/rna_import_.zip https://www.data.gouv.fr/fr/datasets/r/e2ec0ffa-dbf0-4c0a-ae3e-a0e76a6dec63
	wget -O $SCRIPT_DIR/import-association/import/rna_waldec_.zip https://www.data.gouv.fr/fr/datasets/r/8c338cff-561e-4bbe-8973-7636a00282cc
}

date
echo $SCRIPT_DIR
date
installNpm
date
compilationAngular
date
deplacementFichierPhp
date
deplacementIndexerPython
date
downloadZipAssociationFile
date

