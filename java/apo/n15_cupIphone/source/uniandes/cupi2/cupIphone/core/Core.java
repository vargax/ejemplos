/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Core.java 1131 2010-09-14 20:06:52Z carl-veg $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n15_cupIphone
 * Autor: Yeisson Oviedo - 23-feb-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupIphone.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.swing.JPanel;

import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.lista.Lista;
import uniandes.cupi2.cupIphone.componentes.ProxyAplicacion;
import uniandes.cupi2.cupIphone.componentes.excepciones.EjecucionException;
import uniandes.cupi2.cupIphone.componentes.excepciones.InstalacionException;

/**
 *  
 */
public class Core implements ICore, Serializable
{
	//-----------------------------------------------------------------
	// Constantes
	//-----------------------------------------------------------------

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Directorio de aplicaciones
	 */
	public static final String DIR_APPS = "data/apps/";

	//-----------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------
	
	private Lista<ProxyAplicacion> aplicaciones;

	/**
	 * Referencia a la aplicación ejecutando actualmente
	 */
	private transient ProxyAplicacion aplicacionActual = null;
	
	//-----------------------------------------------------------------
	// Constructores
	//-----------------------------------------------------------------

	/**
	 *  Constructor
	 */
	public Core( )
	{
		aplicaciones = new Lista<ProxyAplicacion>();
	}

	//-----------------------------------------------------------------
	// Métodos
	//-----------------------------------------------------------------

	/**
	 * Retorna un iterador sobre el listado de aplicaciones instaladas
	 * @return Las aplicaciones
	 */
	public Iterador<ProxyAplicacion> darAplicaciones() {
		return aplicaciones.darIterador();
	}

	/**
	 * Intenta instalar una aplicación a partir
	 * del jar recibido
	 * @param archivoJar Archivo jar a instalar
	 * @return La aplicación instalada
	 * @throws InstalacionException 
	 */
	public ProxyAplicacion instalarProxyAplicacion(File archivoJar) throws InstalacionException {
		File jar = null;
		try {
			jar = copiarArchivo(archivoJar, DIR_APPS, crearSufijo());
		} catch (IOException e) {
			throw new InstalacionException("No se puede copiar el jar en la carpeta de aplicaciones");
		}
		try{
			ProxyAplicacion app = new ProxyAplicacion(jar, this);
			if (aplicaciones.buscar(app) != -1)
				throw new InstalacionException("Ya existe una aplicación con el mismo ID: " + app.darID());
			
			aplicaciones.agregar(app);
			return app;
		}catch (InstalacionException e){
			//intentar borrar el jar
			jar.delete();			
			throw e;
		}
	}

	/**
	 * Des instala una aplicación
	 * @param app Aplicación
	 * @throws InstalacionException 
	 */
	public void eliminarProxyAplicacion(ProxyAplicacion app) throws InstalacionException {
		for (int i = 0; i < aplicaciones.darLongitud(); i++)
			if (aplicaciones.darElemento(i).esDependencia(app))
				throw new InstalacionException("La aplicación es dependencia de otros componentes");
		app.eliminar();
		aplicaciones.eliminar(app);
	}
	
	/**
	 * Ejecuta la aplicación con el ID especificado
	 * @param idAplicacion
	 * @return Panel principal de la aplicación
	 * @throws EjecucionException 
	 */
	public JPanel ejecutarAplicacion(String idAplicacion) throws EjecucionException {		
		ProxyAplicacion ap = buscarAplicacion(idAplicacion);
		if (ap == null)
			throw new EjecucionException("Error al ejecutar la aplicación");
		aplicacionActual = ap;
		return ap.ejecutar();		
	}

	/**
	 * Intenta terminar la aplicación actual, si existe alguna corriendo.
	 * No hace nada en caso contrario
	 */
	public void terminarAplicacionActual() {
		if (aplicacionActual != null){
			aplicacionActual.terminar();
			aplicacionActual = null;
		}
	}

	/**
	 * Retorna la instancia del modelo de la aplicación
	 * indicada
	 */
	public Object darInstanciaModelo(String idAplicacion) throws EjecucionException
	{
		ProxyAplicacion ap = null;
		ap = buscarAplicacion(idAplicacion);
		ProxyAplicacion temp = aplicacionActual;
		if (ap == null)
			throw new EjecucionException("No existe ninguna aplicación con el ID suministrado");
		try{
			aplicacionActual = ap;
			Object res = ap.darInstanciaModelo();
			return res;
		}catch (Throwable t)
		{
			throw new EjecucionException(t.getMessage(), t);
		}finally{
			aplicacionActual = temp;
		}	
	}
	
	/**
	 * Inicializa el core luego de cargarse 
	 */
	public void inicializar() {
		//Intentar borrar los jars y las carpetas de datos
		//que no se estén usando
		File dir = new File(DIR_APPS);
		File[] files = dir.listFiles();
		for (File f: files){
			//Si no se está usando, eliminarlo
			boolean usado = false;
			for (int i = 0; i < aplicaciones.darLongitud() && !usado; i++){
				ProxyAplicacion ap = aplicaciones.darElemento(i);
				if (f.equals(ap.darJar()) || f.equals(ap.darDirDatos())){
					usado = true;
				}
			}
			//Tratar de eliminar, si no se puede, no se hace nada
			if (!usado)
				borrarArchivo(f);
		}
		
		//Inicializar los jars de todas las aplicaciones
		for (int i = 0; i < aplicaciones.darLongitud(); i++){
			try {
				aplicaciones.darElemento(i).cargar();
			} catch (InstalacionException e) {				
				e.printStackTrace();
				System.err.println();
				System.err.println("La aplicación " + aplicaciones.darElemento(i).darID() + " no se pudo cargar.");
				System.err.println("La aplicación fue eliminada");
				aplicaciones.eliminar(i);

			}
		}
	}

	/**
	 * Busca una aplicación y la retorna
	 * @param id Id de la aplicación a buscar
	 * @return Referencia a la aplicación, null si no la encuentra
	 */
	public ProxyAplicacion buscarAplicacion(String id) {
		for (int i = 0; i < aplicaciones.darLongitud(); i++)
			if (aplicaciones.darElemento(i).darID().equals(id))
				return aplicaciones.darElemento(i);
		return null;
	}
		
	public File darDirectorioDatos(){
		if (aplicacionActual != null)
			return aplicacionActual.darDirDatos();
		return null;
	}

	//-----------------------------------------------------------------
	// Métodos auxiliares
	//-----------------------------------------------------------------

	/**
	 * Método utilitario para copiar un archivo a una nueva ubicación
	 */
	private File copiarArchivo(File archivoJar, String nuevoDir, String sufijo) throws IOException {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try{
			File f = new File(nuevoDir, archivoJar.getName() + sufijo);
			if (f.exists())
				throw new IOException();
			f.createNewFile();
			fis = new FileInputStream(archivoJar);
			fos = new FileOutputStream(f);
			byte[] buffer = new byte[1024];
			int i = fis.read(buffer);
			while (i != -1){			
				fos.write(buffer, 0, i);
				i = fis.read(buffer);
			}		
			return f;
		}
		catch(IOException e)
		{
			throw e;
		}
		finally{
			try{
				fis.close();
				fos.close();
			}catch (Throwable t){}
		}
	}
	
	/**
	 * Método utilitario que permite borrar un archivo
	 * Si se está borrando una carpeta, se borra todo su contenido
	 * recursivamente
	 * @param f Archivo o carpeta a borrar
	 */
	private void borrarArchivo(File f) {
		boolean dir = f.isDirectory();
		if (dir){
			File[] archivos = f.listFiles();
			for (File f1: archivos)
				borrarArchivo(f1);			
		}
		System.out.println(f.delete()? (dir ? "Directorio " : "Archivo ") + f.toString() + " eliminado exitosamente" : "No de pudo borrar el archivo " + f.toString() );		
	}
	
	/**
	 * Utilitario que permite crear sufijos para archivos que se
	 * están copiando. Se implementa como un método independiente para
	 * facilitar su modificación en caso que sea necesario
	 * @return
	 */
	private String crearSufijo() {		
		return "_" + (new Date().getTime());
	}
}