sudo apt-get update
sudo apt-get install python3
sudo apt-get install python3-pip
sudo pip3 install elasticsearch
sudo apt-get install libpq-dev
sudo pip3 install psycopg2



SELECT json_agg(json_build_object('id', a.id,'titre', a.titre,'objet', a.objet,'adr1', a.adr1,'adrs_codepostal', a.adrs_codepostal,'libcom', a.libcom))
FROM (SELECT id, titre, objet, adr1, adrs_codepostal, libcom FROM association LIMIT 100) a;

SELECT json_build_object('id', a.id,'titre', a.titre,'objet', a.objet,'adr1', a.adr1,'adrs_codepostal', a.adrs_codepostal,'libcom', a.libcom)
     FROM (SELECT id, titre, objet, adr1, adrs_codepostal, libcom FROM association LIMIT 10) a;