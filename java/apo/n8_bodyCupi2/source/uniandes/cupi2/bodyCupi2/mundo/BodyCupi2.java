/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_bodyCupi2
 * Autor: Cupi2 - 04-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bodyCupi2.mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import uniandes.cupi2.bodyCupi2.excepciones.*;

/**
 *  La clase principal de la aplicación <br>
 *	<b> inv: </b><br>
 * usuarios != null
 * No pueden existir id's repetidos
 * No pueden existir ingresos repetidos (para el mismo usuario el mismo día)
 * No pueden haber registros de salida duplicados (solo cero o un registro de salida por usuario por día)
 */
public class BodyCupi2
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * Los usuarios del gimnasio
     */
    public ArrayList<Usuario> usuarios;


    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye un nuevo gimnasio desde un archivo serializado <br>
     * En caso de error al cargar los datos o no existir un archivo de configuración, <br>
     * se crea una lista de usuarios vacía <br>
     * @param rutaArchivoConfiguracion La ruta del archivo de configuración
     */
    public BodyCupi2(String rutaArchivoConfiguracion  )
    {
        try
        {
            cargarDatos( rutaArchivoConfiguracion );

        }
        catch( PersistenciaException e )
        {
            usuarios=new ArrayList();
            verificarInvariante();
        }     

    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Carga los datos de los usuarios a partir de una archivo de datos serializado. <br>
     * @param rutaArchivoConfiguracion Ruta del archivo de datos - rutaArchivoConfiguracion != null
     * @throws PersistenciaException Si ocurre un error al cargar los datos o no existe el archivo 
     */
    public void cargarDatos( String rutaArchivoConfiguracion ) throws PersistenciaException
    {
       try {
    	   ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivoConfiguracion));
    	   usuarios = (ArrayList)ois.readObject();
    	   ois.close();
       } catch (Exception e) {
    	   throw new PersistenciaException(rutaArchivoConfiguracion, e.getMessage());
       }
    }
    
    /**
     * Agrega un nuevo usuario con una nueva suscripción  <br>
     * @param id El id del usuario
     * @param nombre El nombre del usuario
     * @param edad La edad del usuario
     * @param telefono El teléfono del usuario
     * @param genero El género del usuario
     * @param foto La foto del usuario
     * @param tipoSuscripcion El tipo de la suscripción
     * @param registroMedico El registro médico del usuario
     * @param anio El año en que comienza la suscripción
     * @param mes El mes en que comienza la suscripción
     * @param dia El día en que comienza la suscripción
     * @param hora La hora en que comienza la suscripción
     * @param minutos Los minutos en que comienza la suscripción
     * @throws UsuarioExisteException En caso que ya exista el usuario a crear
     */
    public void agregarUsuario(int id, String nombre, int edad,int telefono,String genero, String foto,String registroMedico, String tipoSuscripcion, int anio, int mes, int dia, int hora, int minutos)throws UsuarioExisteException
    {
        if(!existeUsuario( id ))
        {
            Usuario usuario= new Usuario(id, nombre, edad,telefono,genero, foto,registroMedico );
            usuario.crearSuscripcion( anio, mes, dia, hora, minutos, tipoSuscripcion );
            usuarios.add( usuario );
            verificarInvariante();
        }
        else
        {
            throw new UsuarioExisteException( nombre );
        }
        
    }
    
    /**
     * Persiste la información de los usuarios en un archivo serializado
     * @param rutaArchivoSerializado La ruta del archivo en el cual se guardan los datos
     * @throws PersistenciaException En caso que no se pueda crear el archivo serializado, <br>
     * que haya un problema al escribir los datos o al cerrar el archivo.
     * 
     */
    public void persistirDatos( String rutaArchivoSerializado ) throws PersistenciaException
    {
    	try {
    		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivoSerializado));
    		oos.writeObject(usuarios);
    		oos.close();
    	} catch (IOException e) {
    		throw new PersistenciaException(rutaArchivoSerializado, "No fue posible persistir BodyCupidos: \n"+e.getMessage());
    	}
    }
    
    /**
     * Verifica si existe un usuario con el id recibido por parámetro
     * @param id El id del usuario
     * @return true en caso que exista, false en caso contrario
     */
    public boolean existeUsuario(int id)
    {
        boolean existe=false;
        for(int i=0; i<usuarios.size( ) && !existe;i++)
        {
            Usuario temp=(Usuario)usuarios.get( i );
            int idTemp=temp.darId( );
            if(id==idTemp)
            {
                existe=true;
            }
        }
        
        return existe;
    }
    

    /**
     * Registra la entrada de un usuario al gimnasio.
     * @param idUsuario El id del usuario
     * @param anio el año de la fecha en que el usuario entra
     * @param mes el mes el mes de la fecha en que el usuario entra
     * @param dia el día el día de la fecha en que el usuario entra
     * @param hora la hora la hora de la fecha en que el usuario entra
     * @param minutos los minutos de la fecha en que el usuario entra
     * @throws UsuarioNoExisteException En caso que se trate de registrar una entrada para un usuario que no existe
     * @throws RegistroTiempoExisteException En caso que el usuario haya ingresado en la fecha recibida por parámetro
     */
    public void registrarEntrada( int idUsuario, int anio, int mes, int dia, int hora, int minutos ) throws UsuarioNoExisteException, RegistroTiempoExisteException
    {
    	Usuario tempUsuario = darUsuario(idUsuario);
    	Fecha tempFecha = new Fecha(anio, mes, dia, hora, minutos);
    	tempUsuario.crearRegistroTiempo(tempFecha);
    }
    
    /**
     * Registra la salida de un usuario del gimnasio
     * @param idUsuario El id del usuario
     * @param anio el año de la fecha en que el usuario sale del gimnasio
     * @param mes el mes de la fecha en que el usuario sale del gimnasio
     * @param dia el día de la fecha en que el usuario sale del gimnasio
     * @param hora la hora de la fecha en que el usuario sale del gimnasio
     * @param minutos los minutos de la fecha en que el usuario sale del gimnasio
     * @throws UsuarioNoExisteException En caso que no exista un usuario con el id recibido por parámetro
     * @throws RegistroTiempoNoExisteException En caso que el usuario no haya entrado en la fecha
     * @throws HoraSalidaExistenteParaRegistroException En caso que ya se haya registrado una hora de salida
     */
    public void registrarSalida(int idUsuario, int anio, int mes, int dia, int hora, int minutos) throws UsuarioNoExisteException, RegistroTiempoNoExisteException, HoraSalidaExistenteParaRegistroException
    {
    	Usuario tempUsuario = darUsuario(idUsuario);
    	Fecha tempFecha = new Fecha(anio, mes, dia, hora, minutos);
    	
    	tempUsuario.cerrarRegistroTiempo(tempFecha);
    }
    

    /**
     * Retorna el usuario cuyo id corresponde al dado por parámetro.
     * @param idUsuario El id del usuario
     * @return El usuario cuyo id corresponde al recibido por parámetro. <br>
     * null en caso de no encontrar ningún usuario
     */
    public Usuario darUsuario(int idUsuario)
    {
        boolean termino=false;
        Usuario elUsuario=null;
        for(int i=0;i<usuarios.size( )&&!termino;i++)
        {
            Usuario temp=(Usuario)usuarios.get(i);
            if(temp.darId( )==idUsuario)
            {
                elUsuario=temp;
                termino=true;
            }
        }
        
        return elUsuario;
        
    }
    
    /**
     * Informa el índice de un usuario corresponde a la posición que éste ocupa
     * en el vector de usuarios. Se utiliza para identificar cuál es el
     * usuario siguiente o anterior.
     * @param idUsuario El id del usuario cuyo índice se está buscando
     * @return El índice del usuario cuyo id es recibido por parámetro,
     * En caso de no encontrar al usuario en el vector, retorna -1
     */
    public int darIndiceUsuario(int idUsuario)
    {
        boolean encontro=false;
        int indice=-1;
        for(int i=0;i<usuarios.size( )&&!encontro;i++)
        {
            Usuario temp=(Usuario)usuarios.get( i );
            if(temp.darId( )==idUsuario)
            {
                indice=i;
                encontro=true;
            }
                
        }
        
        return indice;
    }

    /**
     * Retorna el registro de tiempo del usuario cuyo id es recibido por parámetro <br>
     * @param idUsuario El id del usuario
     * @param anio El anio de la fecha del registro de tiempo buscado
     * @param mes El mes de la fecha del registro de tiempo buscado
     * @param diaMes El dia del mes de la fecha del registro de tiempo buscado
     * @return El registro de tiempo, del usuario cuyo id fue recibido por parámetro
     */
    public RegistroTiempo darRegistroTiempo(int idUsuario, int anio, int mes,int diaMes)
    {
        Usuario elUsuario=darUsuario(idUsuario);
        Fecha fechaRegistro=new Fecha(anio,mes,diaMes,0,0);
        RegistroTiempo elRegistro=elUsuario.darRegistroTiempo(fechaRegistro);
        return elRegistro;
    }
    
    
    /**
     * Retorna la última suscripción del usuario cuyo id es recibido por parámetro
     * @param idUsuario El id del usuario
     * @return La última suscripción del usuario
     */
    public Suscripcion darUltimaSuscripcionUsuario(int idUsuario)
    {
        Usuario usuario=darUsuario( idUsuario );
        Suscripcion suscripcion=usuario.darUltimaSuscripcion( );
        return suscripcion;
    }

    /**
     * Retorna el usuario anterior al que tiene el id recibido por parámetro. El usuario anterior es aquel
     * que se encuentra en la posición anterior en el vector de usuarios. En caso que el usuario cuyo
     * id es recibido por parámetro sea el primero, retorna null
     * @param idUsuario El id del usuario
     * @return El usuario anterior.
     */
    public Usuario darUsuarioAnterior( int idUsuario )
    {
        Usuario usuarioAnterior=null;
        int posUsuarioActual=darIndiceUsuario( idUsuario );
        int posUsuarioAnterior=usuarios.size( )-1;
        if(posUsuarioActual>0)
        {
            posUsuarioAnterior=posUsuarioActual-1;
            usuarioAnterior=(Usuario)usuarios.get( posUsuarioAnterior );
        }
        
        return usuarioAnterior;
    }
    
    /**
     * Retorna el número de suscripciones de un usuario
     * @param idUsuario El ID del usuario
     * @return El número de suscripciones de un usuario
     */
    public int darNumRegistrosTiempo(int idUsuario)
    {
        Usuario usuario=darUsuario( idUsuario );
        return usuario.darNumRegistrosTiempo( );
    }
    
    /**
     * Retorna el usuario siguiente al que tiene el id recibido por parámetro. El usuario siguiente es aquel
     * que se encuentra en la posición siguiente en el vector de usuarios. En caso que el usuario con el
     * id recibido por parámetro sea el último, se retorna el primero
     * @param idUsuario El id del usuario
     * @return El siguiente usuario.
     */
    public Usuario darSiguienteUsuario(int idUsuario)
    {
        Usuario siguienteUsuario=null;
        int posUsuarioActual=darIndiceUsuario( idUsuario );
        int posSiguienteUsuario=0;
        int posUltimoUsuario= usuarios.size( )-1;
        if(posUsuarioActual<posUltimoUsuario)
        {
            posSiguienteUsuario=posUsuarioActual+1;
            siguienteUsuario=(Usuario)usuarios.get( posSiguienteUsuario );
        }
        return siguienteUsuario;
        
    }

    
    /**
     * Valida si hay al menos un usuario registrado en el gimnasio
     * @return true en caso que haya un usuario registrado, false en caso contrario
     */
    public boolean existenUsuarios()
    {
        boolean existen=false;
        if(usuarios.size( )>0)
            existen=true;
        return existen;
    }
    
    
    /**
     * Retorna el primer usuario del vector de usuarios. En caso que no hayan usuarios registrados retorna null
     * @return El primer usuario del vector
     */
    public Usuario darUsuarioInicial()
    {
        Usuario usuario=null; 
        if(usuarios.size()>0)
            usuario=(Usuario)usuarios.get( 0 );
        return usuario;
    }
    
    
    /**
     * Retorna el último registro de tiempo, del usuario cuyo id es recibido por parámetro.
     * En caso que no exista el usuario o el usuario no tenga ningún registro de tiempo,
     * retorna null
     * @param idUsuario El id del usuario
     * @return El último registro del usuario cuyo id es recibido por parámetro
     */
    public RegistroTiempo darUltimoRegistroTiempo(int idUsuario)
    {
        RegistroTiempo registro=null;
        boolean existe=existeUsuario( idUsuario );
        if(existe)
        {
            Usuario usuario=darUsuario( idUsuario );
            registro=usuario.darUltimoRegistroTiempo( );
        }
        
        return registro;
    }

    
    /**
     * Crea una suscripción para el usuario cuyo id es recibido por parámetro.
     * La suscripción debe crearse con el tipo y la fecha recibidos por parámetro y con hora y minutos en 0 <br>
     * @param idUsuario El id del usuario
     * @param tipoSuscripcion El tipo de la suscripción
     * @param diaMes El día del mes de la fecha de inicio de la suscripción
     * @param mes El mes de la fecha de inicio de la suscripción
     * @param anho El año de la fecha de inicio de la suscripción
     */
    public void crearSuscripcion( int idUsuario, String tipoSuscripcion, int diaMes, int mes, int anho )
    {
        Usuario usuario =darUsuario( idUsuario );
        usuario.crearSuscripcion( anho, mes, diaMes, 0, 0, tipoSuscripcion );
    }
    
    /**
     * Retorna el registro de tiempo siguiente a aquel que corresponde al registro de 
     * tiempo cuya fecha es recibida por parámetro. En caso que se esté en el último 
     * registro, retorna null
     * @param idUsuario El id del usuario
     * @param idRegistroTiempo El ID del registro de tiempo
     * @return EL siguiente registro de tiempo
     */
    public RegistroTiempo darSiguienteRegistroTiempo(int idUsuario, String idRegistroTiempo)
    {
        Usuario usuario=darUsuario( idUsuario );
        RegistroTiempo siguienteRegistro=usuario.darSiguienteRegistroTiempo(idRegistroTiempo);
        return siguienteRegistro;
        
    }
    
    /**
     * Retorna el registro de tiempo anterior a aquel que corresponde al registro de 
     * tiempo cuya fecha es recibida por parámetro. En caso que se esté en el primer 
     * registro, retorna null
     * @param idUsuario El id del usuario
     * @param idRegistroTiempo El ID del registro de tiempo
     * @return EL siguiente registro de tiempo
     */
    public RegistroTiempo darAnteriorRegistroTiempo(int idUsuario, String idRegistroTiempo)
    {
        Usuario usuario=darUsuario( idUsuario );
        RegistroTiempo anteriorRegistro=usuario.darAnteriorRegistroTiempo(idRegistroTiempo);
        return anteriorRegistro;
        
    }
    
    /**
     * Crea un reporte de usuarios que tienen sus últimas suscripciones vencidas. (Ver el formato del reporte en el documento de descripción)
     * La última suscripción de un usuario es la única que se tiene en cuenta para generar el reporte.
     * @param pathArchivo La ruta del archivo en donde se guardan los datos
     * @param nombreArchivo El nombre del archivo en donde se guardan los datos
     * @throws PersistenciaException En caso que no se pueda escribir el archivo
     */
    public void exportarUsuariosConSuscripcionesVencidas(String pathArchivo, String nombreArchivo) throws PersistenciaException
    {
    	File archivo = new File(pathArchivo,nombreArchivo);
		try {
			PrintWriter salida = new PrintWriter(archivo);
			salida.println("LISTA DE USUARIOS CON SUSCRIPCIONES VENCIDAS");
	    	for (int i = 0; i < usuarios.size(); i++) {
	    		Usuario tempUsuario = usuarios.get(i);
	    		if (tempUsuario.darUltimaSuscripcion().darEstado().equals(Suscripcion.ESTADO_VENCIDA)) {
	    			salida.println("Usuario: "+tempUsuario.darNombre());
	    			salida.println("Id: "+tempUsuario.darId());
	    			salida.println("Tipo de suscripción: "+tempUsuario.darUltimaSuscripcion().darTipoSuscripcion());
	    			salida.println("Fecha de apertura suscripción: "+tempUsuario.darUltimaSuscripcion().darFechaInicioConFormato());
	    		}
	    	}
	    	salida.close();
		} catch (FileNotFoundException e) {
			throw new PersistenciaException(pathArchivo+nombreArchivo, e.getMessage());
		}
    }
    
    /**
     * Exporta en un archivo de texto todos los usuarios del gimnasio. (Ver el formato del reporte en el documento de descripción)
     * @param pathArchivo La ruta del archivo en donde se guardan los datos
     * @param nombreArchivo El nombre del archivo en donde se guardan los datos
     * @throws PersistenciaException En caso que no se pueda escribir el archivo
     */
    public void exportarUsuarios(String pathArchivo, String nombreArchivo) throws PersistenciaException 
    {
        try {
        	File archivo = new File(pathArchivo,nombreArchivo);
        	PrintWriter salida = new PrintWriter(archivo);
        	salida.println("LISTA DE USUARIOS DE BODYCUPI2 \n");
        	for (int i = 0; i < usuarios.size(); i++) {
        		Usuario tempUsuario = usuarios.get(i);
        		salida.println("Usuario: "+tempUsuario.darNombre());
        		salida.println("Id: "+tempUsuario.darId());
        		salida.println("Edad: "+tempUsuario.darEdad());
        		salida.println("Género: "+tempUsuario.darGenero());
        		salida.println("Registro médico:"+tempUsuario.darRegistroMedico());
        		salida.println("Teléfono:"+tempUsuario.darTelefono());
        		salida.println("------------------------------------------------");
        	}
        	salida.close();
        } catch (FileNotFoundException e) {
        	throw new PersistenciaException(pathArchivo+nombreArchivo, e.getMessage());
        }
    }

    /**
     * Exporta en un archivo de texto los registros de tiempo de un usuario (Ver el formato del reporte en el documento de descripción)
     * @param idUsuario El id del usuario cuyos registros se van a exportar
     * @param nombreArchivo El nombre del archivo
     * @param pathArchivo La ruta del archivo
     * @throws PersistenciaException En caso que no se pueda escribir el archivo
     */
    public void exportarRegistrosTiempoUsuario( int idUsuario, String pathArchivo, String nombreArchivo ) throws PersistenciaException
    {
    	try {
    		File archivo = new File(pathArchivo, nombreArchivo);
    		PrintWriter salida = new PrintWriter(archivo);
    		salida.println("LISTA DE REGISTROS DE TIEMPO \n");  		
    		
    		ArrayList regUsuario = ((Usuario) darUsuario(idUsuario)).darRegistrosTiempo(); 
    		
    		for (int i = 0; i < regUsuario.size(); i++) {
    			RegistroTiempo tempRegistroTiempo = (RegistroTiempo) regUsuario.get(i);
    			Fecha inicio = tempRegistroTiempo.darTiempoEntrada();
    			Fecha fin = tempRegistroTiempo.darTiempoSalida();
    			
    			salida.println("Registro #"+tempRegistroTiempo.darId());
    			salida.println("Fecha Entrada: "+inicio.darFechaConFormato());
    			if (fin != null) salida.println("Fecha Salida: "+fin.darFechaConFormato());
    			else salida.println("Fecha Salida: Pendiente");
    			salida.println("------------------------------------------------");
    		}
    		salida.close();
    	} catch (FileNotFoundException e) {
        	throw new PersistenciaException(pathArchivo+nombreArchivo, e.getMessage());
        }
    }
    
    /**
     * Importa usuarios al gimnasio desde un archivo de texto con el formato descrito en el enunciado 
     * @param rutaArchivo ruta del archivo a cargar
     * @throws FormatoArchivoException Si el archivo no cumple con el formato establecido
     */
    public void importarUsuarios(String rutaArchivo) throws FormatoArchivoException
    {
    	try {
    	BufferedReader archivo = new BufferedReader(new FileReader(rutaArchivo));
    	//System.out.println(archivo.readLine());
    	String linea = archivo.readLine(); String[] lineas = linea.split(":");
    	int numUsuarios = Integer.parseInt(lineas[1]);
    	for (int i = 0; i < numUsuarios; i++) {
    		// punteada
    		archivo.readLine();
    		// datos usuario
    		linea = archivo.readLine(); lineas = linea.split(";");
    		Usuario tempUsuario = new Usuario(Integer.parseInt(lineas[0]), 
    				lineas[1], Integer.parseInt(lineas[2]), Integer.parseInt(lineas[4]), 
    				lineas[3].equals("Masculino") ? Usuario.GENERO_MASCULINO : Usuario.GENERO_FEMENINO, 
    				lineas[5], lineas[6]);
    		// "Datos suscripciones"
    		archivo.readLine();
    		// "Cantidad Suscripciones"
    		linea = archivo.readLine(); lineas = linea.split(":");
    		int numSuscripciones = Integer.parseInt(lineas[1]);
    		for (int j = 0; j < numSuscripciones; j++) {
    			linea = archivo.readLine(); lineas = linea.split(";");
    			tempUsuario.crearSuscripcion(Integer.parseInt(lineas[1]), Integer.parseInt(lineas[2]), 
    					Integer.parseInt(lineas[3]), Integer.parseInt(lineas[4]), Integer.parseInt(lineas[5]), 
    					lineas[0].equals("AMATEUR") ? Suscripcion.SUSCRIPCION_AMATEUR : lineas[0].equals("REGULAR") ? Suscripcion.SUSCRIPCION_REGULAR : Suscripcion.SUSCRIPCION_MASTER);
    		}
    		// "Datos registros de tiempo"
    		archivo.readLine();
    		// "Cantidad de registros de tiempo"
    		linea = archivo.readLine(); lineas = linea.split(":");
    		int numRegistrosTiempo = Integer.parseInt(lineas[1]);
    		for (int j = 0; j < numRegistrosTiempo; j++) {
    			linea = archivo.readLine();
    			String[] tempLinea = linea.split("-");
    			lineas = tempLinea[0].split(";");
    			Fecha entrada = new Fecha(Integer.parseInt(lineas[0]), Integer.parseInt(lineas[1]), 
    					Integer.parseInt(lineas[2]), Integer.parseInt(lineas[3]), Integer.parseInt(lineas[4]));
    			tempUsuario.crearRegistroTiempo(entrada);
    			
    			if (!(tempLinea[1].equals("Pendiente"))) {
    			lineas = tempLinea[1].split(";");
    			Fecha salida = new Fecha(Integer.parseInt(lineas[0]), Integer.parseInt(lineas[1]), 
    					Integer.parseInt(lineas[2]), Integer.parseInt(lineas[3]), Integer.parseInt(lineas[4]));
    			tempUsuario.cerrarRegistroTiempo(salida);
    			}
    		}
    		usuarios.add(tempUsuario);
    	}
    	archivo.close();
    	} catch (Exception e) {
    		throw new FormatoArchivoException(e.getMessage());
    	}
    }
    //-----------------------------------------------------------------
    // Puntos de Extensión
    //-----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
    
    //-----------------------------------------------------------------
    // Invariante
    //-----------------------------------------------------------------
    private void verificarInvariante() {
    	assert (usuarios != null) : "La lista de usuarios no ha sido inicializada";
    }


}