@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n9_toDoList
REM Autor: Carlos Navarrete Puentes - 24-feb-2011
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

cd ../..
java -ea -classpath lib/toDoList.jar;test/lib/toDoListTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.toDoList.test.ToDoListTest
java -ea -classpath lib/toDoList.jar;test/lib/toDoListTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.toDoList.test.PrioridadTest
java -ea -classpath lib/toDoList.jar;test/lib/toDoListTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.toDoList.test.TareaTest
cd bin/win