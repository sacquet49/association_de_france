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
  sudo apt-get update -y
  sudo apt install -y dirmngr
  sudo apt-key adv -y --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EA8CACC073C3DB2A
  echo "deb http://ppa.launchpad.net/linuxuprising/java/ubuntu bionic main" | sudo tee /etc/apt/sources.list.d/linuxuprising-java.list
  sudo apt-get update -y
  sudo apt-get install -y python3 python3-pip libpq-dev oracle-java11-installer unzip
  sudo pip3 --yes install elasticsearch psycopg2
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

date
installEnv
date
unzipBinairy
date
performIntegrationAssociation
date
checkError
date
python3 $SCRIPT_DIR/indexer/create_mappings.py
date
python3 $SCRIPT_DIR/indexer/index_all.py
date

