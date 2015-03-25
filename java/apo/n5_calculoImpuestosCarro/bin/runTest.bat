@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación
REM Licenciado bajo el esquema Academic Free License version 2.1
REM
REM Proyecto Cupi2
REM Ejercicio: n5_calculoImpuestosCarro
REM Autor: Diana Puentes - 22-Jun-2005
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM/

REM ---------------------------------------------------------
REM Ejecución de la prueba
REM Archivo de ejecución: impuestosCarroTest.jar
REM ---------------------------------------------------------

cd..

java -classpath ./lib/impuestosCarro.jar;./test/lib/impuestosCarroTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.impuestosCarro.test.CalculadorImpuestosTest
java -classpath ./lib/impuestosCarro.jar;./test/lib/impuestosCarroTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.impuestosCarro.test.LineaTest
java -classpath ./lib/impuestosCarro.jar;./test/lib/impuestosCarroTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.impuestosCarro.test.MarcaTest
java -classpath ./lib/impuestosCarro.jar;./test/lib/impuestosCarroTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.impuestosCarro.test.ModeloTest
java -classpath ./lib/impuestosCarro.jar;./test/lib/impuestosCarroTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.impuestosCarro.test.RangoImpuestoTest

cd bin


