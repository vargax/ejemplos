#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad de los Andes (Bogotá - Colombia)
# Departamento de Ingeniería de Sistemas y Computación
# Licenciado bajo el esquema Academic Free License version 2.1
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n4_procesadoraCafe
# Autor: Catalina Rodríguez - 01-sep-2010
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo
#SET CLASSPATH=

# ---------------------------------------------------------
# Asegura la creación de los directorios classes y lib
# ---------------------------------------------------------

cd ../..
mkdir classes
mkdir lib

# ---------------------------------------------------------
# Compila las clases del directorio source
# ---------------------------------------------------------

cd source
javac -source 1.5 -nowarn -d ../classes/ uniandes/cupi2/procesadoraCafe/mundo/*.java
javac -source 1.5 -nowarn -d ../classes/ uniandes/cupi2/procesadoraCafe/interfaz/*.java

# ---------------------------------------------------------
# Crea el archivo jar a partir de los archivos compilados
# ---------------------------------------------------------

cd ../classes
jar cf ../lib/procesadoraCafe.jar uniandes/*

cd ../bin/mac
stty echo