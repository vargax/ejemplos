package uniandes.infracom.SisTrans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Martín Uribe Gutierrez
 * @author Lina Giseth Casas Salas
 * Infraestructura Computacional 2012-20
 * Universidad de los Andes
 * 
 * Clase que representa la ejecución del protocolo para las solicitudes de un cliente que desea conocer la posición 
 * de un listado de camiones sin usar seguridad en canales.
 */
public class ProtocoloInseguro extends Thread
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
	 * Constante de reconocimiento. 
	 */
	public static final String CERTIFICADO = "CERTIFICADO";
	
	/**
	 * Constante que indica la etapa de envío del listado de camiones
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
	 * Indica si el usuario aun sigue conectado
	 */
	private boolean conectado;
	
	/**
	 * Socket de comunicacion con el cliente
	 */
	private Socket socket;
	
	/**
	 * Lector del flujo de entrada
	 */
	private BufferedReader reader;
	
	/**
	 * Escritor sobre el flujo de salida
	 */
	private PrintWriter writer;
	
	/**
	 * Lista de camiones generados
	 */
	private Camion[] camiones;
	
	//***************************************************************
	//CONSTRUCTOR DE LA CLASE
	
	/**
	 * Abre el canal de comunicación con el cliente e inicializa los flujos de entrada y salida.
	 * @param ss El socket de comunicación con el cliente.
	 * @param cam Lista de camiones generada previamente.
	 */
	public ProtocoloInseguro(int id, Socket ss, Camion[] cam)
	{
		this.id = id;
		this.inicio = System.currentTimeMillis();
		conectado = true;
		socket = ss;
		camiones = cam;		
		try 
		{
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Inicio del hilo de ejecución
	 */
	public void run()
	{
		try 
		{
			while( conectado )
			{
				procesar(reader.readLine());
			}
			System.out.println(id+","+(System.currentTimeMillis()-inicio));
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("murió el buffer");
		}
	}
	
	/**
	 * Procesa el string que le llega como parametro, de acuerdo con el protocolo definido
	 * @param s Cadena leida en el protocolo de comunicacion.
	 */
	public void procesar(String s)
	{
		String[] l = s.split(SEPARADOR);
		
		if( l[0].equals(HOLA) )
		{
			enviarStatus();
		}
		else if( l[0].equals(STATUS) )
		{
			// Caso Cliente
		}
		else if( l[0].equals(CERTIFICADO) )
		{
			procesarCertificado(s);
		}
		else if( l[0].equals(INIT) )
		{
			// Caso Cliente
		}
		else if( l[0].equals(PETICION) )
		{
			procesarPeticion(l[1]);
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
	 * Método que imprime la excepción cuando ha ocurrido un problema en el proceso de comunicación con el cliente
	 * @param e Es el mensaje de Excepción
	 */
	public void imprimirExcepcion(String e)
	{
	    writer.println(EXCEPCION+SEPARADOR +e);
	}
	
	
	/**
	 * Metodo que responde el status actual del servidor.
	 * @param s Cadena de entrada
	 */
	private void enviarStatus()
	{
		//Responder OK
		writer.println(OK);
	}
	
	
	/**
	 * Metodo que procesa el certificado recibido.
	 * @param s
	 */
	private void procesarCertificado(String s) 
	{
		//No hay certificado
		//Responder INIT:lista		
		String resultado = "";
        
        int m = 0;
        for (int i = 0; i < camiones.length; i++) 
        {
            if( m > 0 )
                resultado += SEPARADOR2;
            resultado += camiones[i].getId();
            m++;
        }
        
//        System.out.println("LISTA " + resultado);
        String enviar = INIT+SEPARADOR+resultado;     
//        System.out.println("A enviar: *" + enviar);
     
        // Envia la lista de camiones como texto plano.
        writer.print( enviar );
        writer.flush();
		
	}
	
	
	/**
	 * Metodo que recibe y procesa la solicitud de informacion de camiones enviada por el cliente.
	 * @param s El string leido en el protocolo de comunicacion.
	 */
	private void procesarPeticion(String s) 
	{
		//PETICION:idCamion[;idCamion]
		
		//INFO:idCamion,XXX-XX-XX,XX-XX-XX[;idCamion,XXX-XX-XX,XX-XX-XX]	
		String[] ids = s.split(SEPARADOR2);
		
		String resultado = "";
        
        int m = 0;
        for (int i = 0; i < camiones.length; i++) 
        {
            //System.out.println("entra for");
            boolean termino = false;
            for (int j = 0; j < ids.length && !termino; j++) 
            {
                //System.out.println("entra for2");
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
		writer.print(INFO+SEPARADOR+resultado);
		writer.flush();
		conectado = false;
	}	
}
