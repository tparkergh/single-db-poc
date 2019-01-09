# single-db-poc
A proof of concept project that will highlight the advantages and disadvantages of using a single data base to store all CARES related data.

# Cares BRE API

Docker compose file location
>cares-bre/cares-bre-rest-api

Start application
>docker-compose -f docker-compose-cares-bre-rest-api.yml up -d

Stop application
>docker-compose -f docker-compose-cares-bre-rest-api.yml down

# Cares API

Docker compose file location
>cares-bre-rest-api

Start application
>docker-compose -f docker-compose-cares-rest-api.yml up -d

Stop application
>docker-compose -f docker-compose-cares-rest-api.yml down

# Cares UI

Docker compose file location
>cares-ui

Start application
>docker-compose -f docker-compose-cares-ui.yml up -d

Stop application
>docker-compose -f docker-compose-cares-ui.yml down
