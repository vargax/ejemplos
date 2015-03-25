package uniandes.cupi2.bodyCupi2.mundo;

import java.io.Serializable;

/**
 * Clase que contiene un registro de entrada y salida para un usuario
 * <b> inv: </b><br>
 * tiempoEntrada != null <br>
 * Si tiene un tiempoSalida asignado este sea mayor a tiempoEntrada <br>
 */
public class RegistroTiempo implements Serializable
{
 
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 200L;
    
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * El id de un registro de tiempo
     */
    private String id;
    
    /**
     * El tiempo de entrada al gimnasio: Fecha y hora
     */
    private Fecha tiempoEntrada;
    
    /**
     * El tiempo de salida del gimnasio: Fecha y hora
     */
    private Fecha tiempoSalida;
    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    
    /**
     * Crea el registro de tiempo del usuario con la hora de llegada
     * @param laHoraLlegada La hora de llegada del usuario al gimnasio
     */
    public RegistroTiempo(Fecha laHoraLlegada)
    {
        tiempoEntrada=laHoraLlegada;
        verificarInvariante();
        
    }
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Registra el tiempo de salida del usuario
     * @param elTiempoSalida La hora de salida
     */
    public void agregarTiempoSalida(Fecha elTiempoSalida)
    {
        tiempoSalida=elTiempoSalida;
        verificarInvariante();
    }
    
    /**
     * Retorna el tiempo de entrada al gimnasio
     * @return El tiempo de entrada al gimnasio
     */
    public Fecha darTiempoEntrada()
    {
        return tiempoEntrada;
    }
    
    /**
     * Retorna el tiempo de salida del gimnasio
     * @return El tiempo de salida del gimnasio
     */
    public Fecha darTiempoSalida()
    {
        return tiempoSalida;
    }
    
    /**
     * Informa si tiene un tiempo de salida asigado.
     * @return true en caso que se haya asignado tiempoSalida. false en caso contrario
     */
    public boolean existeTiempoSalida()
    {
    	boolean existe=false;
    	if(tiempoSalida!=null)
    	{
    		existe=true;
    	}
    	return existe;
    }
    
    
    /**
     * Asigna un id a un registro de tiempo
     * @param elId El id de un registro de tiempo - elId != null
     */
    public void asignarId(String elId)
    {
        id=elId;
        verificarInvariante();
    }
    
    
    /**
     * Retorna el ID del registro de tiempo
     * @return El ID del registro de tiempo
     */
    public String darId()
    {
        return id;
    }
    
    //-----------------------------------------------------------------
    // Invariante
    //-----------------------------------------------------------------
    
    /**
     * Verifica el invariante de la clase. <br>	
     * <b> inv: </b><br>
     * tiempoEntrada != null <br>
     * Si tiene un tiempoSalida asignado este sea mayor a tiempoEntrada <br>
     */
    private void verificarInvariante()
    {
    	assert(tiempoEntrada!=null):"El tiempo de entrada debe ser inicializado al momento de crear el registro de tiempo";
    	if(tiempoSalida!=null)
    		assert(tiempoSalida.compararFechas(tiempoEntrada)>=0):"El tiempo de salida debe ser posterior o igual al de entrada";
    }

}
