package clienteSP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;

/**
 * @author Sergio Hernandez Camilo Vargas
 *Esta clase implementa un cliente que busca a conectarse con el servidor SP Sin seguridad
 */
public class ClienteSPInseguro 
{
	/**
	 * PROTOCOLO QUE SE DEBE IMPLEMENTAR
	 * 1. El cliente se comunica con el servidor para iniciar una sesión de solicitud de información, espera un
	mensaje de confirmación de inicio del servidor, a continuación envía la lista de algoritmos de cifrado
	que usará durante la sesión y espera un segundo mensaje del servidor confirmando que soporta los
	algoritmos seleccionados.
	2. El cliente envía su certificado digital (CD) para autenticarse con el servidor . El CD debe seguir el
	estándar X509.
	3. Una vez el SP recibe el CD, lo verifica y genera una llave simétrica (LS) y le envía al cliente un sobre
	digital que contiene la llave y la lista de unidades de distribución o camiones. Para generar el sobre
	digital, el servidor usa la llave pública (LPU) contenida en el CD.
	4. Cuando el cliente reciba la lista de camiones envía, en un sobre digital construido con la llave simétrica
	enviada por el servidor LS, una sub lista que solo incluye los camiones de los que se desea conocer la
	posición.
	5. Al recibir la solicitud anterior, el SP envía la posición de las unidades de distribución requeridas en un
	sobre digital. Este mensaje debe además incluir un digest para que el cliente pueda verificar la
	integridad del mensaje.
	 */
	// -----------------------------------------------------------------
	// Constantes
	// ------------------------------------------------------x-----------
	/**
	 * Constantes para la convencion de comunicacion
	 */
	/**
	 * Primer mensaje al servidor SP
	 */
	public final static String HOLA = "HOLA";
	/**
	 * Primera respuesta del servidor indicando si esta listo o no
	 * O Respuesta indicando si recibio correctamente o no los algoritmos
	 */
	public final static String STATUS = "STATUS";
	/**
	 * Indica que el servidor recibio los algoritmos
	 */
	public final static String STATUS_OK = "OK";
	/**
	 * Indica que el servidor no recibio los algoritmos
	 */
	public final static String STATUS_ERROR = "ERROR";
	/**
	 * Indica que va a enviar el certificado
	 */
	public final static String CERTIFICADO = "CERTIFICADO";
	/**
	 * Indica que a continuacion vienen en un sobre digital la lista de camiones yla llave simetrica generada por el SP
	 */
	public final static String INIT = "INIT";
	/**
	 * Solicita una lista de camiones
	 */
	public final static String PETICION = "PETICION";
	/**
	 * Responde con la informacion de los camiones solicitada
	 */
	public final static String INFO = "INFO";
	/**
	 * Cuando hay una excepcion se debe leer
	 */
	public final static String EXCEPCION = "EXCEPCION";
	/**
	 * Separadores para las lista y la informaciones
	 */
	public final static String SEPARADOR_PRINCIPAL = ":";
	public final static String SEPARADOR_LISTACAMIONES = ";";
	public final static String SEPARADOR_INFORMACIONCAMIONES = ",";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	/**
	 * Es el canal de comunicación a través del cual el punto de venta se comunica con el almacén
	 */
	private Socket socket;

	/**
	 * Atributo que indica que si esta o no conectado
	 */
	private boolean conectado;

	/**
	 * Es el conjunto de propiedades que contienen la configuración de la aplicación
	 */
	private Properties configuracion;

	/**
	 * Flujo de salida asociado con el canal de comunicación
	 */
	private PrintWriter outString;

	/**
	 * Flujo de entrada asociado con el canal de comunicación
	 */
	@SuppressWarnings("unused")
	private InputStream in;

	/**
	 * Flujo de entrada asociado con el canal de comunicación
	 */
	private BufferedReader inString;

	/**
	 * Los siguientes atributos guardan las diferentes cadenas de texto intercambiadas entre el cliente y el servidor. 
	 * Dichas cadenas son luegos impresas en un archivo .txt para que se puedan observar juntas
	 */
	private String clienteServidor1;
	private String servidorCliente1;
	private String servidorCliente2;
	private String clienteServidor2;
	private String clienteServidor3;
	private String servidorCliente3;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye el clienteSP que buscara a conectarse con el servidor SP
	 * @param archivo El archivo de propiedades que contiene la configuración que requiere el servidor
	 * @throws Exception Se lanza esta excepción si hay problemas cargando el archivo de propiedades
	 */
	public ClienteSPInseguro( String archivo ) throws Exception
	{
		cargarConfiguracion( archivo );
		desconectar();
		protocoloInseguro();
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Carga la configuración a partir de un archivo de propiedades
	 * @param archivo El archivo de propiedades que contiene la configuración que requiere servidor SP
	 * @throws Exception Se lanza esta excepción si hay problemas cargando el archivo de propiedades
	 */
	private void cargarConfiguracion( String archivo ) throws Exception
	{
		FileInputStream fis = new FileInputStream( archivo );
		configuracion = new Properties( );
		configuracion.load( fis );
		fis.close( );
	}

	/**
	 * Conecta el clienteSP con el servidor SP a traves de un socket que se conecta con el puerto que sigue el protocolo sin seguridad
	 * @throws Exception Se lanza esta excepción si hay problemas en la comunicación con el servidor
	 */
	public void conectarInseguro( ) throws Exception
	{
		String direccion = configuracion.getProperty( "servidor.direccion" );
		//    	String ip= configuracion.getProperty("servidor.ip");
		int puertoInseguro = Integer.parseInt( configuracion.getProperty( "servidor.puertoInseguro" ) );
		socket = new Socket( direccion, puertoInseguro  );
		in=socket.getInputStream();
		inString = new BufferedReader( new InputStreamReader( socket.getInputStream( ) ) );
		conectado=true;
	}

	/**
	 * Desconecta el servidor cualquier sea la conexion (segura o insegura)
	 */
	public void desconectar( )
	{
		conectado=false;
		try
		{
			if( socket != null )
			{
				socket.shutdownInput();
				socket.shutdownOutput();
				socket.close( );
			}
		}
		catch( IOException e )
		{
		}
		socket = null;
	}

	/**
	 * Metodo que inicia la conexion al servidor sin verificar la seguridad y realiza el protocolo sin seguridad
	 * @throws Exception 
	 */
	public void protocoloInseguro() throws Exception
	{
		//Primero debe conectarse por el puerto inseguro
		conectarInseguro();
		//Una vez conectado envia el primer mensaje, HOLA
		outString=new PrintWriter( socket.getOutputStream( ), true );
		outString.println(HOLA);
		clienteServidor1=HOLA;
		runInseguro();		
	}

	/**
	 * Metodo que muestra en consola los resultados del protocolo con seguridad
	 */
	@SuppressWarnings("unused")
	private void concluir() 
	{
		BufferedWriter writer=null;
		try
		{		
			System.out.println();
			System.out.println("---------------------------");
			System.out.println("Revisar el archivo conclusionSinSeguridad.txt en la carpeta data. \n En dicho archivo se indican las cadenas de texto que se usaron en el protocolo.");
			System.out.println("---------------------------");
			File file = new File("./data/conclusionSinSeguridad.txt");
			writer = new BufferedWriter(new FileWriter(file));
			writer.write("Se siguio satisfactoriamente el protocolo sin seguridad. En orden se realizaron los siguientes pasos:"+"\n"+
					"---------------------------"+"\n"+
					"Cliente ----------> Servidor    : "+clienteServidor1+"\n"+
					"Servidor ---------> Cliente     : "+servidorCliente1+"\n"+
					"Cliente ----------> Servidor    : "+clienteServidor2+"\n"+
					"Servidor ---------> Cliente     : "+servidorCliente2+"\n"+
					"Cliente ----------> Servidor    : "+clienteServidor3+"\n"+
					"Servidor ---------> Cliente     : "+servidorCliente3+"\n"+
					"---------------------------");		
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo que siempre esta a la escucha del servidor mientras el socket este prendido al puerto inseguro
	 */
	public void runInseguro(){
		while(conectado==true&&!socket.isClosed())
		{
			try{
				char[] arreglo = new char[100000];
				inString.read(arreglo); 
				String linea = new String(arreglo).trim();
				//-------------Protocolo Inseguro
				if(linea.equals(STATUS_OK))
				{
					servidorCliente1=linea;
					//-----------Se debe mandar el certificado
					mandarCertificado();
				}
				else if(linea.startsWith(INIT))
				{
					servidorCliente2=linea;
					//-----------Se debe procesar la lista de camiones y realiza la peticion
					procesarListaCamionesInseguro(linea);
				}
				else if(linea.startsWith(INFO))
				{
					servidorCliente3=linea;
					//-----------Se debe leer la respuesta y terminar
					desconectar();
//					concluir();
				}
				else if(linea.startsWith(EXCEPCION))
				{
					desconectar();
				}

			}
			catch(Exception e)
			{
				e.printStackTrace();
				desconectar();
			}
		}
	}

	/**
	 * Metodo que envia el certificado a traves del socket
	 * @throws InvalidKeyException
	 * @throws CertificateEncodingException
	 * @throws IllegalStateException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws SignatureException
	 * @throws IOException 
	 */
	public void mandarCertificado()
	{
		try
		{
			outString=new PrintWriter( socket.getOutputStream( ), true );
			outString.println(CERTIFICADO);
			clienteServidor2=CERTIFICADO;
			outString.flush();
			socket.getOutputStream().flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Procesa la lista de los camiones recibidos en la linea
	 * @param linea
	 * @throws IOException 
	 */
	public void procesarListaCamionesInseguro(String linea) throws IOException 
	{
		//Primero separemosla por el separador principal
		String[] porElSeparadorPrincipal= linea.split(SEPARADOR_PRINCIPAL);
		//Tiene 3 posiciones por la Exception que hay al final del mensaje
		String listaCamionesString="";
		if(porElSeparadorPrincipal.length==3)
		{
			//Hay una Excepcion al final
			//Me interesa la posicion 2
			String listaCamionesConLaUltimaPosicionMal = porElSeparadorPrincipal[1];
			//A este quiero quitarle el Exception que tiene al final
			listaCamionesString = listaCamionesConLaUltimaPosicionMal.substring(0, listaCamionesConLaUltimaPosicionMal.indexOf(EXCEPCION));
			//Ahora me interesa tener la lista de los camiones, solo es separar por el separador de la lista	
		}
		else
		{
			//No hay Excepcion al final
			listaCamionesString = porElSeparadorPrincipal[1];
		}
		String[] listaCamiones = listaCamionesString.split(SEPARADOR_LISTACAMIONES);
		//Vamos a suponer que por ahora nos interesan todos los camiones
		//Ahora forma la lista a enviar y la envia
		//Empieza con el protocolo y la primera peticion, si no hay es vacio.
		String aEnviar =""+ PETICION+SEPARADOR_PRINCIPAL;
		for(int i = 0;i<listaCamiones.length;i++)
		{
			aEnviar+=listaCamiones[i]+SEPARADOR_LISTACAMIONES;
		}
		//Ya tiene la peticion, la manda por el socket
		outString=new PrintWriter( socket.getOutputStream( ), true );
		outString.println(aEnviar);
		clienteServidor3=aEnviar;
		outString.flush();
		socket.getOutputStream().flush();
	}

	// -----------------------------------------------------------------
	// Main
	// -----------------------------------------------------------------

	/**
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 * @param args
	 */
	public static void main( String[] args )
	{
		try
		{
			@SuppressWarnings("unused")
			ClienteSPInseguro clienteSPSeguro = new ClienteSPInseguro("./data/clienteSP.properties" );
		}
		catch( Exception e )
		{
			System.out.println( e.getMessage( ) );
		}
	}
}
