# Techno : 

Back : Symfony API v4, php 7.

Front : PrimeNg, Angular 9, cartographie leaflet

Base de données : PostgresSql 12

Indexer NoSql : ElasticSearch / Kibana 7.6 + Application d'indexation spring boot 2 Java 11. 

# How to install this app

Cette application nécessite docker et la WSL pour être lancé dans un environnement windows.
https://nickjanetakis.com/blog/setting-up-docker-for-windows-and-wsl-to-work-flawlessly

Le fichier docker-compose à la racine du projet permet la compilation de la partie JAVA et Angular du projet.
Le répertoire docker-asso contient l'environnement d'exécution du projet toujours sous la forme d'une description dockeriser.

Lancement de l'installation de l'environement :

`$ cd docker_asso`

`$ bash -xe install.sh`

A la fin de l'installation, l'application est accessible via un navigateur `localhost`

Kibana est accessible via un navigateur `localhost:5601`

Connexion :  admin / Admintcg2018#

# IHM

![Main page](preview/home.png)

![Exemple admin](preview/admin.png)

![Exemple recherche](preview/search.png)

![Exemple recherche](preview/search2.png)

![Exemple d'une association](preview/association.png)