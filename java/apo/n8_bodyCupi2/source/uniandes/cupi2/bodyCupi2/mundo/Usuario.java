package uniandes.cupi2.bodyCupi2.mundo;

import java.io.Serializable;
import java.util.ArrayList;

import uniandes.cupi2.bodyCupi2.excepciones.HoraSalidaExistenteParaRegistroException;
import uniandes.cupi2.bodyCupi2.excepciones.RegistroTiempoExisteException;
import uniandes.cupi2.bodyCupi2.excepciones.RegistroTiempoNoExisteException;

/**
 * Clase usada para representar un usuario del gimnasio
 *<b> inv: </b><br>
 * nombre != null && nombre != ""
 * edad > 0
 * telefono.toString().length() > 7
 * genero == GENERO_MASCULINO || genero == GENERO_FEMENINO
 * foto != null && foto !=""
 * registroMedico != null && registroMedico != ""
 * suscripciones != null
 * registrosTiempos != null
 */
public class Usuario implements Serializable
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
    
    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 100L;
    
    /**
     * Constante representa a un usuario de genero masculino
     */
    public final static String GENERO_MASCULINO="Masculino";
    
    /**
     * Constante representa a un usuario de genero femenino
     */
    public final static String GENERO_FEMENINO="Femenino";
    
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * El id (cédula) del usuario
     */
    private int id;
    
    /**
     * El nombre del usuario
     */
    private String nombre;
    
    /**
     * La edad del usuario
     */
    private int edad;
    
    /**
     * El teléfono del usuario
     */
    private int telefono;
    
    /**
     * El genero del usuario
     */
    private String genero;
    
    /**
     * La foto del usuario
     */
    private String foto;
    
    /**
     * El registro médico del usuario
     */
    private String registroMedico;
    
    /**
     * Las suscripciones del usuario del usuario
     */
    private ArrayList suscripciones;
    
    /**
     * Los registros de tiempos del usuario
     */
    private ArrayList registrosTiempos;
    
    
    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    
    /**
     * Crea un nuevo usuario con sus datos personales. 
     * Inicializa los vectores de suscripciones y de registros de tiempo
     * @param elId El id del usuario - elId != null && != ""
     * @param elNombre El nombre del usuario - elNombre != null && != ""
     * @param laEdad La edad del usuario - laEdad > 0
     * @param elTelefono El teléfono del usuario - elTelefono > 0
     * @param elGenero El género del usuario - elGenero != null && != ""
     * @param laFoto La foto del usuario - laFoto != null && != ""
     * @param elRegistroMedico El registro médico del usuario - elRegistroMedico != null && != ""
     */
    public Usuario(int elId, String elNombre, int laEdad,int elTelefono,String elGenero,String laFoto, String elRegistroMedico)
    {
        id=elId;
        nombre=elNombre;
        edad=laEdad;
        telefono=elTelefono;
        genero=elGenero;
        foto=laFoto;
        registroMedico=elRegistroMedico;
        suscripciones=new ArrayList();
        registrosTiempos=new ArrayList( );
        
        verificarInvariante();

    }
    
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    
    /**
     * Retorna la siguiente suscripción a aquella cuyo id es recibido por parámetro. 
     * La siguiente suscripción corresponde a aquella que está en la siguiente posición del vector
     * de posiciones. Si no existe la suscripción con el id dado, retorna la primera suscripción del usuario.
     * @param idSuscripcion el id de la suscripción - idSuscripcion != null
     * @return La siguiente suscripción a aquella cuyo id es recibido por parámetro o null si no hay suscripciones
     */
    public Suscripcion darSiguienteSuscripcion(String idSuscripcion)
    {
     Suscripcion suscripcion=null;
     int pos=darPosicionSuscripcion( idSuscripcion );
     if(pos<(suscripciones.size( )-1))
     {
         pos++;
         suscripcion=(Suscripcion)suscripciones.get( pos );
     }
     
     return suscripcion;
    }
    
    
    /**
     * Retorna la anterior suscripción a aquella cuyo id es recibido por parámetro.
     * La suscripción anterior corresponde a aquella que está en la posición anterior del vector.
     * @param idSuscripcion el id de la suscripción - idSuscripcion != null
     * @return La anterior suscripción a aquella cuyo id es recibido por parámetro  o null si no hay suscripciones
     */
    public Suscripcion darAnteriorSuscripcion(String idSuscripcion)
    {
        Suscripcion suscripcion=null;
        int pos=darPosicionSuscripcion( idSuscripcion );
        if(pos>0)
        {
            pos--;
            suscripcion=(Suscripcion)suscripciones.get( pos );
        }      
        return suscripcion;
    }
    
    
    /**
     * Retorna la posición en el vector de suscripciones de la suscripción cuyo id es recibido por parámetro.
     * Si no se encuentra una suscripción con el id dado, retorna -1
     * @param idSuscripcion El id de la suscripción - idSuscripción != null
     * @return La posición de la suscripción cuyo id es recibido por parámetro, -1 si no es encontrada
     */
    public int darPosicionSuscripcion(String idSuscripcion)
    {
        
        int posicion=-1;
        boolean encontro=false;
        for(int i=0;i<suscripciones.size( ) && !encontro ;i++)
        {
            Suscripcion temp=(Suscripcion)suscripciones.get( i );
            if(temp.darId( ).equals( idSuscripcion ))
            {
                posicion=i;
                encontro=true;
            }
        }
        
        return posicion;
    }
    
    /**
     * Retorna el número de suscripciones del usuario
     * @return El número de suscripciones del usuario
     */
    public int darNumSuscripciones()
    {
        return suscripciones.size( );
    }
    
    /**
     * Retorna el número de registros de tiempo
     * @return El número de registros de tiempo
     */
    public int darNumRegistrosTiempo()
    {
        return registrosTiempos.size( );
    }
    
    /**
     * Retorna el id del usuario
     * @return El id del usuario
     */
    public int darId()
    {
        return id;
    }
    
    /**
     * Retorna la edad del usuario
     * @return La edad del usuario
     */
    public int darEdad()
    {
        return edad;
    }
    
    /**
     * Retorna la ruta de la foto del usuario
     * @return La ruta de la foto del usuario
     */
    public String darFoto()
    {
        return foto;
    }
    
    /**
     * Retorna el genero del usuario
     * @return El genero del usuario
     */
    public String darGenero()
    {
        return genero;
    }
    
    /**
     * Retorna el nombre del usuario
     * @return El nombre del usuario
     */
    public String darNombre()
    {
        return nombre;
    }
    
    /**
     * Retorna el registro médico del usuario
     * @return El registro médico del usuario
     */
    public String darRegistroMedico()
    {
        return registroMedico;
    }
    
    /**
     * Retorna el teléfono del usuario
     * @return El teléfono del usuario
     */
    public int darTelefono()
    {
        return telefono;
    }
    
    /**
     * Agrega una nueva suscripción a la lista de suscripciones.
     * <b> post: </b> Agrega una nueva suscripción al usuario con  id: idUsuario+numSuscripciones
     * @param anio año de inicio - anio > 0
     * @param mes Mes de inicio - mes > 0
     * @param dia Dia de inicio - dia > 0
     * @param hora Hora de inicio - hora > 0
     * @param minutos Minutos de inicio - minutos > 0
     * @param tipoSuscripcion Tipo de suscripción - tipoSuscripcion != null
     */
    public void crearSuscripcion(int anio, int mes, int dia, int hora, int minutos, String tipoSuscripcion) {
    	Fecha fechaInicioSuscripcion=new Fecha(anio, mes, dia, hora, minutos);
        Suscripcion suscripcion=new Suscripcion(""+id+darNumSuscripciones( ),fechaInicioSuscripcion, tipoSuscripcion);
    	suscripciones.add( suscripcion );
    	
    } 
    
    /**
     * Crea un registro de tiempo para el usuario. Si ya existe un registro de tiempo <br>
     * en la fecha recibida por parámetro, se lanza una excepción. 
     * <b> post: </b>Se agrego un nuevo registro de tiempo con el ID: el id del usuario concatenado con el número de, <br> 
     * registros de tiempo que ya tiene el usuario (sin incluir al registro que se acaba de crear) <br>
     * @param fechaEntrada La fecha del día para el cual se va a crear el ingreso - fechaEntrada != null
     * @throws RegistroTiempoExisteException  En caso que ya exista un registro de tiempo
     * en la fecha recibida por parámetro.
     */
    public void crearRegistroTiempo(Fecha fechaEntrada) throws RegistroTiempoExisteException
    {
        if(!existeRegistroTiempo( fechaEntrada ))
        {
            RegistroTiempo registro= new RegistroTiempo( fechaEntrada );
            registro.asignarId( id+""+registrosTiempos.size( ) );
            registrosTiempos.add(registro);
        }
        else
        {
            throw new RegistroTiempoExisteException( fechaEntrada );
        }
    }
    
    /**
     * Cierra el último registro de tiempo para el usuario. Si no existe un registro de tiempo abierto, se lanza una excepción.
     */
    public void cerrarRegistroTiempo(Fecha fechaSalida) throws HoraSalidaExistenteParaRegistroException {
    	RegistroTiempo tempRegistro = darUltimoRegistroTiempo();
    	if (!tempRegistro.existeTiempoSalida()) tempRegistro.agregarTiempoSalida(fechaSalida);
    	else throw new HoraSalidaExistenteParaRegistroException(fechaSalida);
    }
    /**
     * Valida si ya existe un registro de tiempo en la fecha recibida por parámetro
     * @param fechaEntrada La fecha de entrada al gimnasio - fechaEntrada != null
     * @return true , en caso que ya haya ingresado durante el día de la fecha de entrega,
     * false en caso contrario
     */
    public boolean existeRegistroTiempo(Fecha fechaEntrada)
    {
        boolean existe=false;
        for(int i=0; i<registrosTiempos.size( ) && !existe;i++)
        {
            RegistroTiempo temp= (RegistroTiempo)registrosTiempos.get( i );
            Fecha tiempoEntradaTemp=temp.darTiempoEntrada( );
            if(fechaEntrada.compararFechas( tiempoEntradaTemp )==0)
            {
                existe=true;
            }
            
            
        }
        
        return existe;
    }
    
    /**
     * Retorna el registro de tiempo cuya fecha de entrada es igual a la recibida por parámetro
     * @param laFecha La fecha para la cual se quiere buscar el registro de tiempo - laFecha != null
     * @return El registro de tiempo cuya fecha se igual a la recibida por parámetro
     */
    public RegistroTiempo darRegistroTiempo(Fecha laFecha)
    {
    	boolean termino=false;
    	RegistroTiempo elRegistro=null;
    	for(int i=0; i<registrosTiempos.size() && ! termino ;i++)
    	{
    		RegistroTiempo registroTemp=(RegistroTiempo)registrosTiempos.get(i);
    		Fecha fechaTemp=registroTemp.darTiempoEntrada();
    		if(laFecha.compararFechas(fechaTemp)==0)
    		{
    			termino=true;
    			elRegistro=registroTemp;
    		}
    	}
    	
    	return elRegistro;
    }
    
    
    /**
     * Retorna los registros de tiempos
     * @return Los registros de tiempos
     */
    public ArrayList darRegistrosTiempo()
    {
        return registrosTiempos;
    }
    
    
    /**
     * Retorna el último registro de tiempo de un usuario.
     * El último registro es el que está en la última posición del vector
     * @return El último registro de tiempo del usuario. null en caso que no hay ningún registro
     */
    public RegistroTiempo darUltimoRegistroTiempo()
    {
        RegistroTiempo registro= null;
        if(registrosTiempos.size( )>0)
        {
            int numRegistros=registrosTiempos.size( );
            registro=(RegistroTiempo)registrosTiempos.get( numRegistros -1);
        }
        
        return registro;
    }
    
    /**
     * Retorna la última suscripción del usuario
     * La última suscripción es la que está en la última posición de la lista de suscripciones
     * @return La última suscripción del usuario
     */
    public Suscripcion darUltimaSuscripcion()
    {
        int indiceUltimaSuscripcion=suscripciones.size()-1;
        Suscripcion ultimaSuscripcion=(Suscripcion)suscripciones.get( indiceUltimaSuscripcion );
        return ultimaSuscripcion;
    }

    /**
     * Retorna el siguiente registro de tiempo
     * En caso que se esté en el último registro, retorna null 
     * @param idRegistroTiempo El ID del registro de tiempo
     * @return El siguiente registro de tiempo
     */
    public RegistroTiempo darSiguienteRegistroTiempo( String  idRegistroTiempo )
    {
        boolean encontro=false;
        int posUltimoRegistro=registrosTiempos.size( )-1;
        RegistroTiempo siguienteRegistro=null;
        for(int i=0;i<registrosTiempos.size( )&&!encontro;i++)
        {
            RegistroTiempo registroTemp=(RegistroTiempo)registrosTiempos.get( i );
            String idTemp=registroTemp.darId();
            if(idRegistroTiempo.equals(idTemp))
            {
                encontro=true;
                if(i<posUltimoRegistro)
                {
                    siguienteRegistro=(RegistroTiempo)registrosTiempos.get( i+1 );
                }
                
            }
        }
        
        return siguienteRegistro;
        
    }


    /**
     * Retorna el anterior registro de tiempo
     * En caso que se esté en el último registro, retorna null 
     * @param idRegistroTiempo El ID del registro de tiempo - idRegistroTiempo != null
     * @return El anterior registro de tiempo
     */
    public RegistroTiempo darAnteriorRegistroTiempo( String idRegistroTiempo )
    {
        boolean encontro=false;
        int posPrimerRegistro=0;
        RegistroTiempo anteriorRegistro=null;
        for(int i=0;i<registrosTiempos.size( )&&!encontro;i++)
        {
            RegistroTiempo registroTemp=(RegistroTiempo)registrosTiempos.get( i );
            String idTemp=registroTemp.darId();
            if(idRegistroTiempo.equals(idTemp))
            {
                encontro=true;
                if(i>posPrimerRegistro)
                {
                    anteriorRegistro=(RegistroTiempo)registrosTiempos.get( i-1 );
                }
                
            }
        }
        
        return anteriorRegistro;
        
        
    }
    
    //-----------------------------------------------------------------
    // Invariante
    //-----------------------------------------------------------------
    private void verificarInvariante() {
    	assert (edad > 0) : "Edad inválida";
    	Integer telPrueba = new Integer(telefono);
    	assert (telPrueba.toString().length() >= 7) : "Teléfono inválido";
    	assert (genero.equals(GENERO_FEMENINO) || genero.equals(GENERO_MASCULINO)) : "Genero inválido";
    	assert (foto != null && foto.length() >0) : "Foto inválida";
    	assert (registroMedico != null && registroMedico.length() >0) : "Registro médico inválido";
    	assert (suscripciones != null) : "La lista de suscripciones no ha sido inicializada";
    	assert (registrosTiempos != null) : "La lista de registros de tiempos no ha sido inicializada";
    }
}

  