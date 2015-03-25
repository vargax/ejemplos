package uniandes.cupi2.componenteCompartir.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import estructurasDatos.IListaEncadenada;
import estructurasDatos.ListaEncadenada;
import estructurasDatos.ListaEncadenadaOrdenada;
import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.componenteContactos.mundo.Contacto;
import uniandes.cupi2.componenteContactos.mundo.IComponenteContactos;
import uniandes.cupi2.cupIphone.componentes.excepciones.EjecucionException;
import uniandes.cupi2.cupIphone.core.ICore;
import uniandes.cupi2.mailer.ArchivoAdjunto;
import uniandes.cupi2.mailer.Email;
import uniandes.cupi2.mailer.EnvioException;

public class ComponenteCompartir extends Observable implements IComponenteCompartir {
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	/**
	 * La configuración por defecto de cupiShare
	 */
	public final static String NOMBRE = "My cupiPhone";
	public final static int PUERTO = 9876;
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/**
	 * El núcleo del cupiPhone
	 */
	private ICore core;
	/**
	 * El directorio de la aplicación
	 */
	private File directorio;
	/**
	 * Una lista para los nombres de los archivos en el directorio local
	 */
	private IListaEncadenada<String> archivosLocales;
	/**
	 * Almacena las estadísticas de los archivos
	 */
	private IListaEncadenada<Archivo> estadisticas;
	/**
	 * El número de archivos que se estan enviando en este momento
	 */
	private int enviando;
	/**
	 * El tamaño de los archivos que se están enviando en este momento
	 */
	private float tamaño;
	//-----------------------------------------------------------------
    // Atributos Servidor
    //-----------------------------------------------------------------
	/**
	 * El puerto de escucha de la aplicación
	 */
	private int puerto;
	/**
	 * El serverSocket encargado de recibir las conexiones entrantes
	 */
	private ServerSocket socketServidor;
	/**
	 * El thread para el servidor
	 */
	private Thread threadServidor;
	/**
	 * Una lista para almacenar a los clientes conectados
	 */
	private IListaEncadenada<Cliente> clientes;
	//-----------------------------------------------------------------
    // Atributos Cliente
    //-----------------------------------------------------------------
	/**
	 * El nombre del cliente
	 */
	private String nombre;
	/**
	 * El servidor al cual se encuentra conectado
	 */
	private Servidor servidor;
	/**
	 * Una lista para almacenar los nombres de los archivos del servidor
	 */
	private IListaEncadenada<String> archivosServidor;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	/**
	 * Crea una nueva instacia del componente compartir
	 * @param coreP: Una referencia al núcleo de la aplicación
	 */
	public ComponenteCompartir(ICore coreP) {
		core = coreP;
		directorio = core.darDirectorioDatos();
		refrescarLocales();
		clientes = new ListaEncadenada<Cliente>();
		enviando = 0;
		tamaño = 0;
		cargarConfiguracion();
	}
	//-----------------------------------------------------------------
    // Gestión conexiones
    //-----------------------------------------------------------------
	/**
	 * Inicializa el socket de escucha y espera conexiones de los clientes
	 */
	public void esperarConexiones() {
		System.out.println("MAIN: Instanciando el proceso para el servidor...");
		threadServidor = new Thread() {
			private boolean conectado = true;
			public void run () {
				try {
					System.out.println("MAIN: Iniciando socket de escucha en "+puerto);
					socketServidor = new ServerSocket(puerto);
					while (conectado) {
						Socket socketNuevoCliente = socketServidor.accept();
						System.out.println("SOCKET: Solicitud de conexión detectada...");
						Cliente nuevoCliente = new Cliente(ComponenteCompartir.this, socketNuevoCliente);
						nuevoCliente.start();
						
					}
				} catch (Exception e) {
					System.out.println("SERVIDOR - ERROR: No fue posible inicializar el socket");
					e.printStackTrace();
				}
			}
		};
		System.out.println("MAIN: Iniciando el servidor...");
		threadServidor.start();
	}
	//-----------------------------------------------------------------
    // Métodos para la gestión de los clientes (conexiones entrantes)
	// --> administrados por la clase Cliente (Thread)
    //-----------------------------------------------------------------
	/**
	 * Devuelve una lista con los nombres de los archivos disponibles para descargar
	 * @return Lista de Strings con los nombres de los archivos para descargar
	 */
	public IListaEncadenada<String> darNombresArchivos() {
		ListaEncadenadaOrdenada<String> respuesta = new ListaEncadenadaOrdenada<String>();
		
		File[] archivos = directorio.listFiles();
		for (File archivo : archivos)
			if (archivo.isFile()) respuesta.agregar(archivo.getName());
		
		return respuesta;
	}
	/**
	 * Finaliza la conexión con un cliente
	 */
	public void desconectarCliente(Cliente cliente) {
		String nombreCliente = cliente.getNombre();
		System.out.println("MAIN: Solicitud de desconexión de "+nombreCliente);
		boolean eliminado = clientes.eliminar(cliente);
		if (eliminado) {
			System.out.println("MAIN: Cliente "+nombreCliente+" desconectado");
		}
		else {
			System.out.println("MAIN - ERROR: El cliente "+nombreCliente+" no se encontraba registrado en la lista de clientes");
		}		
		notificarObservadores("usuarios");
	}
	/**
	 * Le permite a un cliente registrarse en la aplicación, una vez ha finalizado el
	 * proceso de login.
	 * @param cliente: Una referencia al cliente que se va a registrar
	 */
	public void registrarCliente(Cliente cliente) {
		clientes.agregar(cliente);
		notificarObservadores("usuarios");
	}
	/**
	 * Realiza el registro de las estadísticas de un archivo
	 */
	public void registrarEstadisticas(String nombreArchivo, String usuario) {
		Archivo aRegistrar = null;
		for (Archivo a : estadisticas) {
			if (a.darNombreArchivo().equals(nombreArchivo)) {
				aRegistrar = a;
				break;
			}
		}
		if (aRegistrar == null) {
			aRegistrar = new Archivo(nombreArchivo);
			estadisticas.agregar(aRegistrar);
		}
		aRegistrar.registrarDescarga(usuario);
		notificarObservadores("estadisticas");
		System.out.println("MAIN: Estadística registrada para "+aRegistrar.darNombreArchivo());
	}
	/**
	 * Actualiza los atributos enviando y tamaño
	 */
	public void setDescargas(float tamañoP) {
		if (tamañoP > 0) enviando++;
		else enviando--;
			
		tamaño+=tamañoP;
		
		System.out.println("MAIN: Enviando = "+enviando+" Tamaño = "+tamaño);
		notificarObservadores("descargando");
	}
	//-----------------------------------------------------------------
    // Métodos para la gestión del servidor remoto (conexiones salientes)
	// --> administrados por la clase Servidor (Thread)
    //-----------------------------------------------------------------
	/**
	 * Devuelve el nombre del cliente para conexiones salientes
	 * @return El nombre del cliente
	 */
	public String darNombre() {
		return nombre;
	}
	/**
	 * Devuelve el path de la aplicación
	 */
	public String darPath() {
		return directorio.getPath();
	}
	/**
	 * Finaliza la conexión con el servidor
	 */
	public void desconectarServidor(String nombreServidor) {
			System.out.println("MAIN: Desconectado de "+nombreServidor);
			refrescarRemotos(null);
			servidor.stop();
	}
	//-----------------------------------------------------------------
    // Métodos interfaz
    //-----------------------------------------------------------------
	@Override
	public void conectar(String ip, int puerto) throws UnknownHostException, IOException {
		if (servidor != null) servidor.logout();
		servidor = new Servidor(this, ip, puerto);
		servidor.start();
	}
	@Override
	public void descargar(String nombreArchivo) throws Exception {
		servidor.descargarArchivo(nombreArchivo);
	}
	@Override
	public IListaEncadenada<String> buscarArchivo(String criterioBusqueda) {
		IListaEncadenada<String> respuesta = new ListaEncadenadaOrdenada<String>();
		for(String s : archivosServidor) {
			if (s.startsWith(criterioBusqueda)) respuesta.agregar(s);
		}
		return respuesta;
	}
	@Override
	public IListaEncadenada<String> usuariosConectados() {
		IListaEncadenada<String> respuesta = new ListaEncadenadaOrdenada<String>();
		for(Cliente c : clientes) respuesta.agregar(c.getNombre());
		if(respuesta.darNumeroObjetos()==0) respuesta.agregar("Ningún usuario conectado");
		return respuesta;
	}
	@Override
	public void desconectarUsuario(String nombreUsuario) {
		for (Cliente c : clientes) {
			if (c.getNombre().equals(nombreUsuario)) {
				c.logout();
				break;
			}
		}
	}
	@Override
	public IListaEncadenada<String> estadisticasArchivo(String nombreArchivo) {
		for (Archivo a : estadisticas) {
			if(a.darNombreArchivo().equals(nombreArchivo))
				return a.darDescargas();
		}
		return new ListaEncadenada<String>("Ninguna estadística disponible");
	}
	@Override
	public void enviarCorreo(String nombreArchivo,String email) throws MessagingException {
		File archivo = new File(directorio.getPath()+"/"+nombreArchivo);
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.uniandes.edu.co");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.port","25");
		props.setProperty("mail.smtp.user", "c.vargas124");
		props.setProperty("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(true);
		
		BodyPart texto = new MimeBodyPart();
		texto.setText("Hola! te adjunto "+nombreArchivo);
		
		BodyPart adjunto = new MimeBodyPart();
		adjunto.setDataHandler(new DataHandler(new FileDataSource(archivo.getAbsolutePath())));
		adjunto.setFileName(nombreArchivo);
		
		MimeMultipart mmp = new MimeMultipart();
		mmp.addBodyPart(texto);
		mmp.addBodyPart(adjunto);
		
		MimeMessage mail = new MimeMessage(session);
		mail.setFrom(new InternetAddress("c.vargas124@uniandes.edu.co"));
		mail.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		mail.setSubject(nombreArchivo+" enviado desde "+nombre);
		mail.setContent(mmp);
		
		Transport t = session.getTransport("smtp");
		t.connect("c.vargas124","aufGeregt6");

		Thread.currentThread().setContextClassLoader( getClass().getClassLoader() );
		
		t.sendMessage(mail,mail.getAllRecipients());
		t.close();
		
//		Email mail = new Email();
//		mail.cambiarTitulo(nombreArchivo+" enviado desde "+nombre);
//		mail.cambiarRemitente("c.vargas124@uniandes.edu.co");
//		mail.agregarDestinatario(email);
//		mail.cambiarMensaje("Hola! te adjunto "+nombreArchivo);
//		mail.agregarAdjunto(new ArchivoAdjunto(archivo));
//		mail.enviar("smtp.uniandes.edu.co","c.vargas124@uniandes.edu.co","NordenausSie8");
		
		System.out.println("MAIN: "+nombreArchivo+" enviado a "+email);
	}
	@Override
	public IListaEncadenada<Contacto> darContactos() throws Exception {
		IComponenteContactos contactos = (IComponenteContactos) core.darInstanciaModelo("Componente de Contactos");
		IListaEncadenada<Contacto> respuesta = new ListaEncadenada<Contacto>();
		Iterador<Contacto> i = contactos.darContactos();
		while(i.haySiguiente()) {
			Contacto c = i.darSiguiente();
			if(c.darCorreo() != null && c.darCorreo().length() >= 3)
				respuesta.agregar(c);
		}
		return respuesta;
	}
	@Override
	public String[] darConfiguracion() {
		String[] respuesta = {nombre,puerto+""};
		return respuesta;
	}
	@Override
	public void definirConfiguracion(String nuevoNombre, String nuevoPuerto) {
		nombre = nuevoNombre;
		puerto = Integer.parseInt(nuevoPuerto);
		System.out.println("MAIN: La configuración se ha cambiado: Nombre = "+nombre+" Puerto = "+puerto);
		System.out.println("MAIN: Aplicando nueva configuración...");
		threadServidor.stop();
		try {
			if (socketServidor != null) socketServidor.close();
			socketServidor = null;
		} catch (IOException e) {
			System.out.println("MAIN - ERROR: No fue posible eliminar el socket de escucha...");
			e.printStackTrace();
		}
		esperarConexiones();
	}
	@Override
	public IListaEncadenada<String> getLocales() {
		if (archivosLocales.darNumeroObjetos() == 0)
			archivosLocales.agregar("Ningún archivo en el directorio local");
		return archivosLocales;
	}
	@Override
	public IListaEncadenada<String> getRemotos() {
		if (archivosServidor == null)
			archivosServidor = new ListaEncadenada<String>("Desconectado");
		return archivosServidor;
	}
	@Override
	public float[] getDescargas() {
		float[] respuesta = {enviando, tamaño};
		return respuesta;
	}
	//-----------------------------------------------------------------
    // Métodos aplicación
    //-----------------------------------------------------------------
	/**
	 * Finaliza la ejecución del componente:
	 * - Finaliza todas las conexiones activas.
	 * - Cierra el socket de escucha
	 * - Persiste las estadísticas y las configuraciones
	 */
	public void terminarEjecucion() {
		System.out.println("MAIN: Solicitud de detención detectada...");
		try {
			System.out.println("MAIN: Finalizando thread servidor...");
			threadServidor.stop();
			System.out.println("MAIN: Cerrando el socket de escucha...");
			if (socketServidor != null) socketServidor.close();
		} catch (IOException e) {
			System.out.println("MAIN - ERROR: No fue posible cerrar el socket de escucha...");
			e.printStackTrace();
		}
		System.out.println("MAIN: Finalizando conexión saliente...");
		if (servidor != null) servidor.logout();
		System.out.println("MAIN: Finalizando conexiones entrantes...");
		for (Cliente c : clientes) c.logout();
		salvarConfiguracion();
	}
	//-----------------------------------------------------------------
    // Métodos Observable
    //-----------------------------------------------------------------
	/**
	 * Notifica a los observadores
	 * @param modificado: String que representa el elemento modificado
	 */
	private void notificarObservadores(String modificado) {
		setChanged();
		notifyObservers(modificado);
	}
	/**
	 * Actualiza la lista con el nombre de los archivos en el directorio local 
	 */
	public void refrescarLocales() {
		archivosLocales = new ListaEncadenadaOrdenada<String>();
		for(File a : directorio.listFiles())
			if(a.isFile()) archivosLocales.agregar(a.getName());
				
		notificarObservadores("locales");
	}
	/**
	 * Cambia la lista de archivos disponibles en el servidor
	 * @param nuevaLista: El nuevo listado de archivos disponibles
	 */
	public void refrescarRemotos(IListaEncadenada<String> nuevaLista) {
		archivosServidor = nuevaLista;
		notificarObservadores("remotos");
	}
	//-----------------------------------------------------------------
    // Métodos auxiliares
    //-----------------------------------------------------------------
	/**
	 * Recupera la configuración de la aplicación
	 */
	private void cargarConfiguracion() {
		System.out.println("MAIN: Cargando configuraciones...");
		File conf = new File(directorio.getPath()+"/data/config.data");
		if (conf.exists()) {
			System.out.println("MAIN: Archivo de configuranción encontrado... cargando");
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(conf));
				estadisticas = (IListaEncadenada<Archivo>) ois.readObject();
				puerto = (Integer) ois.readObject();
				nombre = (String) ois.readObject();
			} catch (Exception e) {
				System.out.println("MAIN - ERROR: Error al recuperar las configuraciones... aplicando valores por defecto...");
				estadisticas = new ListaEncadenada<Archivo>();
				puerto = PUERTO;
				nombre = NOMBRE;
				e.printStackTrace();
			}
		} else {
			System.out.println("MAIN: No se encontró ningún archivo de configuración... aplicando valores por defecto...");
			estadisticas = new ListaEncadenada<Archivo>();
			puerto = PUERTO;
			nombre = NOMBRE;
		}
	}
	/**
	 * Guarda la configuración de la aplicación
	 */
	private void salvarConfiguracion() {
		System.out.println("MAIN: Persistiendo aplicación...");
		try {
			File data = new File(directorio.getPath()+"/data");
			if (!data.exists()) data.mkdirs();
			File conf = new File(directorio.getPath()+"/data/config.data");
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(conf));
			oos.writeObject(estadisticas);
			oos.writeObject(puerto);
			oos.writeObject(nombre);
			System.out.println("MAIN: La aplicación se ha persistido correctamente...");
		} catch (Exception e) {
			System.out.println("MAIN - ERROR: No fue posible persistir la aplicación...");
			e.printStackTrace();
		}
	}
}
