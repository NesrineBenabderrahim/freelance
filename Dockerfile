# Étape 1 : Utiliser une image de base officielle PHP avec Apache
FROM php:8.2-apache

# Étape 2 : Copier les fichiers de l'application dans le dossier public du serveur Apache
COPY . /var/www/html

# Étape 3 : Configurer les permissions pour le dossier public
RUN chown -R www-data:www-data /var/www/html \
    && chmod -R 755 /var/www/html

# Étape 4 : Exposer le port 80 pour le serveur web
EXPOSE 8080

# Étape 5 : Activer le module rewrite d'Apache (si nécessaire pour les frameworks comme Laravel)
RUN a2enmod rewrite

# Étape 6 : Redémarrer Apache pour appliquer les configurations
CMD ["apache2-foreground"]
