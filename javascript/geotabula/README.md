# Workspace Setup
## Ubuntu 14.04
### PostGIS Database
```
# Install packages
sudo apt-get install postgresql postgresql-contrib postgresql-client pgadmin3
sudo apt-get install postgis postgresql-9.1-postgis-2.1
# Create geotabula user
sudo -i -u postgres
createuser -P -s -e geotabula
psql -h localhost -U geotabula geotabula
# Create geotabula database and enable Postgis
createdb -O geotabula geotabula
psql -c "CREATE EXTENSION postgis; CREATE EXTENSION postgis_topology;" geotabula
```
### NodeJS Runtime Environment
```
sudo apt-get install nodejs npm
```
## Fedora 22
### PostGIS Database
```
# Install packages
ToDo
# Create geotabula user
sudo -i -u postgres
createuser -P -s -e geotabula
psql -h localhost -U geotabula geotabula
# Create geotabula database and enable Postgis
createdb -O geotabula geotabula
psql -c "CREATE EXTENSION postgis; CREATE EXTENSION postgis_topology;" geotabula
```
### NodeJS Runtime Environment
```
ToDo
```
## Mac OSX
### PostGIS Database
```
# Install packages
ToDo
# Create geotabula user
sudo -i -u postgres
createuser -P -s -e geotabula
psql -h localhost -U geotabula geotabula
# Create geotabula database and enable Postgis
createdb -O geotabula geotabula
psql -c "CREATE EXTENSION postgis; CREATE EXTENSION postgis_topology;" geotabula
```
### NodeJS Runtime Environment
```
ToDo
```
# Project Setup
```
## Create project folder
mkdir -p [pathToProjectFolder]
cd [pathToProjectFolder]
## Install NodeJS Modules
npm install mysql pg express socket.io terraformer terraformer-wkt-parser
```
