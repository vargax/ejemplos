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

SET CLASSPATH=

REM ---------------------------------------------------------
REM Compila las clases del directorio test/source
REM ---------------------------------------------------------

cd ..\test\

mkdir classes

cd source
javac -classpath ../../lib/visorImagen.jar;../lib/junit.jar -d ../classes/ uniandes/cupi2/visorImagen/test/*.java

REM ---------------------------------------------------------
REM Crea el archivo jar a partir de los archivos compilados
REM ---------------------------------------------------------

cd ..\classes

jar cf ../lib/visorImagenTest.jar uniandes/* -C ../data .

cd ..\..\bin

pause
