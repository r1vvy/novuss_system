FROM php:7.4-apache

RUN apt-get update \
    && apt-get install -y libpq-dev \
    && docker-php-ext-install pdo_mysql

COPY adminer-4.8.1-mysql.php /var/www/html/index.php

EXPOSE 80

CMD ["apache2-foreground"]
