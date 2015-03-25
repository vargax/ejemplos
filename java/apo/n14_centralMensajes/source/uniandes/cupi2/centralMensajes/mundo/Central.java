/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n14_centralMensajes
 * Autor: Pablo Marquez - 13 Jun, 2007
 * Autor: Juan Erasmo Gómez - 6 Ago, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.centralMensajes.mundo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import uniandes.cupi2.centralMensajes.mundo.algoritmos.IAlgoritmoEncriptacion;
import uniandes.cupi2.centralMensajes.mundo.algoritmos.IFabricaAlgoritmo;
import uniandes.cupi2.collections.iterador.Iterador;

/**
 * Representa la central de mensajes.<br>
 * Se encarga de almacenar los mensajes para que los clientes los consulten.
 */
public class Central implements ICentral
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que determina el tamaño de la clave.
     */
    public final static int TAM_CLAVE = 16;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Tabla donde están los clientes.
     */
    private TablaHashingCentralMensajes<String, Cliente> clientes;

    /**
     * Tabla donde están los mensajes.
     */
    private TablaHashingCentralMensajes<String, Mensaje> mensajes;

    /**
     * Tabla donde están las fabricasAlgoritmos.
     */
    private TablaHashingCentralMensajes<String, IFabricaAlgoritmo> fabricasAlgoritmos;

    /**
     * Código de mensaje actual.
     */
    private int codigoActual;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor de la clase. <br>
     * <b>pre: </b>El properties de claves y el lector de usuarios tienen información completa y válida.
     * @throws PersistenciaException
     */
    public Central( ) throws PersistenciaException
    {
        codigoActual = 0;
        clientes = new TablaHashingCentralMensajes<String, Cliente>( );
        mensajes = new TablaHashingCentralMensajes<String, Mensaje>( );
        fabricasAlgoritmos = new TablaHashingCentralMensajes<String, IFabricaAlgoritmo>();              
        
        inicializarAlgoritmos( );
        cargarUsuarios( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /*
     * (non-Javadoc)
     * 
     * @see uniandes.cupi2.centralMensajes.mundo.ICentral#enviarMensaje(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void enviarMensaje( String llaveCliente, String llaveDestinatario, String texto, String encriptacion )
    {
        String texto1 = texto.toString( );
        Cliente destino = clientes.dar( llaveDestinatario );
        IAlgoritmoEncriptacion algoritmo = darAlgoritmo(encriptacion);
        String textoCodificado = algoritmo.codificarMensaje( texto1 );
        codigoActual++;
        Mensaje mensaje = new Mensaje( codigoActual, llaveCliente, llaveDestinatario, textoCodificado );
        mensajes.agregar( codigoActual + "", mensaje );
        destino.agregarMensaje( mensaje );

    }


	/*
     * (non-Javadoc)
     * 
     * @see uniandes.cupi2.centralMensajes.mundo.ICentral#darListaCodificaciones()
     */
    public Iterador<String> darCodificaciones( )
    {
        return fabricasAlgoritmos.darLlaves( );
    }

    /*
     * (non-Javadoc)
     * 
     * @see uniandes.cupi2.centralMensajes.mundo.ICentral#darLlavesMensajes(java.lang.String)
     */
    public Iterador<String> darLlavesMensajes( String cliente )
    {
        Cliente destinatario = clientes.dar( cliente );
        return destinatario.darLlavesMensajes( );
    }

    /*
     * (non-Javadoc)
     * 
     * @see uniandes.cupi2.centralMensajes.mundo.ICentral#darLlavesMensajesNoLeidos(java.lang.String)
     */
    public Iterador<String> darLlavesMensajesNoLeidos( String cliente )
    {
        Cliente destinatario = clientes.dar( cliente );
        return destinatario.darLlavesMensajesNoLeidos( );
    }

    /*
     * (non-Javadoc)
     * 
     * @see uniandes.cupi2.centralMensajes.mundo.ICentral#darListaClientes()
     */
    public Iterador<String> darClientes( )
    {
        return clientes.darLlaves( );
    }

    /*
     * (non-Javadoc)
     * 
     * @see uniandes.cupi2.centralMensajes.mundo.ICentral#darRemitenteMensaje(java.lang.String)
     */
    public String darRemitenteMensaje( String llaveMensaje )
    {
        return mensajes.dar( llaveMensaje ).darIdOrigen( );
    }

    /*
     * (non-Javadoc)
     * 
     * @see uniandes.cupi2.centralMensajes.mundo.ICentral#eliminarMensaje(java.lang.String, java.lang.String)
     */
    public void eliminarMensaje( String llaveMensaje, String llaveCliente )
    {
        Cliente cliente = clientes.dar( llaveCliente );
        cliente.eliminarMensaje( mensajes.dar( llaveMensaje ) );
        mensajes.eliminar( llaveMensaje );
    }

    /*
     * (non-Javadoc)
     * 
     * @see uniandes.cupi2.centralMensajes.mundo.ICentral#marcarMensajeLeido(java.lang.String, java.lang.String)
     */
    public void marcarMensajeLeido( String cliente, String llaveMensaje )
    {
        Mensaje mensaje = mensajes.dar( llaveMensaje );
        mensaje.cambiarEstadoMensaje( true );
    }

    /*
     * (non-Javadoc)
     * 
     * @see uniandes.cupi2.centralMensajes.mundo.ICentral#recuperarMensaje(java.lang.String, java.lang.String, java.lang.String)
     */
    public String recuperarMensaje( String llaveCliente, String llaveMensaje, String encriptacion )
    {
        Mensaje mensaje = mensajes.dar( llaveMensaje );
        IAlgoritmoEncriptacion llave = darAlgoritmo(encriptacion);
        return llave.decodificarMensaje( mensaje.darTexto( ) );
    }

    /*
     * (non-Javadoc)
     * 
     * @see uniandes.cupi2.centralMensajes.mundo.ICentral#nuevoCliente(java.lang.String)
     */
    public void nuevoCliente( String identificador )
    {
        Cliente cliente = new Cliente( identificador );
        clientes.agregar( identificador, cliente );
    }

    /*
     * (non-Javadoc)
     * 
     * @see uniandes.cupi2.centralMensajes.mundo.ICentral#darCantidadClientes()
     */
    public int darCantidadClientes( )
    {
        return clientes.darNumeroElementos( );
    }

    // -----------------------------------------------------------------
    // Métodos Auxiliares
    // -----------------------------------------------------------------

    /**
     * Inicializa de las claves a partir de un properties.<br>
     * <b>pre: </b>info contiene la información completa y válida de las claves.
     * @param info Properties con la información de la claves - info != null.
     */
    private void inicializarAlgoritmos(  ) throws PersistenciaException
    {
    	Properties info = new Properties();
    	try {
			info.load(new FileInputStream("data/fabricas.properties"));
	        int numeroAlgoritmos = Integer.parseInt( info.getProperty( "numAlgoritmos" ) );                
	        for( int i = 1; i <= numeroAlgoritmos; i++ )
	        {
	        	String nombreAlgoritmo = info.getProperty("algoritmo" + i +".nombre");
	        	String fabrica = info.getProperty("algoritmo" + i +".fabrica");
	        	IFabricaAlgoritmo fab = (IFabricaAlgoritmo)Class.forName(fabrica).newInstance();
	        	fabricasAlgoritmos.agregar( nombreAlgoritmo, fab);
	        }
		} catch (Throwable e) {
			throw new PersistenciaException("No se pudo cargar la información de las fabricas de algoritmos", e);
		}
    }

    private IAlgoritmoEncriptacion darAlgoritmo(String encriptacion) 
    {    
		return fabricasAlgoritmos.dar(encriptacion).crearAlgoritmo();
	}

    
    /**
     * Carga los usuarios a partir de un archivo y los agrega a la respectiva tabla hashing. <br>
     * <b>pre: </b>Agregó a la tabla usuarios a los usuarios que se encontraban en el archivo especificado.
     * @param reader Lector del archivo que contiene la información de los usuarios - reader != null.
     * @throws PersistenciaException Si se presentó algún problema al cargar el archivo.
     */
    private void cargarUsuarios( ) throws PersistenciaException
    {
        try
        {
        	BufferedReader reader = new BufferedReader( new FileReader( "./data/usuarios.data" ) );
            int numUsuarios = Integer.parseInt( reader.readLine( ) );
            while( numUsuarios-- > 0 )
            {
                String linea = reader.readLine( );
                nuevoCliente( linea );
            }
            reader.close( );
        }
        catch( IOException e )
        {
            throw new PersistenciaException( "Se presentaron problemas al leer el archivo de usuarios" );
        }
    }
}
