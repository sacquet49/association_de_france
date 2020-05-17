#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "$0" )" && pwd )"

echo $SCRIPT_DIR
cd $SCRIPT_DIR/../angular
npm i
npm run build
cp -rf $SCRIPT_DIR/../angular/dist/* $SCRIPT_DIR/nginx/front/public

cp -rf $SCRIPT_DIR/../back-asso/bin $SCRIPT_DIR/php/back-asso
cp -rf $SCRIPT_DIR/../back-asso/config $SCRIPT_DIR/php/back-asso
cp -rf $SCRIPT_DIR/../back-asso/public $SCRIPT_DIR/php/back-asso
cp -rf $SCRIPT_DIR/../back-asso/src $SCRIPT_DIR/php/back-asso
cp -f $SCRIPT_DIR/../back-asso/composer.json $SCRIPT_DIR/php/back-asso
cp -f $SCRIPT_DIR/../back-asso/composer.lock $SCRIPT_DIR/php/back-asso
cp -f $SCRIPT_DIR/../back-asso/symfony.lock $SCRIPT_DIR/php/back-asso
cp -f $SCRIPT_DIR/php/.env $SCRIPT_DIR/php/back-asso


cp -rf $SCRIPT_DIR/../indexer_python/* $SCRIPT_DIR/import-association/indexer
cp -rf $SCRIPT_DIR/import-association/conf.json $SCRIPT_DIR/import-association/indexer
