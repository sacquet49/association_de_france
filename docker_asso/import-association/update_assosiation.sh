#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "$0" )" && pwd )"

ASSO_INSTALL=$SCRIPT_DIR/integrationFichierCsv
CONFIG=$SCRIPT_DIR/import/association.properties
JAVA_BINARY=$1

usage () {
        echo "Usage: nohup $0 <association_update.zip path>"
}

if [ -z "$1" ]; then
        usage
        exit 1
fi

installEnv() {
  apt-get update -y
  apt-get install -y libpq-dev openjdk-17-jre unzip netcat
}

performIntegrationAssociation () {
	{
		bash -x $ASSO_INSTALL/integrationFichierCsv/integrationFichierCsv_run.sh --context_param properties_filepath=$CONFIG || RESULT=$?
	} >&2
	if [ -z "$RESULT" ] ; then
		echo 0
	else
		echo $RESULT
	fi
}

unzipBinairy () {
	rm -rf $ASSO_INSTALL
	rm -rf $SCRIPT_DIR/rna_import
	rm -rf $SCRIPT_DIR/rna_waldec
	unzip $JAVA_BINARY -d $ASSO_INSTALL
	unzip $SCRIPT_DIR/import/rna_import_*.zip -d $SCRIPT_DIR/rna_import
	unzip $SCRIPT_DIR/import/rna_waldec_*.zip -d $SCRIPT_DIR/rna_waldec
}

checkError () {
  cat $SCRIPT_DIR/rna_import/*.rejet
  cat $SCRIPT_DIR/rna_waldec/*.rejet
}

runindexer () {
  cd $SCRIPT_DIR/indexer
  java -jar elastic_indexer.jar --spring.config.location=application.properties
}

date
installEnv
date
while ! nc -z asso-db 5432; do
	sleep 60
done
while ! nc -z es 9200; do
	sleep 60
done
date
unzipBinairy
date
performIntegrationAssociation
date
checkError
date
runindexer
date
