This howto walks through the steps required to setup the environment and build a simple web-based application to display a shape and analyse their non spacial related data.
# Workspace Setup
## Packages Installation
### Ubuntu 14.04
```
# Postgres
sudo apt-get install postgresql postgresql-contrib postgresql-client pgadmin3
sudo apt-get install postgis postgresql-9.3-postgis-scripts
# NodeJS
sudo apt-get install nodejs npm
```
### Fedora 22
```
# Postgres
sudo yum install postgresql
sudo yum install postgis
# NodeJS
sudo yum install nodejs
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
First we have to setup the project folder and install the required NodeJS modules:
```
### Create project folder
mkdir -p [pathToProjectFolder]
cd [pathToProjectFolder]
### Install NodeJS Modules
npm install geotabula express socket.io terraformer terraformer-wkt-parser
```
Now we are going to populate the database with an initial shape:

## Project Template
A basic NodeJS application has two files and two folders:
- *index.js:* This file contains the NodeJS code that runs in the server.
- *index.html:* This is the html file served to the client.
- *public folder:* The files in this folder are available to the client. This is the place to put client-side JavaScript and CSS files.
- *node_modules folder:* This folder contains the NodeJS npm installed modules.
For this ex
