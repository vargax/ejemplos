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
REM Compila las clases del directorio source
REM ---------------------------------------------------------

cd ..

mkdir lib
mkdir classes

cd source
javac  -d ../classes/ uniandes/cupi2/visorImagen/mundo/*.java
javac -classpath ../classes/ -d ../classes/ uniandes/cupi2/visorImagen/interfaz/*.java


REM ---------------------------------------------------------
REM Crea el archivo jar a partir de los archivos compilados
REM ---------------------------------------------------------

cd ..
cd classes
jar cf ../lib/visorImagen.jar ./*

cd ..\bin

pause
