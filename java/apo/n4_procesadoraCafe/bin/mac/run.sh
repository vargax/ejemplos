#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad de los Andes (Bogotá - Colombia)
# Departamento de Ingeniería de Sistemas y Computación
# Licenciado bajo el esquema Academic Free License version 2.1
#
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n4_procesadoraCafe
# Autor: Catalina Rodríguez - 01-sep-2010
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo

#SET CLASSPATH=

# ---------------------------------------------------------
# Ejecucion del programa
# ---------------------------------------------------------

cd ../..
java -ea -classpath ./lib/procesadoraCafe.jar uniandes.cupi2.procesadoraCafe.interfaz.InterfazProcesadoraCafe
cd bin/mac

stty echo