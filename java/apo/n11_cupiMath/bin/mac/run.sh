#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad de los Andes (Bogotá - Colombia)
# Departamento de Ingeniería de Sistemas y Computación
# Licenciado bajo el esquema Academic Free License version 2.1
#
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n11_expresionesAritmeticas
# Autor: cupi2 - 15-mar-2011
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo

#SET CLASSPATH=

# ---------------------------------------------------------
# Ejecucion del programa
# ---------------------------------------------------------

cd ../..
java -ea -classpath ./lib/expresionesAritmeticas.jar uniandes.cupi2.expresionesAritmeticas.interfaz.InterfazExpresionesAritmeticas
cd bin/mac

stty echo