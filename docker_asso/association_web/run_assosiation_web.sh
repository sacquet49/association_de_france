#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "$0" )" && pwd )"

installEnv() {
  apt-get update -y
  apt-get install -y libpq-dev openjdk-17-jre unzip netcat
}

runApp () {
  cd $SCRIPT_DIR
  java -jar association_web.jar --spring.config.location=application.properties
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
runApp
date
