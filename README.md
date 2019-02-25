# single-db-poc
A proof of concept project that will highlight the advantages and disadvantages of using a single data base to store all CARES related data.

## Intake Incident Reporting
Intake Incident Reporting (IIR) is a proof of concept project that will allow the reporter of an incident to be entered into CARES and recorded in CWDS/CMS.  

### Standing up the Intake Incident Reporting Project

The IIR project consist of serval docker containers and a mocked z/OS Connect service.

#### GitHub Project

https://github.com/ca-cwds/single-db-poc.git

#### Docker compose file location
All locations are relative to the root directory of the project.

docker-compose

#### Start the project

>$ sh docker-compose/standup_docker_containers.sh

### Individual Components of the Intake Incident Reporting Project
#### Cares BRE API

Docker compose file location
>cares-bre/cares-bre-rest-api

Start application
>docker-compose -f docker-compose-cares-bre-rest-api.yml up -d

Stop application
>docker-compose -f docker-compose-cares-bre-rest-api.yml down

#### Cares API

Docker compose file location
>cares/cares-rest-api

Start application
>docker-compose -f docker-compose-cares-rest-api.yml up -d

Stop application
>docker-compose -f docker-compose-cares-rest-api.yml down

#### Cares UI

Docker compose file location
>cares-ui

Start application
>docker-compose -f docker-compose-cares-ui.yml up -d

Stop application
>docker-compose -f docker-compose-cares-ui.yml down

### SB Legacy environment

Cares BRE Rest API
- http://api-a.sblegacy.cwds.io:3006/swagger-ui.html#/bre-rest-controller

Cares Rest API
- http://api-a.sblegacy.cwds.io:3007/swagger-ui.html#

Cares UI
- http://api-a.sblegacy.cwds.io:3008/

### Local environment
DB2 should be provided. Simple way to have it it's to use existing [cwds/db2data](https://hub.docker.com/r/cwds/db2data) docker image. Refer to db2data dockerhub docs how to run db2data docker container. Notice that we should build own version of the image to do not affect other projects.
Change docker compose database configuration to point to local db2.

Note: docker containers on one host can be connected through DockerNAT IP address. You can find that value by command ipconfig in Windows, IPv4 Address attribute value. That IP can be used as host to DB2 if DB2 is in docker container on the same host.

Cares BRE Rest API
- http://localhost:3006/swagger-ui.html#/bre-rest-controller

Cares Rest API
- http://localhost:3007/swagger-ui.html#

Cares UI
- http://localhost:3008/
