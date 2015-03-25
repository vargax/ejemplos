/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n18_colegioWeb
 * Autor: Pablo Barvo - Apr 26, 2006
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
 * Servlet de manejo de edición de la información de un estudiante
 */
@SuppressWarnings("serial")
public class ServletEdicion extends ServletTemplate
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
        //
        // Si es agregar/modificar cambia el título
        String estudiante = request.getParameter( "estudiante" );
        if( estudiante == null )
        {
            return "Agregar Estudiante";
        }
        else
        {
            return "Modificar Estudiante";
        }
    }

    /**
     * Devuelve el nombre de la imagen para el título de la página en el Header
     * @param request Pedido del cliente
     * @return Nombre de la imagen para el título de la página en el Header
     */
    public String darImagenTitulo( HttpServletRequest request )
    {
        //
        // Si es agregar/modificar cambia el título
        String estudiante = request.getParameter( "estudiante" );
        if( estudiante == null )
        {
            return "agregar48.jpg";
        }
        else
        {
            return "editar48.jpg";
        }
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
        // Si es agregar/modificar realiza diferentes acciones
        String codigo = request.getParameter( "estudiante" );
        boolean nuevo = ( codigo == null );
        if( !nuevo )
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
                    // Imprime la forma con la información del estudiante
                    Estudiante estudiante = estudiantes.get( 0 );
                    imprimirContendio( respuesta, estudiante.darCodigo( ), estudiante.darNombre( ), estudiante.darApellido( ), estudiante.darCurso( ), estudiante.darPromedio( ), estudiante.darComentarios( ), false );
                }
            }
            catch( DatosException e )
            {
                //
                // Imprime el mensaje de la excepción
                imprimirMensajeError( respuesta, "Error al buscar el estudiante con código '" + codigo + "'.", "Excepción generada en la operación", e );
            }
        }
        else
        {
            //
            // Imprime el contenido con todos los valores vacíos
            imprimirContendio( respuesta, "", "", "", "", "", "", true );
        }
    }

    /**
     * Imprime el contenido con la información especificada
     * @param respuesta Respuesta al cliente
     * @param codigo Código del estudiante
     * @param nombre Nombre del estudiante
     * @param apellido Apellido del estudiante
     * @param curso Curso del estudiante
     * @param promedio Promedio del estudiante
     * @param comentarios Comentarios adicionales
     * @param nuevo Indica si es un nuevo estudiante o es una modificación
     */
    private void imprimirContendio( PrintWriter respuesta, String codigo, String nombre, String apellido, String curso, String promedio, String comentarios, boolean nuevo )
    {
        //
        // Imprime el contenido
        respuesta.write( "                      <form method=\"POST\" action=\"guardar.htm\">\r\n" );
        respuesta.write( "                          <table border=\"0\" width=\"100%\" id=\"table5\">\r\n" );
        respuesta.write( "                              <tr>\r\n" );
        respuesta.write( "                                  <td width=\"18%\" align=\"right\"><b>Código:</b></td>\r\n" );
        respuesta.write( "                                  <td width=\"80%\">\r\n" );
        respuesta.write( "                                  <input type=\"text\" name=\"codigo\" size=\"26\" class=\"normal\" value=\"" + codigo + "\"></td>\r\n" );
        respuesta.write( "                              </tr>\r\n" );
        respuesta.write( "                              <tr>\r\n" );
        respuesta.write( "                                  <td width=\"18%\" align=\"right\"><b>Nombre:</b></td>\r\n" );
        respuesta.write( "                                  <td width=\"80%\">\r\n" );
        respuesta.write( "                                  <input type=\"text\" name=\"nombre\" size=\"26\" class=\"normal\" value=\"" + nombre + "\"></td>\r\n" );
        respuesta.write( "                              </tr>\r\n" );
        respuesta.write( "                              <tr>\r\n" );
        respuesta.write( "                                  <td width=\"18%\" align=\"right\"><b>Apellido:</b></td>\r\n" );
        respuesta.write( "                                  <td width=\"80%\">\r\n" );
        respuesta.write( "                                  <input type=\"text\" name=\"apellido\" size=\"26\" class=\"normal\" value=\"" + apellido + "\"></td>\r\n" );
        respuesta.write( "                              </tr>\r\n" );
        respuesta.write( "                              <tr>\r\n" );
        respuesta.write( "                                  <td width=\"18%\" align=\"right\"><b>Curso:</b></td>\r\n" );
        respuesta.write( "                                  <td width=\"80%\">\r\n" );
        respuesta.write( "                                  <select size=\"1\" name=\"curso\" class=\"normal\">\r\n" );
        respuesta.write( "                                  <option value=\"K\"" + ( "K".equals( curso ) ? " selected" : "" ) + ">Kinder</option>\r\n" );
        respuesta.write( "                                  <option value=\"T\"" + ( "T".equals( curso ) ? " selected" : "" ) + ">Transición</option>\r\n" );
        respuesta.write( "                                  <option value=\"1\"" + ( "1".equals( curso ) ? " selected" : "" ) + ">1º</option>\r\n" );
        respuesta.write( "                                  <option value=\"2\"" + ( "2".equals( curso ) ? " selected" : "" ) + ">2º</option>\r\n" );
        respuesta.write( "                                  <option value=\"3\"" + ( "3".equals( curso ) ? " selected" : "" ) + ">3º</option>\r\n" );
        respuesta.write( "                                  <option value=\"4\"" + ( "4".equals( curso ) ? " selected" : "" ) + ">4º</option>\r\n" );
        respuesta.write( "                                  <option value=\"5\"" + ( "5".equals( curso ) ? " selected" : "" ) + ">5º</option>\r\n" );
        respuesta.write( "                                  <option value=\"6\"" + ( "6".equals( curso ) ? " selected" : "" ) + ">6º</option>\r\n" );
        respuesta.write( "                                  <option value=\"7\"" + ( "7".equals( curso ) ? " selected" : "" ) + ">7º</option>\r\n" );
        respuesta.write( "                                  <option value=\"8\"" + ( "8".equals( curso ) ? " selected" : "" ) + ">8º</option>\r\n" );
        respuesta.write( "                                  <option value=\"9\"" + ( "9".equals( curso ) ? " selected" : "" ) + ">9º</option>\r\n" );
        respuesta.write( "                                  <option value=\"10\"" + ( "10".equals( curso ) ? " selected" : "" ) + ">10º</option>\r\n" );
        respuesta.write( "                                  <option value=\"11\"" + ( "11".equals( curso ) ? " selected" : "" ) + ">11º</option>\r\n" );
        respuesta.write( "                                  </select></td>\r\n" );
        respuesta.write( "                              </tr>\r\n" );
        respuesta.write( "                              <tr>\r\n" );
        respuesta.write( "                                  <td width=\"18%\" align=\"right\"><b>Promedio:</b></td>\r\n" );
        respuesta.write( "                                  <td width=\"80%\">\r\n" );
        respuesta.write( "                                  <input type=\"text\" name=\"promedio\" size=\"26\" class=\"normal\" value=\"" + promedio + "\"></td>\r\n" );
        respuesta.write( "                              </tr>\r\n" );
        respuesta.write( "                              <tr>\r\n" );
        respuesta.write( "                                  <td width=\"18%\" align=\"right\">&nbsp;</td>\r\n" );
        respuesta.write( "                                  <td width=\"80%\">&nbsp;</td>\r\n" );
        respuesta.write( "                              </tr>\r\n" );
        respuesta.write( "                              <tr>\r\n" );
        respuesta.write( "                                  <td width=\"18%\" align=\"right\"><b>\r\n" );
        respuesta.write( "                                  Comentarios:</b></td>\r\n" );
        respuesta.write( "                                  <td width=\"80%\">&nbsp;</td>\r\n" );
        respuesta.write( "                              </tr>\r\n" );
        respuesta.write( "                              <tr>\r\n" );
        respuesta.write( "                                  <td align=\"right\" colspan=\"2\" valign=\"middle\">\r\n" );
        respuesta.write( "                                  <p align=\"center\">\r\n" );
        respuesta.write( "                                  <textarea rows=\"8\" name=\"comentarios\" cols=\"62\">" + comentarios + "</textarea></td>\r\n" );
        respuesta.write( "                              </tr>\r\n" );
        respuesta.write( "                          </table>\r\n" );
        respuesta.write( "                          <p>\r\n" );
        respuesta.write( "                          <input type=\"submit\" value=\"Guardar\" name=\"B1\" class=\"normal\">\r\n" );
        respuesta.write( "                          <input type=\"reset\" value=\"Borrar\" name=\"B2\" class=\"normal\"></p>\r\n" );
        respuesta.write( "                          <input type=\"hidden\" name=\"tipo\" value=\"" + ( ( nuevo ) ? "nuevo" : "modificar" ) + "\">\r\n" );
        respuesta.write( "                      </form>\r\n" );
    }

}
