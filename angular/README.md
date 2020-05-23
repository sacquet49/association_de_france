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