@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n7_recetario
REM Autor: Catalina Rodríguez - 01-feb-2011
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

cd ../..
java -ea -classpath lib/recetario.jar;test/lib/recetarioTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.recetario.test.RecetarioTest
java -ea -classpath lib/recetario.jar;test/lib/recetarioTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.recetario.test.RecetaTest
cd bin/win