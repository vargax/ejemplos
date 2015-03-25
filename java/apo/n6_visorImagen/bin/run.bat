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
REM Ejecución del programa
REM Archivo de ejecución: visorImagen.jar
REM Clase principal: uniandes.cupi2.visorImagen.interfaz.InterfazVisorImagen
REM ---------------------------------------------------------
cd ..
java -classpath ./lib/visorImagen.jar; uniandes.cupi2.visorImagen.interfaz.InterfazVisorImagen
cd bin
