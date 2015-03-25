@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n3_sistemaLockers
REM Autor: Catalina Rodriguez - 23-ago-2010
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

cd ../..
java -ea -classpath lib/sistemaLockers.jar;test/lib/sistemaLockersTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.sistemaLockers.test.SistemaLockersTest
java -ea -classpath lib/sistemaLockers.jar;test/lib/sistemaLockersTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.sistemaLockers.test.LocacionTest
java -ea -classpath lib/sistemaLockers.jar;test/lib/sistemaLockersTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.sistemaLockers.test.CasilleroTest
cd bin/win