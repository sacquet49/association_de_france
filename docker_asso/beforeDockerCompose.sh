#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "$0" )" && pwd )"

installNpm() {
  curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.35.2/install.sh | bash
  export NVM_DIR="$HOME/.nvm"
  [ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"
  [ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"
  command -v nvm
  nvm ls-remote --lts
  nvm install v12.14.1
}

compilationAngular () {
  cd $SCRIPT_DIR/../angular
  npm i
  npm run build
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
