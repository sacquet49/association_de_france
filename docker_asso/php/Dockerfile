# ./docker/php/Dockerfile
FROM php:7.4-fpm

RUN apt-get update && apt-get install -y libpq-dev git \
	&& docker-php-ext-install pdo pdo_pgsql

RUN pecl install apcu-5.1.17
RUN docker-php-ext-enable apcu

RUN php -r "copy('https://getcomposer.org/installer', 'composer-setup.php');" \
    && php -r "if (hash_file('SHA384', 'composer-setup.php') === '795f976fe0ebd8b75f26a6dd68f78fd3453ce79f32ecb33e7fd087d39bfeb978342fb73ac986cd4f54edd0dc902601dc') { echo 'Installer verified'; } else { echo 'Installer corrupt'; unlink('composer-setup.php'); } echo PHP_EOL;" \
    && php composer-setup.php --filename=composer \
    && php -r "unlink('composer-setup.php');" \
    && mv composer /usr/local/bin/composer

WORKDIR /usr/src/app

COPY ./back-asso /usr/src/app

RUN PATH=$PATH:/usr/src/app/back-asso/vendor/bin:bin

RUN composer require symfony/dotenv
RUN composer install --no-dev --optimize-autoloader
RUN chmod -R 777 /usr/src/app/var/
RUN chmod -R 777 /usr/src/app/config/
RUN chmod -R 777 /usr/src/app/src/