# Prerequisites
- Ubuntu 14.04x64 / Fedora 22

# Workspace Setup
## PostGIS Database
- Ubuntu 14.04
```
sudo su
# Install packages
aptitude install postgresql postgresql-contrib postgresql-client pgadmin3
aptitude install postgis postgresql-9.1-postgis-2.1
# Create geotabula user
sudo -i -u postgres
createuser -P -s -e geotabula
psql -h localhost -U geotabula geotabula
# Create geotabula database and enable Postgis
createdb -O geotabula geotabula
psql -c "CREATE EXTENSION postgis; CREATE EXTENSION postgis_topology;" geotabula
```
- Fedora 22
```
ToDo
```
