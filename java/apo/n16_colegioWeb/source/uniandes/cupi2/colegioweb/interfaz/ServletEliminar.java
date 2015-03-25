/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ServletEliminar.java,v 1.2 2008/06/16 01:04:26 jua-gome Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n18_colegioWeb
 * Autor: Pablo Barvo - 27/04/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.colegioweb.interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uniandes.cupi2.colegioweb.mundo.Colegio;
import uniandes.cupi2.colegioweb.mundo.DatosException;
import uniandes.cupi2.colegioweb.mundo.Estudiante;

/**
 * Servlet encargado de eliminar un estudiante
 */
@SuppressWarnings("serial")
public class ServletEliminar extends ServletTemplate
{

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve el título de la página para el Header
     * @param request Pedido del cliente
     * @return Título de la página para el Header
     */
    public String darTituloPagina( HttpServletRequest request )
    {
        return "Eliminar Estudiante";
    }

    /**
     * Devuelve el nombre de la imagen para el título de la página en el Header
     * @param request Pedido del cliente
     * @return Nombre de la imagen para el título de la página en el Header
     */
    public String darImagenTitulo( HttpServletRequest request )
    {
        return "eliminar48.jpg";
    }

    /**
     * Escribe el contenido de la página
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepción de error al escribir la respuesta
     */
    public void escribirContenido( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        //
        // Saca el Printer
        PrintWriter respuesta = response.getWriter( );
        //
        // Busca el código del estudiante a eliminar
        String codigo = request.getParameter( "estudiante" );
        if( codigo == null )
        {
            imprimirMensajeError( respuesta, "Petición Inválida", "La petición no incluye información del estudiante a eliminar" );
        }
        else
        {
            try
            {
                ArrayList<Estudiante> estudiantes = Colegio.darInstancia( ).darEstudiantes( "CODIGO", codigo );
                if( estudiantes.size( ) != 1 )
                {
                    //
                    // Imprime el mensaje de error de no encontrar un estudiante
                    imprimirMensajeError( respuesta, "Estudiante no encontrado!", "El estudiante con código '" + codigo + "' no se encuentra en el sistema" );
                }
                else
                {
                    //
                    // Elimina el estudiante e imprime un mensaje de éxito
                    Colegio.darInstancia( ).eliminarEstudiante( codigo );
                    imprimirMensajeOk( respuesta, "Estudiante eliminado", "El estudiante fue eliminado del sistema" );
                }
            }
            catch( DatosException e )
            {
                //
                // Imprime el mensaje de la excepción
                imprimirMensajeError( respuesta, "Error al eliminar el estudiante con código '" + codigo + "'.", "Excepción generada en la operación", e );
            }
        }
    }

}
