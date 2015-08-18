This howto walks through the steps required to setup the environment and build a simple web-based application to display a shape and analyse their non spacial related data.
# Workspace Setup
## Packages Installation
### Ubuntu 14.04
```
# Postgres
sudo apt-get install postgresql postgresql-contrib postgresql-client pgadmin3
sudo apt-get install postgis postgresql-9.1-postgis-2.1
# NodeJS
sudo apt-get install nodejs npm
```
### Fedora 22
```
# Postgres
ToDo
# NodeJS
ToDo
```
### Mac OSX 10.x
```
# Postgres
ToDo
# NodeJS
ToDo
```
## PostGIS setup
```
# Create geotabula user
sudo -i -u postgres
createuser -P -s -e geotabula
psql -h localhost -U geotabula geotabula
# Create geotabula database and enable Postgis
createdb -O geotabula geotabula
psql -c "CREATE EXTENSION postgis; CREATE EXTENSION postgis_topology;" geotabula
```
# Project Setup
## Prerequisites
```
### Create project folder
mkdir -p [pathToProjectFolder]
cd [pathToProjectFolder]
### Install NodeJS Modules
npm install geotabula express socket.io terraformer terraformer-wkt-parser
```
## Project Template
A basic NodeJS application has two files and two folders:
- *index.js:* This file contains the NodeJS code that runs in the server.
- *index.html:* This is the html file served to the client.
- *public folder:* The files in this folder are available to the client. This is the place to put client-side JavaScript and CSS files.
- *node_modules folder:* This folder contains the NodeJS npm installed modules.
```
mkdir public
touch index.js
touch index.html
```
## Geotabula example

