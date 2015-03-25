@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n5_crucero
REM Autor: Catalina Rodríguez - 16-sep-2010
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

cd ../..
java -ea -classpath lib/crucero.jar;lib/flickrapi-1.2.jar;test/lib/cruceroTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.crucero.test.CruceroTest
java -ea -classpath lib/crucero.jar;lib/flickrapi-1.2.jar;test/lib/cruceroTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.crucero.test.DestinoTest
java -ea -classpath lib/crucero.jar;lib/flickrapi-1.2.jar;test/lib/cruceroTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.crucero.test.FotoTest
cd bin/win