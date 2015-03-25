@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2
REM Ejercicio: n6_buscaminas
REM Autor: Mario Sánchez - 26-Jul-2005
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM/

REM ---------------------------------------------------------
REM Ejecución de la prueba
REM ---------------------------------------------------------

cd ..
java -classpath ./lib/buscaminas.jar;./test/lib/buscaminasTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.buscaminas.test.CampoMinadoTest
java -classpath ./lib/buscaminas.jar;./test/lib/buscaminasTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.buscaminas.test.CasillaTest
cd bin