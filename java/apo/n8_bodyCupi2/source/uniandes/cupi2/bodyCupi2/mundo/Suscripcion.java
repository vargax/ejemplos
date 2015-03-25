package uniandes.cupi2.bodyCupi2.mundo;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Clase para modelar una suscripción de un usuario en el gimnasio <br>
 * <b> inv: </b><br>
 * La fechaInicio esté inicializada <br>
 * El tipo de suscripción debe ser: AMATEUR, REGULAR o MASTER <br>
 * La duración de la suscripción corresponda al tipo de suscripción <br>
 */
public class Suscripcion implements Serializable
{
    
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
	public static final long serialVersionUID = 400L;
    
    /**
     * Constante que representa una suscripción amateur
     */
    public final static String SUSCRIPCION_AMATEUR="AMATEUR";
    
    /**
     * Constante que representa una suscripción regular
     */
    public final static String SUSCRIPCION_REGULAR="REGULAR";
    
    /**
     * Constante que representa una suscripción master
     */
    public final static String SUSCRIPCION_MASTER="MASTER";
    
    /**
     * El numero de días de una suscripción AMATEUR
     */
    public final static int NUM_DIAS_AMATEUR=2;
    
    /**
     * El numero de días de una suscripción REGULAR
     */
    public final static int NUM_DIAS_REGULAR=3;
    
    /**
     * El numero de días de una suscripción MASTER
     */
    public final static int NUM_DIAS_MASTER=4;
    
    /**
     * La suscripción está activa
     */
    public final static String ESTADO_ACTIVA="ACTIVA";
    
    /**
     * La suscripción está vencida
     */
    public final static String ESTADO_VENCIDA="VENCIDA";
    
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * El id único de una suscripción
     */
    private String id;
    
    /**
     * La fecha de inicio de la suscripción
     */
    private Fecha fechaInicio;
    
    /**
     * El tipo de la suscripción
     */
    private String tipoSuscripcion;
    
    /**
     * La duración en días de la suscripción
     */
    private int duracion;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    
    /**
     * Construye una nueva suscripción con la información dada por parámetro <br>
     * <b> post: <b/> Se le asigno a duración, la duración correspondiente según el tipo de suscripción <br>
     * @param laId identificación de la suscripción - laId !0 null
     * @param laFechaApertura la fecha de apertura - laFechaApertura != null
     * @param elTipoDeSuscripcion el tipo de suscripción - elTipoDeSuscripcion != null
     */
    public Suscripcion(String laId, Fecha laFechaApertura, String elTipoDeSuscripcion)
    {
    	id = laId;
        fechaInicio=laFechaApertura;
        tipoSuscripcion=elTipoDeSuscripcion;
        if(tipoSuscripcion.equals( SUSCRIPCION_AMATEUR ))
        {
            duracion=NUM_DIAS_AMATEUR;   
        }
        else if(tipoSuscripcion.equals( SUSCRIPCION_REGULAR ))
        {
            duracion=NUM_DIAS_REGULAR;   
        }
        else
        {
            duracion=NUM_DIAS_MASTER;
        }
        verificarInvariante();
        
    }
    
    //-----------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------
    
    
    /**
     * Retorna el id de la suscripción
     * @return El id de la suscripción
     */
    public String darId()
    {
        return id;
    }
    
    /**
     * Retorna la fecha de inicio de la suscripción con formato
     * @return La fecha de inicio de la suscripción con formato
     */
    public String darFechaInicioConFormato()
    {
        return fechaInicio.toString( );
    }

    
    /**
     * Retorna el tipo de la suscripción
     * @return El tipo de la suscripción 
     */
    public String darTipoSuscripcion()
    {
        return tipoSuscripcion;
    }
    
    /**
     * Retorna la duración de la suscripción
     * @return La duración de la suscripción
     */
    public int darDuracion()
    {
    	return duracion;
    }
    
    /**
     * La suscripción está vencida si la fecha actual es mayor o igual a la fecha de apertura
     * de la suscripción más el número de días que esta dura
     * @return El estado de la suscripción ESTADO_ACTIVA o ESTADO_VENCIDA
     */
    public String darEstado()
    {
        String estado="";
        
        //Obtener la fecha actual
        Calendar fechaTempActual=Calendar.getInstance( );
        int dia=fechaTempActual.get( Calendar.DAY_OF_MONTH );
        int mes=fechaTempActual.get( Calendar.MONTH)+1;
        int anio=fechaTempActual.get( Calendar.YEAR );
        Fecha fechaActual= new Fecha(anio, mes, dia, 0, 0);
        
        //Obtener la fecha de vencimiento: Suma a la fecha de inicio el número de días de la suscripción
        Calendar fechaTempVencimientoSuscripcion=Calendar.getInstance( );
        fechaTempVencimientoSuscripcion.set( fechaInicio.darAnio( ), fechaInicio.darMes( )-1, fechaInicio.darDia( ) );
        fechaTempVencimientoSuscripcion.add( Calendar.DAY_OF_MONTH, darDuracion());
        
        int diaVencimiento=fechaTempVencimientoSuscripcion.get( Calendar.DAY_OF_MONTH );
        int mesVencimiento=fechaTempVencimientoSuscripcion.get( Calendar.MONTH)+1;
        int anioVencimiento=fechaTempVencimientoSuscripcion.get( Calendar.YEAR );
        Fecha fechaVencimiento=new Fecha(anioVencimiento,mesVencimiento,diaVencimiento,0,0);
        
        //Comparar si la fecha actual es igual o posterior a la fecha de vencimiento de la suscripción
        int comparacion=fechaActual.compararFechas( fechaVencimiento );
        if(comparacion==0 || comparacion==1)
            estado=ESTADO_VENCIDA;
        else
            estado=ESTADO_ACTIVA;
        return estado; 
    }
    
    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------
    
    /**
     * Verifica el invariante de la clase <br>
	 * <b> inv: </b><br>
	 * La fechaInicio esté inicializada <br>
	 * El tipo de suscripción debe ser: AMATEUR, REGULAR o MASTER <br>
	 * La duración de la suscripción corresponda al tipo de suscripción <br>
	 */
    private void verificarInvariante()
    {
    	assert(fechaInicio!=null): "La fecha de inicio debe estar inicializada";
    	assert(tipoSuscripcion.equals(SUSCRIPCION_AMATEUR)||tipoSuscripcion.equals(SUSCRIPCION_REGULAR)||tipoSuscripcion.equals(SUSCRIPCION_MASTER)): "El tipo de suscripción debe ser: AMATEUR, REGULAR o MASTER";
    	if(tipoSuscripcion.equals( SUSCRIPCION_AMATEUR ))
        {
            assert(duracion==NUM_DIAS_AMATEUR):"La duración no es la esperada";   
        }
        else if(tipoSuscripcion.equals( SUSCRIPCION_REGULAR ))
        {
        	assert(duracion==NUM_DIAS_REGULAR):"La duración no es la esperada";   
        }
        else
        {
        	assert(duracion==NUM_DIAS_MASTER):"La duración no es la esperada";
        }
    }

}
