package uniandes.infracom.SisTrans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.security.cert.X509Certificate;

/**
 * @author Martín Uribe Gutierrez
 * @author Lina Giseth Casas Salas
 * Infraestructura Computacional 2012-20
 * Universidad de los Andes
 * Algoritmos tomados de: 
 * http://www.java2s.com/Code/Java/Security/CatalogSecurity.htm
 * http://www.bouncycastle.org/
 * 
 * Clase que representa la ejecución del protocolo para las solicitudes de un cliente que desea conocer la posición 
 * de un listado de camiones utilizando canales seguros.
 */
public class ProtocoloSeguro extends Thread
{
	//***************************************************************
	//CONSTANTES PARA LA DEFINICION DEL PROTOCOLO
	
	/**
	 * Constante que representa el inicio de las negociaciones entre las entidades
	 */
	public static final String HOLA = "HOLA";
	
	/**
	 * Constante que indica el estado del servidor durante el proceso de negociación
	 */
	public static final String STATUS = "STATUS";
	
	/**
	 * Constante que indica la etapa de envío del listado de camiones y la llave simétrica
	 */
	public static final String INIT = "INIT";
	
	/**
	 * Constante que indica que el cliente tiene una solicitud
	 */
	public static final String PETICION = "PETICION";
	
	/**
	 * Constante de respuesta del servidor a la solicitud del cliente
	 */
	public static final String INFO = "INFO";
	
	/**
	 * El separador utilizado para segmentar los datos primarios
	 */
	public static final String SEPARADOR = ":";
	
	/**
	 * El separador para segmentar los datos secundarios (camiones)
	 */
	public static final String SEPARADOR2 = ";";
	
	/**
	 * El separador para segmentar los datos terciarios (posición)
	 */
	public static final String SEPARADOR3 = ",";
	
	/**
	 * Constante que indica el estado correcto de una etapa del protocolo
	 */
	public static final String OK = "OK";
	
	/**
	 * Constante que indica que hubo un error en el protocolo, no se contuará el protocolo en dado caso
	 */
	public static final String DENEGADO = "DENEGADO";
	
	/**
	 * Constante para indicar una excepción, se adicionará a la respuesta la signatura de la excepción
	 */
	public static final String EXCEPCION = "EXCEPCION";
	
	/**
	 * Constante para indicar el envío de los algoritmos a utilizar para proporcionar privacidad en los canales
	 */
	public static final String ALGORITMOS = "ALGORITMOS";
	
	/**
	 * Código ASCII utilizado en las cadenas de caracteres. Necesario para los procesos de conversión de arreglo de
	 * Bytes a String y viceversa
	 */
	public static final String COD = "US-ASCII";
	
	/**
	 * Algoritmo asimétrico a utilizar
	 */
	public static final String ASYMMETRIC1 = "RSA";
	
//	/**
//	 * Algoritmo asimétrico opcional
//	 */
//	public static final String ASYMMETRIC2 = "ElGamal/None/NoPadding";
	
	/**
	 * Algoritmo Simétrico DES, con modo de encripción ECB y control de padding PKCS5Padding 
	 */
	public static final String SYMMETRIC1 = "DES/ECB/PKCS5Padding";
	
	/**
	 * Algoritmo Simétrico AES, con modo de encripción ECB y control de padding PKCS5Padding
	 */
	public static final String SYMMETRIC2 = "AES/ECB/PKCS5Padding";
	
	/**
	 * Algoritmo Simétrico Blowfish
	 */
	public static final String SYMMETRIC3 = "Blowfish";
	
	/**
	 * Algoritmo Simétrico RC4
	 */
	public static final String SYMMETRIC4 = "RC4";
	
	/**
	 * Algoritmo de Digest MD5
	 */
	public static final String DIGEST1 = "MD5";
	
	/**
	 * Algoritmo de Digest SHA
	 */
	public static final String DIGEST2 = "SHA";
	
	//***************************************************************
	//ATRIBUTOS DE LA CLASE
	/**
	 * Un identificador para el ProtocoloSeguro
	 */
	private int id;
	/**
	 * El momento en milisegundos en que se creó la clase
	 */
	private long inicio;
	/**
	 * Guarda el tipo de algoritmo simétrico seleccionado por el cliente
	 */
	private String tipoSim = "";
	
	/**
	 * Guarda el tipo de algoritmo asimétrico seleccionado por el cliente
	 */
	private String tipoAsim = "";
	
	/**
	 * Guarda el tipo de algoritmo de digest seleccionado por el cliente
	 */
	private String tipoDig = "";
	
	/**
	 * Modela el estado de la sesion
	 */
	private boolean conectado;
	
	/**
	 * Modela la conexion
	 */
	private Socket socket;
	
	/**
	 * Modela los camiones
	 */
	private Camion[] camiones;
	
	/**
	 * Canal de entrada de la conexion
	 */
	private BufferedReader reader;
	
	/**
	 * Canal de salida de la conexion
	 */
	private PrintWriter writer;
	
	/**
	 * Llave simetrica
	 */
	private SecretKey key;
	
	/**
	 * Modela el estado del certificado
	 */
	private boolean esperandoCertificado;
	
	// ***************************************************************
	// CONSTRUCTOR DE LA CLASE
	
	/**
	 * Método constructor que genera la llave secreta y además abre los canales de comunicación con el cliente.
	 * @param ss El socket a través del cual se va a llevar la comunicación.
	 * @param cam Lista de camiones.
	 */
	public ProtocoloSeguro(int id, Socket ss, Camion[] cam) {
		this.id = id;
		this.inicio = System.currentTimeMillis();
		this.conectado = true;
		this.socket = ss;
		this.camiones = cam;
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());		
		try {
			this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.writer = new PrintWriter(socket.getOutputStream(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inicio del hilo de ejecución
	 */
	public void run() {
		try {
			while( conectado )	{
				procesar(reader.readLine());
			}
			System.out.println((System.currentTimeMillis()-inicio));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		writer.close();
        try {
			reader.close();
			socket.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	//***************************************************************
	//MÉTODOS DE LA CLASE
	
	/**
	 * Método que se encarga de procesar todas las comunicaciones del cliente que vienen a través del socket.
	 * @param s Linea de comunicación leída
	 */
	public void procesar(String s)
	{
//		System.out.println("++ Thread "+this.id+" procesando "+s);
		String[] l = s.split(SEPARADOR);
		
		if( l[0].equals(HOLA) )
		{
			enviarStatus();
			esperandoCertificado = true;
		}
		else if (l[0].equals(ALGORITMOS))
		{
			String resp = STATUS + SEPARADOR + OK;
			if (l[1].equals("DES"))
			{
				tipoSim = SYMMETRIC1;
				generarLlave("DES", 64);
			}
			else if (l[1].equals("AES"))
			{
				tipoSim = SYMMETRIC2;
				generarLlave("AES", 128);
			}
			else if (l[1].equals("Blowfish"))
			{
				tipoSim = SYMMETRIC3;
				generarLlave("Blowfish", 128);
			}
			else if (l[1].equals("RC4"))
			{
				tipoSim = SYMMETRIC4;
				generarLlave("RC4", 128);
			}
			else
			{
				resp = STATUS + SEPARADOR + EXCEPCION;
			}
			if (l[2].equals("RSA"))
			{
				tipoAsim = ASYMMETRIC1;
			}
//			else if (l[2].equals("ElGamal"))
//			{
//				tipoAsim = ASYMMETRIC2;
//			}
			else
			{
				resp = STATUS + SEPARADOR + EXCEPCION;
			}
			if (l[3].equals("MD5"))
			{
				tipoDig = DIGEST1;
			}
			else if (l[3].equals("SHA"))
			{
				tipoDig = DIGEST2;
			}
			else
			{
				resp = STATUS + SEPARADOR + EXCEPCION;
			}
			writer.println(resp);
		}
		else if( l[0].equals(STATUS) )
		{
			// Caso Cliente
		}
		else if( esperandoCertificado )
		{
		    
			String s2= s.substring(s.indexOf(SEPARADOR)+1);
			procesarCertificado(s2);
			esperandoCertificado = false;
		}
		else if( l[0].equals(INIT) )
		{
			// Caso Cliente
		}
		else if( l[0].equals(PETICION) )
		{
			String s2= s.split(SEPARADOR)[1];
//			System.out.println(s2);
			procesarPeticion(s2);
		}
		else if( l[0].equals(INFO) )
		{
			//Caso Cliente
		}
		else
		{
		    imprimirExcepcion( "Error en Formato");
		}	
	}
	
	/**
	 * Genera la llave simétrica dependiendo del algoritmo seleccionado por el cliente
	 * @param algoritmo el algoritmo de cifrado seleccionado por el cliente
	 * @param tamLlave el tamaño de la llave a generar
	 */
	public void generarLlave(String algoritmo, int tamLlave)
	{
		KeyGenerator keyGen;
        try
        {
            keyGen = KeyGenerator.getInstance(algoritmo,"BC");
            keyGen.init(tamLlave);
            key = keyGen.generateKey();
//            System.out.println("Llave simétrica: *"+new String(key.getEncoded())+"* S"+key.getEncoded().length);
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
	}
	
	/**
	 * Método que envía el status actual del servidor.
	 */
	private void enviarStatus() {
		//Responder OK
		writer.println(STATUS + SEPARADOR + OK);
	}
	
	
	/**
	 * Método que se encarga de procesar el certificado enviado por el cliente y extraer la llave pública de este.
	 * @param s1 
	 */
	private void procesarCertificado(String s1) 
	{
	    try
        {
            //Lee el certificado que viene a través del socket y lo guarda en un .cer
	        byte[] my = new byte[2000];
	        socket.getInputStream( ).read( my, 0, my.length );
	               
	        
	        X509Certificate cert = X509Certificate.getInstance( my );
            cert.checkValidity( );
            PublicKey pub = cert.getPublicKey( );
            
            //Cifra la llave privada utilizando la llave p�blica.
            Cipher asimCipher = Cipher.getInstance( tipoAsim );
            asimCipher.init( Cipher.ENCRYPT_MODE, pub );
            byte [ ] ciphertext = asimCipher.doFinal(key.getEncoded( ));
            
            //Crear la lista de IDs
            String resultado = "";
            
            int m = 0;
            for (int i = 0; i < camiones.length; i++) 
            {
                if( m > 0 )
                    resultado += SEPARADOR2;
                resultado += camiones[i].getId();
                m++;
            }
            
//            System.out.println("LISTA " + resultado + " Y TAM:" + resultado.getBytes().length);
            
            //Cifra la lista de camiones utilizando la llave simétrica.
            Cipher blowCipher = Cipher.getInstance(tipoSim);
            blowCipher.init( Cipher.ENCRYPT_MODE, key );
            byte [ ] listaCifrada = blowCipher.doFinal(resultado.getBytes());
                     
            String llave = transformar(ciphertext);
            String lista = transformar(listaCifrada);
            
            String enviar = INIT+SEPARADOR+llave+SEPARADOR+lista;
            
//            System.out.println("A enviar: *" + enviar);
            writer.print( enviar );
            writer.flush();

        } catch (Exception e)
        {
            e.printStackTrace();
            imprimirExcepcion( e.getMessage( ) );
            writer.println("ERROR " + e.getMessage());
            
        }
        esperandoCertificado = false;		
	}
	
	/**
	 * Método que se encarga de procesar las peticiones de información del cliente
	 * @param s La petición del cliente.
	 */
	private void procesarPeticion(String ss) {
		//PETICION:idCamion[;idCamion](cifSim)
		
		//Descifra la petición
	    try {
	    	byte[] s = destransformar( ss.split(SEPARADOR2), "peticion" );
            Cipher simCipher = Cipher.getInstance( tipoSim );     
            simCipher.init( Cipher.DECRYPT_MODE, key );
            byte [ ] lista = simCipher.doFinal(s);
            String listaid = new String(lista,COD);
            String[] ids = listaid.split(SEPARADOR2);
            for (int i = 0; i < ids.length; i++) {
            }
            String resultado = "";
            
            int m = 0;
            for (int i = 0; i < camiones.length; i++) {
                boolean termino = false;
                for (int j = 0; j < ids.length && !termino; j++) {
                    if( ids[j].equals( camiones[i].getId() ) )
                    {
                        if( m > 0 )
                            resultado += SEPARADOR2;
                        resultado += camiones[i].getId() + SEPARADOR3
                                    + camiones[i].getLatitud() + SEPARADOR3
                                    + camiones[i].getLongitud();
                        termino = true;
                        m++;
                    }
                }
            }
            String digest = getDigest( resultado );
            //Cifra el resultado      
            simCipher.init( Cipher.ENCRYPT_MODE, key );
            byte [ ] res = simCipher.doFinal(resultado.getBytes());
            
            
            //INFO:idCamion,XXX-XX-XX,XX-XX-XX[;idCamion,XXX-XX-XX,XX-XX-XX](cifSim)
            resultado = INFO + SEPARADOR + transformar(res)+SEPARADOR+digest;
            
            //Enviar información
            writer.println( resultado );
        }
        catch( Exception e ) {
            e.printStackTrace();
            imprimirExcepcion( e.getMessage( ) );
            writer.println("ERROR " + e.getMessage());                   
        }	
		//Leer y procesar	
		conectado = false;
	}

	/**
	 * Método que calcula el hash de un string dado
	 * @param originalMessage
	 * @return
	 */
	public String getDigest(String originalMessage)
	{
	    try
        {
            MessageDigest md = MessageDigest.getInstance( tipoDig );
            md.update( originalMessage.getBytes(),0,originalMessage.getBytes().length );
            StringBuffer sum = new StringBuffer( new BigInteger( 1,md.digest( )).toString( 16) );
            while(sum.length( )<32)
            {
                sum.insert( 0, "0" );
            }
            return sum.toString( );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        return null;    
	}
	

	/**
	 * Método que imprime la excepción cuando ha ocurrido un problema en el proceso de comunicación con el cliente
	 * @param e Es el mensaje de Excepción
	 */
	public void imprimirExcepcion(String e)
	{
	    writer.println(EXCEPCION+SEPARADOR +e);
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
			s = new String("".getBytes(),COD);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < b.length; i++) 
        {
			if( i == b.length-1 )
				s += Byte.toString(b[i]);
			else
				s += Byte.toString(b[i])+SEPARADOR2;
		}
		return s;
	}
	
	
	/**
	 * Algoritmo que transforma los enteros en los bytes correspondientes.
	 * @param s El string con los enteros a transformar.
	 * @param ss La cadena de peticion.
	 * @return Los bytes en su representacion real.
	 */
	public byte[] destransformar( String[] s, String ss ) {
		byte[] b = new byte[s.length];
		
		for (int i = 0; i < b.length; i++) 
		{
			try {
				b[i] = Byte.parseByte(s[i],10);
			} catch (Exception e) {
			} 
		}
		return b;
	}
}
