@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n4_procesadoraCafe
REM Autor: Catalina Rodríguez - 01-sep-2010
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

cd ../..
java -ea -classpath lib/procesadoraCafe.jar;test/lib/procesadoraCafeTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.procesadoraCafe.test.ProcesadoraCafeTest
java -ea -classpath lib/procesadoraCafe.jar;test/lib/procesadoraCafeTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.procesadoraCafe.test.ProductoTest
java -ea -classpath lib/procesadoraCafe.jar;test/lib/procesadoraCafeTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.procesadoraCafe.test.ProveedorTest
java -ea -classpath lib/procesadoraCafe.jar;test/lib/procesadoraCafeTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.procesadoraCafe.test.ClienteTest
cd bin/win