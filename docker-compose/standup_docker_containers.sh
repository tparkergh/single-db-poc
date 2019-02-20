#!/bin/sh
# stand up test bubble environment

echo $PWD
CARES_ROOT=$PWD

echo "remove any existing docker containers..."
docker-compose -f docker-compose/docker-compose.yml down

echo "run docker-compose..."
docker-compose -f docker-compose/docker-compose.yml up -d

cd $CARES_ROOT

docker ps

