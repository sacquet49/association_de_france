#!/bin/bash

apt-get update && apt-get install -y libpq-dev git docker-php-ext-install pdo pdo_pgsql

pecl install apcu-5.1.17
docker-php-ext-enable apcu

php -r "copy('https://getcomposer.org/installer', 'composer-setup.php');" \
	&& php -r "if (hash_file('SHA384', 'composer-setup.php') === 'e0012edf3e80b6978849f5eff0d4b4e4c79ff1609dd1e613307e16318854d24ae64f26d17af3ef0bf7cfb710ca74755a') { echo 'Installer verified'; } else { echo 'Installer corrupt'; unlink('composer-setup.php'); } echo PHP_EOL;" \
	&& php composer-setup.php --filename=composer \
	&& php -r "unlink('composer-setup.php');" \
	&& mv composer /usr/local/bin/composer

cd /usr/src/app

cp -rf ./back-asso /usr/src/app

PATH=$PATH:/usr/src/app/back-asso/vendor/bin:bin

composer require symfony/dotenv
composer install --no-dev --optimize-autoloader
chmod -R 777 /usr/src/app/var/
chmod -R 777 /usr/src/app/config/
chmod -R 777 /usr/src/app/src/