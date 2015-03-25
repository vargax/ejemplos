/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_toDoList
 * Autor: Carlos Navarrete Puentes - 24-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.toDoList.mundo;

/**
 * Excepción que se lanza si hay algún problema con el manejo de la persistencia
 */
public class PersistenciaException extends Exception
{

    /**
     * Construye la excepción con el mensaje que describe el problema
     * 
     * @param mensaje Mensaje que describe la causa de la excepción
     */
    public PersistenciaException( String mensaje )
    {
        super( mensaje );
    }
}
