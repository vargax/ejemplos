@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n2_bancoSangre
REM Autor: Catalina Rodríguez - 11-ago-2010
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

cd ../..
java -ea -classpath lib/bancoSangre.jar;test/lib/bancoSangreTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.bancoSangre.test.BancoSangreTest
java -ea -classpath lib/bancoSangre.jar;test/lib/bancoSangreTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.bancoSangre.test.TipoSangreTest
cd bin/win