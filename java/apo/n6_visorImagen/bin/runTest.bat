@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación
REM Licenciado bajo el esquema Academic Free License version 2.1
REM
REM Proyecto Cupi2
REM Ejercicio: n6_visorImagen
REM Autor: Mario Sánchez - 29-Jun-2005
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM/

REM ---------------------------------------------------------
REM Ejecución de la prueba
REM Archivo de ejecución: visorImagen.jar
REM Clase principal: uniandes.cupi2.visorImagen.test.ImagenTest
REM ---------------------------------------------------------
cd ..

java -classpath ./lib/visorImagen.jar;./test/lib/visorImagenTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.visorImagen.test.ImagenTest

cd bin
