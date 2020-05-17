# PrimeNG Angular-CLI

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 1.0.0-rc.1.

## Development server
Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive/pipe/service/class/module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `-prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).
Before running the tests make sure you are serving the app via `ng serve`.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

https://www.data.gouv.fr/fr/datasets/repertoire-national-des-associations-rna/#_
https://www.associations.gouv.fr/les-centres-de-ressources-pour-les-responsables-ou-createurs-d-association.html#bloc-429
https://github.com/cquest/rnapi

https://stackoverflow.com/questions/2987433/how-to-import-csv-file-data-into-a-postgresql-table
https://docs.docker.com/install/linux/docker-ce/ubuntu/#install-docker-ce-1
https://docs.docker.com/config/daemon/#start-the-daemon-using-operating-system-utilities

http://domoenergytics.com/domo.energy/Installer-PostgreSQL-9-3-sur
http://mherman.org/blog/2016/03/13/designing-a-restful-api-with-node-and-postgres/
https://github.com/mjhea0/node-postgres-promises
https://api.gouv.fr/api/api-geo.html

https://developers.google.com/maps/documentation/javascript/?hl=fr
https://github.com/Asymmetrik/ngx-leaflet#api

## Installation de docker 

    sudo apt-get install \
        apt-transport-https \
        ca-certificates \
        curl \
        software-properties-common
    
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
    
    sudo apt-key fingerprint 0EBFCD88
    
    pub   4096R/0EBFCD88 2017-02-22
          Key fingerprint = 9DC8 5822 9FC7 DD38 854A  E2D8 8D81 803C 0EBF CD88
    uid                  Docker Release (CE deb) <docker@docker.com>
    sub   4096R/F273FCD8 2017-02-22
    
    sudo apt-get update
    
    sudo apt-get install docker-ce

    su - posgres
    pwd : pg
    
    /usr/local/pgsql/bin/psql -U postgres -h localhost -f creat_sql.sql
    
    sudo /etc/init.d/postgresql start
    
    http://localhost:3000/api/associations?codePostal=03&ville=
    http://localhost:3000/api/associations?codePostal=03&ville=MONTLUCON
           
    CREATE INDEX idx_waldec_asso ON waldec_association (adrs_codepostal);
    
    # Créer un composant
    ng g c authentication
    
    # création de services
    ng g s authentication/authentication --flat
    
## Serveur PHP symfony
       
    composer install
    
    php -S localhost:3000 -t public
    
    # Generation des entites
    php bin/console doctrine:mapping:convert --from-database annotation ./src/Entity
    
    # Connaitre les paths des api
    php bin/console debug:router
    
    # Connection
    curl -X POST http://localhost:3000/api/login_check -d username=gary -d password=pass
    
    # Reindexation
    php ./bin/console elastic:reindex
    php ./bin/console elastic:reindex:waldec:association

## Python indexer
     python3 create_mappings.py
     python3 index_all.py