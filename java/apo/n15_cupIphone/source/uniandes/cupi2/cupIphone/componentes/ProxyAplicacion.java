package uniandes.cupi2.cupIphone.componentes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JPanel;

import uniandes.cupi2.collections.lista.Lista;
import uniandes.cupi2.cupIphone.componentes.excepciones.EjecucionException;
import uniandes.cupi2.cupIphone.componentes.excepciones.InstalacionException;
import uniandes.cupi2.cupIphone.core.Core;

/**
 * Modela una aplicación instalada en el core
 * 
 * @author Yeisson Oviedo
 *
 */
public class ProxyAplicacion implements Serializable {

	//-----------------------------------------------------------------
	// Constantes
	//-----------------------------------------------------------------

	/**
	 * Ruta al archivo de configuración
	 */
	private static final String ARCHIVO_CONF = "data/config.properties";

	/**
	 * Propiedad que indica la clase principal
	 */
	private static final String PROP_CLASE_PRINCIPAL = "componente.clase";

	/**
	 * Propiedad que indica el icono a usar
	 */
	private static final String PROP_ICONO = "componente.icono";

	/**
	 * Propiedad que indica el nombre en pantalla
	 */
	private static final String PROP_NOMBRE_PANTALLA = "componente.nombre";

	/**
	 * Propiedad que indica el id de la aplicación
	 */
	private static final String PROP_ID = "componente.id";

	/**
	 * Propiedad que indica las dependencias de la aplicación
	 */
	private static final String PROP_DEPENDENCIAS = "componente.dependencias";

	/**
	 * Propiedad que indica las librerías de la aplicación
	 */
	private static final String PROP_LIBS = "componente.lib";

	/**
	 * Nombre del método que retorna la instancia de la clase principal
	 */
	private static final String METODO_DAR_INSTANCIA = "darInstancia";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	//-----------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------

	/**
	 * URL con el icono de la aplicación
	 */
	private transient URL urlIcono;

	/**
	 * Identificador de la aplicación
	 */
	private String id;

	/**
	 * Nombre en pantalla de la aplicación
	 */
	private String nombreEnPantalla;

	/**
	 * Archivo jar desde donde se carga esta aplicación
	 */
	private File jar;

	/**
	 * ClassLoader de la aplicación
	 */
	private transient URLClassLoader classLoader;

	/**
	 * Instancia de {@link Class} que representa la clase
	 * principal de esta aplicación
	 */
	private transient Class<? extends IAplicacion> clasePrincipal;	

	/**
	 * Listado de dependencias
	 */
	private Lista<ProxyAplicacion> dependencias;

	/**
	 * Referencia al {@link ICore núcleo del sistema}
	 */
	private Core core;

	/**
	 * Instancia cargada de la aplicación
	 */
	private transient IAplicacion instancia;

	private File dirDatos;

	//-----------------------------------------------------------------
	// Constructor
	//-----------------------------------------------------------------

	/**
	 * Constructor
	 * @param jar
	 * @param core 
	 * @throws InstalacionException 
	 */
	public ProxyAplicacion(File jar, Core core) throws InstalacionException {		
		this.jar = jar;
		this.core = core;
		this.dependencias = new Lista<ProxyAplicacion>();
		cargar();
	}

	//-----------------------------------------------------------------
	// Métodos
	//-----------------------------------------------------------------

	/**
	 * Retorna la ubicación del icono correspondiente a la aplicación a manera
	 * de URL apuntando a la imagen dentro del jar de la aplicación.
	 * @return URL apuntando al icono dentro del jar de la aplicación
	 */
	public URL darIcono() {
		return urlIcono;
	}

	/**
	 * Retorna el id de la aplicación
	 * @return ID de la aplicación
	 */
	public String darID() {
		return id;
	}

	/**
	 * Retorna el nombre para mostrar de la aplicación
	 * @return Nombre que se debe mostrar en la pantalla
	 */
	public String darNombreEnPantalla() {
		return nombreEnPantalla;
	}

	/**
	 * Retorna el jar de la aplicación
	 * @return Archivo Jar
	 */
	public File darJar() {
		return jar;

	}

	/**
	 * Retorna el directorio de datos que le corresponde a esta aplicación
	 * @return Instancia de File apuntando al directorio de datos
	 */
	public File darDirDatos() {
		return dirDatos;
	}

	public String toString ()
	{
		return nombreEnPantalla + " - " + id; 
	}

	/**
	 * Carga la aplicación desde el jar
	 * @throws InstalacionException
	 */
	public void cargar() throws InstalacionException {
		try {
			//Inicializar el classLoader
			URL url;
			url = jar.toURI().toURL();
			classLoader = URLClassLoader.newInstance(new URL[]{url}, getClass().getClassLoader());

			//Cargar el jar
			Properties p = new Properties();			
			InputStream is = classLoader.getResourceAsStream(ARCHIVO_CONF);			
			p.load(is);

			//Cargar las propiedades generales
			urlIcono = classLoader.getResource(p.getProperty(PROP_ICONO));
			if (urlIcono == null)
				throw new InstalacionException("No es posible localizar el icono de la aplicación");
			nombreEnPantalla = p.getProperty(PROP_NOMBRE_PANTALLA);
			id = p.getProperty(PROP_ID);

			//Solo se debe verificar el directorio de datos una sola vez
			if(dirDatos == null){
				dirDatos = new File (Core.DIR_APPS + "/" + id + "/");
				if (!dirDatos.exists())
					dirDatos.mkdir();
			}
			
			String sDeps = p.getProperty(PROP_DEPENDENCIAS);
			if (!sDeps.isEmpty()){
				String[] deps = sDeps.split(";");
				for (String d: deps)
				{
					ProxyAplicacion dep = core.buscarAplicacion(d);
					if (dep == null)
						throw new InstalacionException("Una de las dependencias no puede ser resuelta");
					classLoader = URLClassLoader.newInstance(classLoader.getURLs(), dep.classLoader);
					dependencias.agregar(dep);
				}				
			}	
			
			String sLibs = p.getProperty(PROP_LIBS);
			if (!sLibs.isEmpty()){
				File libsDir = new File(dirDatos, "lib/");
				if (!libsDir.exists())
					libsDir.mkdir();
				String[] libs = sLibs.split(";");
				ArrayList<URL> urls = new ArrayList<URL>();
				for (int i = 0; i < libs.length; i++) {
					urls.add(copiarArchivo(classLoader.getResourceAsStream(libs[i]), new File(libsDir, "lib" + i + ".jar")).toURI().toURL());					
				}
				for (URL u: classLoader.getURLs())
					urls.add(u);
				classLoader = URLClassLoader.newInstance(urls.toArray(new URL[0]), classLoader.getParent());
			}
			is.close();			

			//Cargar la clase principal
			clasePrincipal = classLoader.loadClass(p.getProperty(PROP_CLASE_PRINCIPAL)).asSubclass(IAplicacion.class);			
			Method m = clasePrincipal.getMethod(METODO_DAR_INSTANCIA);

			if (!m.getReturnType().equals(IAplicacion.class))
				throw new InstalacionException("El método darInstancia() no retorna IAplicacion");			
			if(!Modifier.isStatic(m.getModifiers()))
				throw new InstalacionException("El método darInstancia() no es estático");

		}catch (MalformedURLException e) {
			throw new InstalacionException("Error al intentar cargar la aplicación", e);
		}catch (IOException e) {
			throw new InstalacionException("El archivo de configuración no se puede cargar", e);
		}catch (ClassNotFoundException e) {
			throw new InstalacionException("La clase principal no se puede cargar", e);
		}catch(ClassCastException e){
			throw new InstalacionException("La clase principal no implementa IAplicacion", e);
		}catch (NoSuchMethodException e) {
			throw new InstalacionException("La clase principal no incluye un método darInstancia()", e);		
		}catch (SecurityException e) {			
			e.printStackTrace();
			throw new InstalacionException("SecurityException", e);
		}catch(NullPointerException e){
			e.printStackTrace();
			throw new InstalacionException("NullPointerException - Verifique el archivo de configuración", e);
		}
	}
	
	private File copiarArchivo(InputStream ins, File dest) throws IOException {
		FileOutputStream fos = null;
		try{			
			dest.createNewFile();			
			fos = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int i = ins.read(buffer);
			while (i != -1){			
				fos.write(buffer, 0, i);
				i = ins.read(buffer);
			}		
			return dest;
		}
		catch(IOException e)
		{
			throw e;
		}
		finally{
			try{
				ins.close();
				fos.close();
			}catch (Throwable t){}
		}
	}


	/**
	 * Descarga y elimina todos los datos correspondientes a esta aplicación
	 * @throws InstalacionException 
	 */
	public void eliminar() throws InstalacionException {		 		
		clasePrincipal = null;
		urlIcono = null;
		classLoader  = null;

		//Intentar borrar el jar un número determinado de veces
		for (int i = 0; i < 2 ;i++){
			System.gc();
			if (jar.delete())
				return;
		}				
		jar = null;
	}

	/**
	 * Retorna el panel principal de la aplicación
	 * @return EL panel principal
	 * @throws EjecucionException 
	 */
	public JPanel ejecutar() throws EjecucionException {
		if (instancia == null)
			cargarInstancia();		
		try{
			instancia.iniciarEjecucion();			
			return instancia.darPanelPrincipal();
		}catch(Throwable t){
			throw new EjecucionException("Error en el componente: " + id , t);
		}
	}

	/**
	 * Si se ha cargado una instancia de la aplicación, se le envía el 
	 * mensaje de terminación, así no haya estado ejecutándose 
	 */
	public void terminar() {
		if (instancia != null)
		{
			try{
				instancia.terminarEjecucion();
			}catch (Throwable t){
				mostrarErrorComponente(t);
			}
		}
	}

	/**
	 * Retorna la instancia del modelo de esta aplicación
	 * @return La instancia de modelo
	 * @throws EjecucionException 
	 */
	public Object darInstanciaModelo() throws EjecucionException{
		if (instancia == null)
			cargarInstancia();
		try{
		return instancia.darInstanciaModelo();
		}catch (Throwable t){
			mostrarErrorComponente(t);
			throw new EjecucionException("Error en el componente: " + id, t);
		}
	}

	/**
	 * Indica si una aplicación es dependencia de la aplicación sobre la que
	 * se invoca el método
	 * @param ap Aplicación a verificar
	 * @return True si ap es dependencia de esta aplicación
	 */
	public boolean esDependencia(ProxyAplicacion ap) {
		for (int i = 0; i < dependencias.darLongitud(); i++)
			if (dependencias.darElemento(i).equals(ap))
				return true;
		return false;
	}

	//-----------------------------------------------------------------
	// Métodos auxiliares
	//-----------------------------------------------------------------

	public boolean equals(Object o){
		if (o instanceof ProxyAplicacion && ((ProxyAplicacion)o).darID().equals(id))
			return true;
		return false;
	}	

	/**
	 * Carga una instancia de la clase principal y le pasa
	 * una referencia al core
	 * @throws EjecucionException
	 */
	private void cargarInstancia() throws EjecucionException {
		try{
			Method m = clasePrincipal.getMethod(METODO_DAR_INSTANCIA);
			instancia = (IAplicacion)m.invoke(null);
			instancia.cambiarCore(core);						
		}catch (Throwable t){
			throw new EjecucionException("No es posible instanciar la clase principal", t);
		}
	}
	
	private void mostrarErrorComponente(Throwable t) {		
		System.err.println();
		System.err.println("================================================");
		System.err.println("ERROR EN EL COMPONENTE: " + id);
		System.err.println("SE PRODUJO LA EXCEPCIÓN: ");
		System.err.println();
		t.printStackTrace();
		System.err.println();
		System.err.println("TERMINA TRAZA DEL ERROR. COMPONENTE: " + id);
		System.err.println("================================================");		
	}
}
