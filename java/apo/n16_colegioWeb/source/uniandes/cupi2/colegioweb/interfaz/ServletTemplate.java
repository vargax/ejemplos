/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ServletTemplate.java,v 1.2 2008/06/16 01:04:26 jua-gome Exp $
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servelt Abstracto principal con el template del diseño de la página
 */
@SuppressWarnings("serial")
public abstract class ServletTemplate extends HttpServlet
{

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Maneja un pedido GET de un cliente
     * @param request Pedido del cliente
     * @param response Respuesta
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        procesarPedido( request, response );
    }

    /**
     * Maneja un pedido POST de un cliente
     * @param request Pedido del cliente
     * @param response Respuesta
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        procesarPedido( request, response );
    }

    /**
     * Procesa el pedido de igual manera para todos
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepción de error al escribir la respuesta
     */
    private void procesarPedido( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        //
        // Comienza con el Header del template
        imprimirHeader( request, response );
        //
        // Escribe el contenido de la página
        escribirContenido( request, response );
        //
        // Termina con el footer del template
        imprimirFooter( response );

    }

    /**
     * Imprime el Header del diseño de la página
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepción al imprimir en el resultado
     */
    private void imprimirHeader( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        //
        // Saca el printer de la repuesta
        PrintWriter respuesta = response.getWriter( );
        //
        // Imprime el header
        respuesta.write( "<html>\r\n" );
        respuesta.write( "<head>\r\n" );
        respuesta.write( "<meta http-equiv=\"Content-Language\" content=\"es-co\">\r\n" );
        respuesta.write( "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\">\r\n" );
        respuesta.write( "<title>Wolf Village School - " + darTituloPagina( request ) + "</title>\r\n" );
        respuesta.write( "<link type=\"text/css\" rel=\"stylesheet\" href=\"style/wolf.css\">\r\n" );
        respuesta.write( "</head>\r\n" );

        respuesta.write( "<body>\r\n" );
        respuesta.write( "<div align=center>\r\n" );
        respuesta.write( "<center>\r\n" );

        respuesta.write( "<table border=\"0\" width=\"720\" id=\"table1\">\r\n" );
        respuesta.write( "   <tr>\r\n" );
        respuesta.write( "       <td>\r\n" );
        respuesta.write( "       <p align=\"center\">\r\n" );
        respuesta.write( "       <img border=\"0\" src=\"imagenes/titulo.jpg\" width=\"640\" height=\"100\"></td>\r\n" );
        respuesta.write( "   </tr>\r\n" );
        respuesta.write( "   <tr>\r\n" );
        respuesta.write( "       <td>&nbsp;</td>\r\n" );
        respuesta.write( "   </tr>\r\n" );
        respuesta.write( "   <tr>\r\n" );
        respuesta.write( "       <td>\r\n" );
        respuesta.write( "       <table border=\"1\" width=\"100%\" style=\"border-collapse: collapse\" bordercolor=\"#999999\" id=\"table2\">\r\n" );
        respuesta.write( "           <tr>\r\n" );
        respuesta.write( "               <td class=\"h2\" height=\"30\">\r\n" );
        respuesta.write( "               <h1 align=\"center\">Sistema Web de Manejo de Estudiantes</td>\r\n" );
        respuesta.write( "           </tr>\r\n" );
        respuesta.write( "           <tr>\r\n" );
        respuesta.write( "               <td bgcolor=\"#000000\" height=\"3px\"></td>\r\n" );
        respuesta.write( "           </tr>\r\n" );
        respuesta.write( "           <tr>\r\n" );
        respuesta.write( "               <td>\r\n" );
        respuesta.write( "               <table border=\"0\" width=\"710\" id=\"table3\">\r\n" );
        respuesta.write( "                   <tr>\r\n" );
        respuesta.write( "                       <td width=\"696\" colspan=\"4\" bgcolor=\"#E2E2E2\">\r\n" );
        respuesta.write( "                       <table border=\"0\" width=\"614\" id=\"table4\">\r\n" );
        respuesta.write( "                           <tr>\r\n" );
        respuesta.write( "                               <td width=\"19\">&nbsp;</td>\r\n" );
        respuesta.write( "                               <td width=\"21\">\r\n" );
        respuesta.write( "                               <img border=\"0\" src=\"imagenes/" + darImagenTitulo( request ) + "\" width=\"48\" height=\"48\"></td>\r\n" );
        respuesta.write( "                               <td width=\"560\"><h2>" + darTituloPagina( request ) + "</td>\r\n" );
        respuesta.write( "                           </tr>\r\n" );
        respuesta.write( "                       </table>\r\n" );
        respuesta.write( "                       </td>\r\n" );
        respuesta.write( "                   </tr>\r\n" );
        respuesta.write( "                   <tr>\r\n" );
        respuesta.write( "                       <td width=\"42\">&nbsp;</td>\r\n" );
        respuesta.write( "                       <td width=\"572\" colspan=\"2\">&nbsp;</td>\r\n" );
        respuesta.write( "                       <td width=\"82\">&nbsp;</td>\r\n" );
        respuesta.write( "                   </tr>\r\n" );
        respuesta.write( "                   <tr>\r\n" );
        respuesta.write( "                       <td width=\"42\">&nbsp;</td>\r\n" );
        respuesta.write( "                       <td width=\"25\">&nbsp;</td>\r\n" );
        respuesta.write( "                       <td width=\"543\">\r\n" );
    }

    /**
     * Imprime el Footer del diseño de la página
     * @param response Respuesta
     * @throws IOException Excepción al escribir en la respuesta
     */
    private void imprimirFooter( HttpServletResponse response ) throws IOException
    {
        //
        // Saca el writer de la respuesta
        PrintWriter respuesta = response.getWriter( );
        //
        // Imprime el footer
        respuesta.write( "                   </td>\r\n" );
        respuesta.write( "                       <td width=\"82\">&nbsp;</td>\r\n" );
        respuesta.write( "                   </tr>\r\n" );
        respuesta.write( "                   <tr>\r\n" );
        respuesta.write( "                       <td width=\"42\">&nbsp;</td>\r\n" );
        respuesta.write( "                       <td width=\"25\">&nbsp;</td>\r\n" );
        respuesta.write( "                       <td width=\"543\">&nbsp;</td>\r\n" );
        respuesta.write( "                       <td width=\"82\">&nbsp;</td>\r\n" );
        respuesta.write( "                   </tr>\r\n" );
        respuesta.write( "               </table>\r\n" );
        respuesta.write( "               </td>\r\n" );
        respuesta.write( "           </tr>\r\n" );
        respuesta.write( "           <tr>\r\n" );
        respuesta.write( "               <td bgcolor=\"#000000\" height=\"2px\"></td>\r\n" );
        respuesta.write( "           </tr>\r\n" );
        respuesta.write( "           <tr>\r\n" );
        respuesta.write( "               <td>&nbsp; Wolf Village School.<br>\r\n" );
        respuesta.write( "               &nbsp;\r\n" );
        respuesta.write( "               Todos los derechos no reservados (excepto las imágenes).<br>\r\n" );
        respuesta.write( "               <b>&nbsp; 2006</b></td>\r\n" );
        respuesta.write( "           </tr>\r\n" );
        respuesta.write( "       </table>\r\n" );
        respuesta.write( "       </td>\r\n" );
        respuesta.write( "   </tr>\r\n" );
        respuesta.write( "</table>\r\n" );

        respuesta.write( "</center>\r\n" );
        respuesta.write( "</div>\r\n" );
        respuesta.write( "</body>\r\n" );

        respuesta.write( "</html>\r\n" );
    }

    /**
     * Imprime un mensaje de error
     * @param respuesta Respuesta al cliente
     * @param titulo Título del error
     * @param mensaje Mensaje del error
     */
    protected void imprimirMensajeError( PrintWriter respuesta, String titulo, String mensaje )
    {
        respuesta.write( "                      <p class=\"error\"><b>Ha ocurrido un error!:<br>\r\n" );
        respuesta.write( "                      </b>" + titulo + "</p><p>" + mensaje + ". </p>\r\n" );
        respuesta.write( "                      <p>Intente la \r\n" );
        respuesta.write( "                      operación nuevamente. Si el problema persiste, contacte \r\n" );
        respuesta.write( "                      al administrador del sistema.</p>\r\n" );
        respuesta.write( "                      <p><a href=\"index.htm\">Volver a la página principal</a>\r\n" );
    }

    /**
     * Imprime un mensaje de error
     * @param respuesta Respuesta al cliente
     * @param titulo Título del error
     * @param exception Excepción de error
     * @param mensaje Mensaje del error
     */
    protected void imprimirMensajeError( PrintWriter respuesta, String titulo, String mensaje, Exception exception )
    {
        respuesta.write( "                      <p class=\"error\"><b>Ha ocurrido un error!:<br>\r\n" );
        respuesta.write( "                      </b>" + titulo + "</p><p>" + mensaje + ". Mas Información:<br>" );
        exception.printStackTrace( respuesta );
        respuesta.write( "</p>\r\n" );
        respuesta.write( "                      <p>Intente la \r\n" );
        respuesta.write( "                      operación nuevamente. Si el problema persiste, contacte \r\n" );
        respuesta.write( "                      al administrador del sistema.</p>\r\n" );
        respuesta.write( "                      <p><a href=\"index.htm\">Volver a la página principal</a>\r\n" );
    }

    /**
     * Imprime un mensaje de éxito
     * @param respuesta Respuesta al cliente
     * @param titulo Título del mensaje
     * @param mensaje Contenido del mensaje
     */
    protected void imprimirMensajeOk( PrintWriter respuesta, String titulo, String mensaje )
    {
        respuesta.write( "                      <p class=\"ok\"><b>Operación exitosa:<br>\r\n" );
        respuesta.write( "                      </b>" + titulo + "</p><p>" + mensaje + ". </p>\r\n" );
        respuesta.write( "                      <p><a href=\"index.htm\">Volver a la página principal</a>\r\n" );
    }

    // -----------------------------------------------------------------
    // Métodos Abstractos
    // -----------------------------------------------------------------

    /**
     * Devuelve el título de la página para el Header
     * @param request Pedido del cliente
     * @return Título de la página para el Header
     */
    public abstract String darTituloPagina( HttpServletRequest request );

    /**
     * Devuelve el nombre de la imagen para el título de la página en el Header
     * @param request Pedido del cliente
     * @return Nombre de la imagen para el título de la página en el Header
     */
    public abstract String darImagenTitulo( HttpServletRequest request );

    /**
     * Escribe el contenido de la página
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepción de error al escribir la respuesta
     */
    public abstract void escribirContenido( HttpServletRequest request, HttpServletResponse response ) throws IOException;

}
