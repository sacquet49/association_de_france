#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "$0" )" && pwd )"

SISAC_MIG_INSTALL=$SCRIPT_DIR/integrationFichierCsv
CONFIG=$SCRIPT_DIR/association.properties
JAVA_BINARY=$1

usage () {
        echo "Usage: nohup $0 <association_update.zip path>"
}

if [ -z "$1" ]; then
        usage
        exit 1
fi

installEnv() {
  sudo apt-get update -y
  sudo apt-get install -y python3 python3-pip libpq-dev oracle-java11-installer unzip
  sudo pip3 --yes install elasticsearch psycopg2
}

performIntegrationAssociation () {
	{
		bash -x $SISAC_MIG_INSTALL/integrationFichierCsv/integrationFichierCsv_run.sh --context_param properties_filepath=$CONFIG || RESULT=$?
	} >&2
	if [ -z "$RESULT" ] ; then
		echo 0
	else
		echo $RESULT
	fi
}

unzipBinairy () {
	rm -rf $SISAC_MIG_INSTALL
	rm -rf rejet_rma_import.csv
	rm -rf rejet_rna_waldec.csv
	rm -rf rna_import
	rm -rf rna_waldec
	unzip $JAVA_BINARY -d $SISAC_MIG_INSTALL
	unzip rna_import_*.zip -d rna_import
	unzip rna_waldec_*.zip -d rna_waldec
}

checkError () {
  cat rna_import/*.rejet
  cat rna_waldec/*.rejet
}

date
installEnv
unzipBinairy
performIntegrationAssociation
checkError
date



