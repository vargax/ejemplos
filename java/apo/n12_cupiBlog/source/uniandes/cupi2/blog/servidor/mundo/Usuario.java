/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiBlog
 * Autor: Luis Ricardo Ruiz Rodríguez - 01-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.blog.servidor.mundo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import uniandes.cupi2.blog.comun.Articulo;
import uniandes.cupi2.blog.comun.Comentario;
import uniandes.cupi2.blog.comun.Comunicacion;
import uniandes.cupi2.blog.comun.InfoUsuario;
import uniandes.cupi2.blog.excepciones.CupiBlogPersistenciaException;
import uniandes.cupi2.blog.excepciones.CupiBlogProtocoloException;

/**
 * Clase que representa al hilo de comunicación con el cliente del blog<br>
 * <b>inv:</b> El vínculo con la clase principal debe existir.<br>
 * Los canales de comunicación debe estar inicializado.<br>
 */
public class Usuario extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * la información de usuario del cliente
     */
    private InfoUsuario usuario;

    /**
     * El administrador de las salas y usuarios
     */
    private BlogServidor servidor;

    /**
     * El canal de comunicación con el cliente
     */
    private Socket socket;

    /**
     * El canal de escritura en la comunicación con el cliente
     */
    private PrintWriter out;

    /**
     * El canal de lectura en la comunicación con el cliente
     */
    private BufferedReader in;
    
    /**
     * Indica si la conexión del usuario se encuentra activa
     */
    private boolean conectado;
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    public Usuario(BlogServidor servidorP, Socket socketP) throws IOException {
    	socket = socketP;
    	servidor = servidorP;
    	out = new PrintWriter(socket.getOutputStream(),true);
    	in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	conectado = true;
    	verificarInvariante();
    }
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Refresca la lista de artículos disponible
     * @throws ParseException 
     * @throws SQLException 
     */
    public void refrescarArticulos(ArrayList<Articulo> articulos) throws SQLException, ParseException {
		out.println("ARTICULOS;;;"+articulos.size());
		for (int i = 0; i < articulos.size(); i++) {
			Articulo tempArticulo = articulos.get(i);
			out.println("ARTICULO;;;"+tempArticulo.darUsuario()+":::"+tempArticulo.darTitulo()+":::"
					+tempArticulo.darCategoria()+":::"+tempArticulo.darContenido()+":::"+tempArticulo.darFechaPublicacionP());
		}
    }
    /**
     * Inicia el proceso del usuario y realiza todas las operaciones necesarias mientras este dure
     */
    public void run() {
		while(conectado) {
			try {
				String protocolo = in.readLine();
				System.out.println("<"+protocolo+"> --> IN");
// Registrar un usuario -----------------------------------------------
				if (protocolo.startsWith("REGISTRAR")) {
		    		System.out.println("PROTOCOLO: Solicitud de registro...");
		    		String parametro = (protocolo.split(";;;"))[1];
		        	String[] parametros = parametro.split(":::");
		    		try {
						usuario = servidor.registrarUsuario(parametros[0], parametros[1], parametros[2]);
						String respuesta = "LOGIN;;;"+usuario.darNombreUsuario()+":::"+usuario.darNombre()+":::"+usuario.darApellidos();
						out.println(respuesta);
						System.out.println("OUT --> <"+respuesta+">");
						System.out.println("PROTOCOLO: Registro exitoso de "+usuario.darNombreUsuario());
					} catch (CupiBlogPersistenciaException e) {
						String respuesta = "ERROR;;;"+e.getMessage();
						out.println(respuesta);
						System.out.println("OUT --> <"+respuesta+">");
						System.out.println("PROTOCOLO: Registro Fallido <"+e.getMessage()+">");
					}
// Login de un usuario -----------------------------------------------		    		
		    	} else if (protocolo.startsWith("LOGIN")) {
		    		System.out.println("PROTOCOLO: Solicitud de ingreso...");
		    		try {
		    			String parametro = (protocolo.split(";;;"))[1];
		    			String[] parametros = parametro.split(":::");
			    		try {
			    			usuario = servidor.loginUsuario(parametros[0]);
			    			String respuesta =  "LOGIN;;;"+usuario.darNombreUsuario()+":::"+usuario.darNombre()+":::"+usuario.darApellidos();
			    			out.println(respuesta);
			    			System.out.println("OUT --> <"+respuesta+">");
			    			System.out.println("PROTOCOLO: Login exitoso de "+usuario.darNombreUsuario());
			    			servidor.refrescarUsuariosConectados();
			    		} catch (CupiBlogPersistenciaException e) {
			    			String respuesta = "ERROR;;;"+e.getMessage();
			    			out.println(respuesta);
			    			System.out.println("OUT --> <"+respuesta+">");
			    			System.out.println("PROTOCOLO: Login fallido <"+e.getMessage()+">");
			    		}
		    		} catch (Exception e) {
		    			String respuesta = "ERROR;;;"+e.getMessage();
		    			out.println(respuesta);
		    			System.out.println("OUT --> <"+respuesta+">");
		    			System.out.println("PROTOCOLO: Login fallido <"+e.getMessage()+">");
		    		}
// Listar Artículos -----------------------------------------------		    		
		    	} else if (protocolo.startsWith("LISTA_ARTICULOS")) {
		    		ArrayList<Articulo> articulos = servidor.listarArticulos();
		    		String respuesta = "ARTICULOS;;;"+articulos.size();
		    		out.println(respuesta);
		    		System.out.println("OUT --> <"+respuesta+">");
		    		for (int i = 0; i < articulos.size(); i++) {
		    			Articulo tempArticulo = articulos.get(i);
		    			respuesta = "ARTICULO;;;"+tempArticulo.darUsuario()+":::"+tempArticulo.darTitulo()+":::"
		    					+tempArticulo.darCategoria()+":::"+tempArticulo.darContenido()+":::"+tempArticulo.darFechaPublicacionP();
		    			out.println(respuesta);
		    			System.out.println("OUT --> <"+respuesta+">");
		    		}
// Comentarios Artículo -----------------------------------------------		    		
		    	} else if (protocolo.startsWith("COMENTARIOS_ARTICULO")) {
		    		String parametro = (protocolo.split(";;;"))[1];
	    	    	String[] parametros = parametro.split(":::");
	    	    	try {
	    	    		ArrayList<Comentario> comentarios = servidor.listarComentarios(parametros[1]);
	    	    		String respuesta = "COMENTARIOS;;;"+comentarios.size();
	    	    		out.println(respuesta);
	    	    		System.out.println("OUT --> <"+respuesta+">");
	    	    		for (int i = 0; i < comentarios.size(); i++) {
	    	    			Comentario tempComentario = comentarios.get(i);
	    	    			respuesta = "COMENTARIO;;;"+tempComentario.darUsuario()+":::"+tempComentario.darContenido()+":::"
	    	    					+tempComentario.darFechaPublicacionP();
	    	    			out.println(respuesta);
	    	    			System.out.println("OUT --> <"+respuesta+">");
	    	    		}
	    	    	} catch (CupiBlogPersistenciaException e) {
	    	    		String respuesta = "ERROR;;;"+e.getMessage();
	    	    		out.println(respuesta);
	    	    		System.out.println("OUT --> <"+respuesta+">");
	    	    		e.printStackTrace();
	    	    	}
// Publicar un artículo -----------------------------------------------	    	    	
		    	} else if (protocolo.startsWith("PUBLICAR_ARTICULO")) {
		    		String parametro = (protocolo.split(";;;"))[1];
	    	    	String[] parametros = parametro.split(":::");
	    	    	try {
	    	    		servidor.publicarArticulo(usuario.darNombreUsuario(), parametros[0], parametros[1], parametros[2]);
	    	    	} catch (CupiBlogPersistenciaException e) {
	    	    		String respuesta = "ERROR;;;"+e.getMessage();
	    	    		out.println(respuesta);
	    	    		System.out.println("OUT --> <"+respuesta+">");
	    	    	}
// Publicar un comentario -----------------------------------------------	    	    	
		    	} else if (protocolo.startsWith("PUBLICAR_COMENTARIO")) {
		    		String parametro = (protocolo.split(";;;"))[1];
	    	    	String[] parametros = parametro.split(":::");
	    	    	try {
	    	    		ArrayList<Comentario> comentarios = servidor.publicarComentario(usuario.darNombreUsuario(), parametros[1], parametros[2]);
	    	    		String respuesta = "COMENTARIOS;;;"+comentarios.size();
	    	    		out.println(respuesta);
	    	    		System.out.println("OUT --> <"+respuesta+">");
	    	    		for (int i = 0; i < comentarios.size(); i++) {
	    	    			Comentario tempComentario = comentarios.get(i);
	    	    			respuesta = "COMENTARIO;;;"+tempComentario.darUsuario()+":::"+tempComentario.darContenido()+":::"
	    	    					+tempComentario.darFechaPublicacionP();
	    	    			out.println(respuesta);
	    	    			System.out.println("OUT --> <"+respuesta+">");
	    	    		}
	    	    	} catch (CupiBlogPersistenciaException e) {
	    	    		String respuesta = e.getMessage();
	    	    		out.println(respuesta);
	    	    		System.out.println("OUT --> <"+respuesta+">");
	    	    	}
// Buscar por categoría -----------------------------------------------	    	    	
		    	} else if (protocolo.startsWith("BUSQUEDA_CATEGORIA")) {
		    		String parametro = (protocolo.split(";;;"))[1];
	    	    	ArrayList<Articulo> articulos = servidor.buscarPorCategoria(parametro);
	    	    	String respuesta = "ARTICULOS;;;"+articulos.size();
		    		out.println(respuesta);
		    		System.out.println("OUT --> <"+respuesta+">");
		    		for (int i = 0; i < articulos.size(); i++) {
		    			Articulo tempArticulo = articulos.get(i);
		    			respuesta = "ARTICULO;;;"+tempArticulo.darUsuario()+":::"+tempArticulo.darTitulo()+":::"
		    					+tempArticulo.darCategoria()+":::"+tempArticulo.darContenido()+":::"+tempArticulo.darFechaPublicacionP();
		    			out.println(respuesta);
		    			System.out.println("OUT --> <"+respuesta+">");
		    		}
// Solicitar Estadísticas -----------------------------------------------		    		
		    	} else if (protocolo.startsWith("ESTADISTICAS")) {
		    		try {
		    			int[] estadisticas = servidor.estadisticasUsuario(usuario.darNombreUsuario());
		    			String respuesta = "ESTADISTICAS;;;"+estadisticas[0]+":::"+estadisticas[1];
		    			out.println(respuesta);
		    			System.out.println("OUT --> <"+respuesta+">");
		    		} catch (SQLException e) {
		    			String respuesta = "ERROR;;;"+"No se han podido recuperar las estadísticas del usuario";
		    			out.println(respuesta);
		    			System.out.println("OUT --> <"+respuesta+">");
		    			throw e;
		    		}
// Cerrar sesión --------------------------------------------------------		    		
		    	} else if (protocolo.startsWith("LOGOUT")) {
		    		conectado = false;
		    		servidor.logoutUsuario(this);
		    		desconectar();
// Cualquier otro comando -----------------------------------------------		    		
		    	} else {
		    		System.out.println("PROTOCOLO: Comando desconocido...");
		    		String respuesta = "ERROR;;;Comando desconocido";
		    		out.println(respuesta);
		    		System.out.println("OUT --> <"+respuesta+">");
		    	}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
    /**
     * Cierra los flujos de entrada y salida
     * @throws IOException 
     */
    public void desconectar() throws IOException {
		try {
			conectado = false;
	    	out.println("LOGOUT");
			out.close();
			in.close();
	    	socket.close();
		} catch (Exception e) {
			
		}
    }
    /**
     * Devuelve la información del usuario
     */
    public InfoUsuario darInfoUsuario() {
    	return usuario;
    }
    /**
     * 
     */
    public String toString() {
    	if (usuario != null) return usuario.toString();
    	return "Usuario sin identificar";
    }
    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------
    /**
     * Verifica la invariante de la clase<br>
     * <b>inv: </b> servidor != null<br>
     * socket != null<br>
     * in != null<br>
     * out != null<br>
     */
    private void verificarInvariante( )
    {
        assert servidor != null : "El vínculo con el servidor debe estar inicializado";
        assert socket != null : "El canal debe estar inicializado";
        assert in != null : "El buffer de lectura debe estar inicializado";
        assert out != null : "El buffer de escritura debe estar inicializado";
    }
}