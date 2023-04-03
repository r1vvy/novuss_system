CREATE USER ${REST_API_DB_USER}@'%' IDENTIFIED BY ${REST_API_DB_USER_PASSWORD};
GRANT ALL PRIVILEGES ON novussfederation_db.* ${REST_API_DB_USER}TO @'%';

CREATE USER ${AUTH_SERVICE_DB_USER}@'%' IDENTIFIED BY ${AUTH_SERVICE_DB_USER_PASSWORD};
GRANT ALL PRIVILEGES ON novussfederation_db.* TO ${AUTH_SERVICE_DB_USER}@'%';
