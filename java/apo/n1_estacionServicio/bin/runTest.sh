#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad de los Andes (Bogotá - Colombia)
# Departamento de Ingeniería de Sistemas y Computación
# Licenciado bajo el esquema Academic Free License version 2.1
#
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n1_prueba
# Autor: Luis - 25-Mar-2010
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo
#SET CLASSPATH=

# ---------------------------------------------------------
# Ejecucion de las pruebas
# ---------------------------------------------------------

cd ../..
java -ea -classpath lib/estacionServicio.jar:test/lib/estacionServicioTest.jar:test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.estacionServicio.test.EstacionServicioTest
java -ea -classpath lib/estacionServicio.jar:test/lib/estacionServicioTest.jar:test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.estacionServicio.test.SurtidorTest

cd bin/mac

stty echo