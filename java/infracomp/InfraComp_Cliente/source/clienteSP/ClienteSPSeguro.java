package clienteSP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.x500.X500Principal;

import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.bouncycastle.x509.extension.AuthorityKeyIdentifierStructure;
import org.bouncycastle.x509.extension.SubjectKeyIdentifierStructure;


/**
 * @author Sergio Hernandez Camilo Vargas
 *Esta clase implementa un cliente que busca a conectarse con el servidor SP
 */
@SuppressWarnings("deprecation")
public class ClienteSPSeguro 
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
	// -----------------------------------------------------------------
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
	 * Primera respuesta del servidor indicando si esta listo 
	 */
	public final static String LISTO = "LISTO";
	/**
	 * Primera respuesta del servidor indicando no esta list
	 */
	public final static String DENEGADO = "DENEGADO";
	/**
	 * Indica que viene la lista de algoritmos
	 */
	public final static String ALGORITMOS = "ALGORITMOS";
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
	public final static String SEPARADOR_LISTAALGORITMOS = ":";
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
	 * Nombre del cliente
	 */
	private String nombre;

	/**
	 * Numero que indica si esta en el primer OK del protocolo seguro o el segundo OK
	 */
	private int numeroOK;

	/**
	 * Flujo de salida asociado con el canal de comunicación
	 */
	private PrintWriter outString;

	/**
	 * Flujo de entrada asociado con el canal de comunicación
	 */
	private BufferedReader inString;

	/**
	 * Llave compartida realcionada con el algoritmo simetrico
	 */
	private SecretKey llaveSimetrica;

	/**
	 * La llave privada del cliente
	 */
	private PrivateKey llavePrivada;

	/**
	 * La llave publica del cliente
	 */
	@SuppressWarnings("unused")
	private PublicKey llavePublica;

	/**
	 * Los siguientes atributos guardan las diferentes cadenas de texto intercambiadas entre el cliente y el servidor. 
	 * Dichas cadenas son luegos impresas en un archivo .txt para que se puedan observar juntas
	 */
	private String clienteServidor1;
	private String servidorCliente1;
	private String servidorCliente2;
	private String clienteServidor2;
	private String clienteServidor3;
	private String clienteServidor4;
	private String servidorCliente3;
	private String servidorCliente4;
	private String clienteServidor5;
	private String servidorCliente5;
	private String servidorCliente6;
	private String servidorCliente7;
	private String clienteCliente1;
	private String clienteCliente2;

	private static final char[] hexChars = {
		'0','1','2','3','4','5','6','7',
		'8','9','a','b','c','d','e','f'};

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye el clienteSP que buscara a conectarse con el servidor SP
	 * @param archivo El archivo de propiedades que contiene la configuración que requiere el servidor
	 * @throws Exception Se lanza esta excepción si hay problemas cargando el archivo de propiedades
	 */
	public ClienteSPSeguro( String archivo ) throws Exception
	{
		cargarConfiguracion( archivo );
		Security.addProvider(new BouncyCastleProvider());
		numeroOK=1;
		desconectar();
		protocoloSeguro();
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
		nombre=configuracion.getProperty("cliente.nombre");
	}

	/**
	 * Conecta el clienteSP con el servidor SP a traves de un socket que se conecta con el puerto que sigue el protocolo con seguridad
	 * @throws Exception Se lanza esta excepción si hay problemas en la comunicación con el servidor
	 */
	public void conectarSeguro( ) throws Exception
	{
		String direccion = configuracion.getProperty( "servidor.direccion" );
		int puertoSeguro = Integer.parseInt( configuracion.getProperty( "servidor.puertoSeguro" ) );
		//       String ip= configuracion.getProperty("servidor.ip");
		socket = new Socket( direccion, puertoSeguro  );
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
	 * Genera el certificado siguiendo la version 3 del estandar X509 
	 * Se usa la libreria Bouncycastle
	 * @throws InvalidKeyException 
	 * @throws SignatureException 
	 * @throws NoSuchAlgorithmException 
	 * @throws NoSuchProviderException 
	 * @throws IllegalStateException 
	 * @throws CertificateEncodingException 
	 */
	public X509Certificate crearCertificado() throws InvalidKeyException, CertificateEncodingException, IllegalStateException, NoSuchProviderException, NoSuchAlgorithmException, SignatureException
	{
		//Fecha en la que empieza a ser valido
		//Desde ahora
		Date fechaAhora= new Date();		
		//Deja de ser valido luego de pasada 1 hora
		Calendar cal1 = Calendar.getInstance(); // creates calendar
		cal1.setTime(fechaAhora); // sets calendar time/date
		cal1.add(Calendar.HOUR_OF_DAY, -5); // subs 2 hours
		Date startDate=cal1.getTime(); // returns new date object, two hours in the past
		// startDate 					time from which certificate is valid
		//Deja de ser valido luego de pasada 1 hora
		Calendar cal2 = Calendar.getInstance(); // creates calendar
		cal2.setTime(fechaAhora); // sets calendar time/date
		cal2.add(Calendar.HOUR_OF_DAY, 5); // adds 5 hours
		Date expiryDate=cal2.getTime(); // returns new date object, one hour in the future
		//  expiryDate					time after which certificate is not valid
		//Constructs a randomly generated BigInteger, uniformly distributed over the range 0 to (2numBits - 1), inclusive. 
		int numBitsSerial=10; //Por ahora es 10 para no complicarse
		Random rndSerial = new Random();
		BigInteger serialNumber = new BigInteger(numBitsSerial,  rndSerial);       // serial number for certificate
		//Ahora se debe generar las llaves privadas y publicas   
		KeyPair asimetricKeys = generarLlavesAsimetrico(1024, "RSA");
		PrivateKey caKey = asimetricKeys.getPrivate();              // private key of the certifying authority (ca) certificate
		llavePrivada = caKey;
		//X509Certificate caCert = ...;        						// public key certificate of the certifying authority
		PublicKey puKey = asimetricKeys.getPublic(); 
		llavePublica=puKey;
		X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
		X500Principal              subjectName = new X500Principal("CN="+nombre);
		certGen.setSerialNumber(serialNumber);
		certGen.setIssuerDN(subjectName);
		certGen.setNotBefore(startDate);
		certGen.setNotAfter(expiryDate);
		certGen.setSubjectDN(subjectName);
		certGen.setPublicKey(puKey);
		certGen.setSignatureAlgorithm("MD2withRSA");
		certGen.addExtension(X509Extensions.AuthorityKeyIdentifier, false, new AuthorityKeyIdentifierStructure(puKey));
		certGen.addExtension(X509Extensions.SubjectKeyIdentifier, false, new SubjectKeyIdentifierStructure(puKey));
		X509Certificate cert = certGen.generateX509Certificate(caKey, "BC");   // note: private key of CA
		return cert;
	}

	/**
	 * Genera las llaves privada y publica con el metodo RSA 
	 * @return PrivateKey la llave generada
	 */
	//Aca es public por ahora, pero deberia ser private.
	public KeyPair generarLlavesAsimetrico(int nBits, String metodo) throws NoSuchAlgorithmException
	{
		KeyPairGenerator generator;
		generator = KeyPairGenerator.getInstance(metodo);
		generator.initialize(nBits);
		KeyPair keyPair = generator.generateKeyPair();
		return keyPair;
	}

	/**
	 * Metodo que inicial la conexion con el servidor con seguridad e inicia el seguridad
	 * @throws Exception
	 */
	public void protocoloSeguro() throws Exception
	{
		//Primero debe conectarse por el puerto seguro
		conectarSeguro();
		//Una vez conectado envia el primer mensaje, HOLA
		outString=new PrintWriter( socket.getOutputStream( ), true );
		clienteServidor1=HOLA;
		outString.println(clienteServidor1);
		runSeguro();		
	}

	/**
	 * Metodo que siempre esta a la escucha del servidor mientras el socket este prendido al puerto seguro
	 */
	public void runSeguro()
	{
		while(conectado==true&&!socket.isClosed())
		{
			try{
				char[] arreglo = new char[100000];
				inString.read(arreglo); 
				String linea = new String(arreglo);
				//-------------Protocolo Seguro
				if(linea.startsWith(STATUS))
				{
					//Puede ser o listo o denegado o ok o error
					//Debemos quitarle STATUS
					String  lineaSinStatus= linea.substring(STATUS.length()+1);//El+1 es por el separador
					if(lineaSinStatus.startsWith(LISTO))
					{
						//El servidor esta listo, se deben preparar los algoritmos para mandar
						//Mandar Algoritmos
						mandarAlgoritmos();
					}
					else if(lineaSinStatus.startsWith(DENEGADO))
					{
						//El servidor denego el acceso a este cliente. No hay nada que hacer
						desconectar();
					}
					else if(lineaSinStatus.startsWith(STATUS_OK)&&numeroOK==1)
					{
						servidorCliente1=linea;
						//El servidor esta listo, se deben preparar los algoritmos para mandar
						//Mandar Algoritmos
						numeroOK=2;
						mandarAlgoritmos();
					}
					else if(lineaSinStatus.startsWith(STATUS_OK)&&numeroOK==2)
					{
						servidorCliente2=linea;
						numeroOK=1;
						//Los algoritmos se recibieron bien, ahora se prepara el certificado para enviarlo
						mandarCertificado();
					}
					else if(lineaSinStatus.startsWith(STATUS_ERROR))
					{
						//El servidor produjo un error en el recibo de la lista de algoritmos
						desconectar();
					}

				}
				else if(linea.startsWith(INIT))
				{
					linea = linea.trim();
					servidorCliente3=linea;
					//Se debe recibir la llave simetrica
					//Primero limpiemos el string de INIT
					String [] arregloLinea = linea.split(SEPARADOR_PRINCIPAL);
					String llaveEncriptada = arregloLinea[1].trim();
					desencriptarLlaveSimetrica(llaveEncriptada);
					//En la posicion 3 esta la lista encriptada con el algoritmo simetrico
					String [] listaCamiones = desencriptarListaCamiones(arregloLinea[2].trim());
					realizarPeticiones(listaCamiones);
				}
				else if (linea.startsWith(INFO))
				{
					linea = linea.trim();
					servidorCliente5=linea;
					//Llego la info de los camiones
					String [] arregloLinea = linea.split(SEPARADOR_PRINCIPAL);
					String infoEncriptada = arregloLinea[1];
					infoEncriptada=infoEncriptada.trim();
					@SuppressWarnings("unused")
					String [] listaInformacionCamiones = procesarInformacionCamiones(infoEncriptada);
					String digestEncriptado = arregloLinea[2];
					procesarDigest(infoEncriptada.split(SEPARADOR_LISTACAMIONES), digestEncriptado);	
					desconectar();
//					concluir();
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
	 * Metodo que se encarga de desencriptar el Digest encriptado por parametro
	 * @param digestEncriptado
	 * @throws NoSuchAlgorithmException 
	 * @throws NoSuchPaddingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeyException 
	 */
	public void procesarDigest(String[] listaInformacionCamiones, String digestEncriptado) throws NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException 
	{
		//Primero formemos el Digest a partir de la listaInformacionCamiones
		//Consigamos los bytes de listaInformacionCamiones
		byte [] listaInformacionCamionesBytes = destransformar(listaInformacionCamiones, "Lista Camiones ");
		//Ahora debo desencriptar esto
		String listaDesencriptadaString = desencriptarSimetrico(listaInformacionCamionesBytes, "Blowfish");
		listaDesencriptadaString=listaDesencriptadaString.trim();
		byte[] bytesDesencriptados = listaDesencriptadaString.getBytes();
		String digestFormado = formarDigest (bytesDesencriptados, "SHA");
		digestFormado = digestFormado.trim();
		digestEncriptado=digestEncriptado.trim();
		//Ahora Comparemos ambos digests
		servidorCliente7="DigestRecibido: "+digestEncriptado;
		clienteCliente1= "DigestFormado: "+digestFormado;
		if(digestFormado.equals(digestEncriptado))
		{
			clienteCliente2="Ambos Digest son iguales!";
		}
		else
		{
			clienteCliente2="Hubo alguna diferencia entre ambos";
		}
	}

	/**
	 * Metodo que forma el Digest a partir de los bytes pasados como parametros y usando el algoritmo dado por parametro
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public String formarDigest(byte[] listaADigest, String algoritmo) throws NoSuchAlgorithmException
	{
		MessageDigest digestAFormar = MessageDigest.getInstance(algoritmo);
		digestAFormar.update(listaADigest,0,listaADigest.length);
		String aRetornar = new BigInteger(1, digestAFormar.digest()).toString(16);
		return aRetornar;
	}

	/**
	 * Metodo que forma el Digest a partir de los bytes pasados como parametros y usando el algoritmo dado por parametro
	 * @param s
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public String formarDigest(String s, String algoritmo) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance(algoritmo);
		md.update(s.getBytes(), 0, s.getBytes().length);
		byte[] hash = md.digest();
		StringBuilder sb = new StringBuilder();
		int msb;
		int lsb = 0;
		int i;
		for (i = 0; i < hash.length; i++) {
			msb = ((int)hash[i] & 0x000000FF) / 16;
			lsb = ((int)hash[i] & 0x000000FF) % 16;
			sb.append(hexChars[msb]);
			sb.append(hexChars[lsb]);
		}
		return sb.toString();
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
			System.out.println("Revisar el archivo conclusionConSeguridad.txt en la carpeta data. \n En dicho archivo se indican las cadenas de texto que se usaron en el protocolo.");
			System.out.println("---------------------------");
			File file = new File("./data/conclusionConSeguridad.txt");
			writer = new BufferedWriter(new FileWriter(file));
			writer.write("Se siguio satisfactoriamente el protocolo con seguridad. En orden se realizaron los siguientes pasos:"+"\n"+
					"---------------------------"+"\n"+
					"Cliente ----------> Servidor    : "+clienteServidor1+"\n"+
					"Servidor ---------> Cliente     : "+servidorCliente1+"\n"+
					"Cliente ----------> Servidor    : "+clienteServidor2+"\n"+
					"Servidor ---------> Cliente     : "+servidorCliente2+"\n"+
					"Cliente ----------> Servidor    : "+clienteServidor3+"\n"+
					"Cliente ----------> Servidor    : "+clienteServidor4+"\n"+
					"Servidor ---------> Cliente     : "+servidorCliente3+"\n"+
					"Servidor ---------> Cliente     : "+servidorCliente4+"\n"+
					"Cliente ----------> Servidor    : "+clienteServidor5+"\n"+
					"Servidor ---------> Cliente     : "+servidorCliente5+"\n"+
					"Servidor ---------> Cliente     : "+servidorCliente6+"\n"+
					"Servidor ---------> Cliente     : "+servidorCliente7+"\n"+
					"Cliente ---------> Cliente      : "+clienteCliente1+"\n"+
					"Cliente ---------> Cliente      : "+clienteCliente2+"\n"+
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
	 * Metodo que procesa la informacion de los camiones
	 * @param infoEncriptada
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeyException 
	 */
	public String [] procesarInformacionCamiones(String infoEncriptadaString) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		infoEncriptadaString=infoEncriptadaString.trim();
		String [] listaEncriptada = infoEncriptadaString.split(SEPARADOR_LISTACAMIONES);
		//Ahora debe desencriptar cada elemento de esta lista
		//Necesito pasar de String [] a byte[]
		byte [] listaEncriptadaBytes = destransformar(listaEncriptada, infoEncriptadaString);
		//Ahora desencriptemos estos bytes
		String listaDesencriptadaString = desencriptarSimetrico(listaEncriptadaBytes, "Blowfish");
		servidorCliente6="La lista desencriptada en un solo String es: "+listaDesencriptadaString;
		String[] listaDesencriptada = listaDesencriptadaString.split(SEPARADOR_LISTACAMIONES);	
		//El ultimo bloque esta danado entonces lo ignoramos
		String[] listaDesencriptadaSinElUltimo = new String [listaDesencriptada.length];
		for(int i = 0 ; i<listaDesencriptadaSinElUltimo.length;i++)
		{
			listaDesencriptadaSinElUltimo[i]=listaDesencriptada[i];
		}
		return listaDesencriptadaSinElUltimo;
	}

	/**
	 * Metodo que realiza peticiones de la lista dada
	 * @param listaCamiones
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws IOException 
	 */
	public void realizarPeticiones(String[] listaCamiones) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException 
	{
		//La ultima posicion de esta lista por el esquema de relleno no es valida
		//Como somos ambiciosos vamos a realizar el pedido de todos los camiones menos el ultimo
		String [] idsAEncriptar = new String[listaCamiones.length];
		for(int i = 0; i<idsAEncriptar.length;i++)
		{
			idsAEncriptar[i]=listaCamiones[i].trim();
		}
		//Ahora sigamos lo que publico Lina en el foto
		/**
		 *escoger los id's de los camiones que deseas solicitar,
		 * separados por ; 
		 * luego encriptarlos con la llave simétrica que te envía el servidor
		 * usar el método transformar para decodificar adecuadamente los bytes cifrados.
		 */
		//Ya tenemos los Ids, ahora separemoslos por ;
		String listaIdsString = ""+idsAEncriptar[0].trim();
		for(int i = 1; i<idsAEncriptar.length;i++)
		{
			listaIdsString+=SEPARADOR_LISTACAMIONES+idsAEncriptar[i].trim();
		}
		listaIdsString=listaIdsString.trim();
		//Formada esta lista, ahora debemos encriptar esto con la llave simetrica
		byte[] bytesEncriptados = encriptarSimetrico(listaIdsString, "Blowfish");
		//Ahora se usa el metodo transformar para decodificar adecuadamente los bytes cifrados
		String listaLista = transformar(bytesEncriptados);
		if(listaLista.startsWith("null"))
		{
			String listaListaSinElNull = listaLista.substring("null".length());
			listaLista=listaListaSinElNull;
		}
		listaLista=listaLista.trim();
		//Ahora nos preparamos para enviar la peticion
		String aEnviar = PETICION+SEPARADOR_PRINCIPAL+listaLista;
		clienteServidor5=aEnviar;
		outString=new PrintWriter( socket.getOutputStream( ), true );
		outString.println(aEnviar);
		outString.flush();
		socket.getOutputStream().flush();
	}

	/**
	 * Metodo que desencripta la lista de camiones dada como un solo String y retorna dicha lista desencriptada
	 * @param string
	 * @return String [] la lista desencriptada
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeyException 
	 */
	public String[] desencriptarListaCamiones(String listaEncriptadaString) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException 
	{
		listaEncriptadaString=listaEncriptadaString.trim();
		String [] listaEncriptada = listaEncriptadaString.split(SEPARADOR_LISTACAMIONES);
		//Ahora debe desencriptar cada elemento de esta lista
		//Necesito pasar de String [] a byte[]
		byte [] listaEncriptadaBytes = destransformar(listaEncriptada, listaEncriptadaString);
		//Ahora desencriptemos estos bytes
		String listaDesencriptadaString = desencriptarSimetrico(listaEncriptadaBytes, "Blowfish");
		servidorCliente4="lista desencriptada en un solo String es: "+listaDesencriptadaString;
		String[] listaDesencriptada = listaDesencriptadaString.split(SEPARADOR_LISTACAMIONES);
		return listaDesencriptada;
	}

	/**
	 * Metodo que desencripta la llave simetrica a partir del string dado 
	 * Dicho String es de la forma INIT:LlaveSimetrica cifrada a partir de la llave publica del certificado
	 * @param linea
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public void desencriptarLlaveSimetrica(String llaveEncriptada) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException 
	{
		byte [] byteLlave = destransformar(llaveEncriptada.split(SEPARADOR_LISTACAMIONES), llaveEncriptada);
		Cipher desCipher = Cipher.getInstance("RSA");
		desCipher.init(Cipher.DECRYPT_MODE, llavePrivada);
		byte [ ] bytesSinCifrado =desCipher.doFinal(byteLlave);
		llaveSimetrica = new SecretKeySpec(bytesSinCifrado, "Blowfish");
	}

	/**
	 * Metodo que envia la lista de algoritmos que soporta el sistema
	 * @throws IOException 
	 */
	public void mandarAlgoritmos() throws IOException {
		//Lee la lista de algoritmos que soporta
		/**
		 * SIMETRICOS
		 *DES. Modo ECB, esquema de relleno PKCS5, llave de 64 bits.
		 *AES. Modo ECB, esquema de relleno PKCS5, llave de 128 bits.
		 *Blowfish. Cifrado por bloques, llave de 128 bits.
		 *RC4. Cifrado por flujo, llave de 128 bits.
		 *ASIMETRICOS (ALGA):
		 *RSA. Cifrado por bloques, llave de 1024 bits.
		 *DIGEST (ALGD):
		 *MD5
		 *SHA
		 */
		//Escogemos por ahora Blowfish, RSA y SHA
		String aEnviar=ALGORITMOS;
		String algoSimetrico="Blowfish";
		String algoAsimetrico="RSA";
		String algoDigest="SHA";
		aEnviar+=SEPARADOR_LISTAALGORITMOS+algoSimetrico+SEPARADOR_LISTAALGORITMOS+algoAsimetrico+SEPARADOR_LISTAALGORITMOS+algoDigest;
		clienteServidor2=aEnviar;
		outString=new PrintWriter( socket.getOutputStream( ), true );
		outString.println(aEnviar);
		outString.flush();
		socket.getOutputStream().flush();
	}

	/**
	 * Metodo que se encarga de encriptar el string dado en parametro con el algoritmo dado en parametro
	 * @param aEncriptar
	 * @param algoritmo
	 * @return Los bytes encriptados
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] encriptarSimetrico(String aEncriptar, String algoritmo) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
		aEncriptar= aEncriptar.trim();
		byte[ ]bytesAEncriptar =  aEncriptar.getBytes();
		Cipher desCipher = Cipher.getInstance(algoritmo);
		desCipher.init(Cipher.ENCRYPT_MODE, llaveSimetrica);
		byte [ ] ciphertext = desCipher.doFinal(bytesAEncriptar);
		return ciphertext;
	}

	/**
	 * Metodo que se encarga de desencriptar el string dado en parametro con el algoritmo dado en parametro
	 * @param aDesencriptar
	 * @param algoritmo
	 * @return el STring desencriptado
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 */
	public String desencriptarSimetrico(byte[] aDesencriptar, String algoritmo) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException
	{
		Cipher desCipher = Cipher.getInstance(algoritmo);
		desCipher.init(Cipher.DECRYPT_MODE, llaveSimetrica);
		byte [ ] cleartext1 =desCipher.doFinal(aDesencriptar);
		return new String(cleartext1);
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
			//Crea el certificado
			X509Certificate certificado = crearCertificado();
			certificado.checkValidity();
			//Lo manda por el out, tiene que enviarlo por bytes
			outString=new PrintWriter( socket.getOutputStream( ), true );
			clienteServidor3=CERTIFICADO;
			outString.println(clienteServidor3);
			outString.flush();
			socket.getOutputStream().flush();	
			byte[] bytesCert = certificado.getEncoded();
			clienteServidor4= "Flujo de bytes del cetificado "+"\n"+"Cliente ----------> Servidor    : "+bytesCert;
			//Ahora lo envia
			socket.getOutputStream().write(bytesCert);
			socket.getOutputStream().flush();	

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Algoritmo de encapsulamiento a enteros. Convierte los bytes de un String a su representacion como enteros.
	 * @param b Los bytes a representar como enteros.
	 * @return EL string construido con la representacion de bytes como enteros.
	 */
	public String transformar( byte[] b )
	{
		String s=null;
		try {
			s = new String("".getBytes(),"COD");
		} catch (UnsupportedEncodingException e) {

			//e.printStackTrace();
		}
		for (int i = 0; i < b.length; i++) 
		{
			if( i == b.length-1 )
				s += Byte.toString(b[i]);
			else
				s += Byte.toString(b[i])+SEPARADOR_LISTACAMIONES;
		}
		return s;
	}

	/**
	 * Algoritmo que transforma los enteros en los bytes correspondientes.
	 * @param s El string con los enteros a transformar.
	 * @param ss La cadena de peticion.
	 * @return Los bytes en su representacion real.
	 */
	public byte[] destransformar( String[] s, String ss )
	{
		byte[] b = new byte[s.length];
		for (int i = 0; i < b.length; i++) 
		{
			try {
				b[i] = Byte.parseByte(s[i],10);
			} catch (Exception e) {
				System.out.println("Excepcion en destransformar");
				System.out.println("S es "+s[i]);
				System.out.println("SS es "+ss);
				System.out.println(ss+SEPARADOR_PRINCIPAL+e.getMessage());
				//e.printStackTrace();
			} 
		}

		return b;
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
			ClienteSPSeguro clienteSPSeguro = new ClienteSPSeguro("./data/clienteSP.properties" );
		}
		catch( Exception e )
		{
			System.out.println( e.getMessage( ) );
		}
	}
}