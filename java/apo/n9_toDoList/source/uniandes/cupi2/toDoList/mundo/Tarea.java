/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_toDoList
 * Autor: Carlos Navarrete Puentes - 24-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.toDoList.mundo;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que representa la información de una tarea <br>
 * <b>inv: </b> <br>
 * nombre != null y nombre != ""<br>
 * descripcion != null y descripcion != ""<br>
 * fechaInicio != null <br>
 * fechaFin puede ser null. Si fechaFin != null, entonces fechaInicio < fechaFin <br>
 */
public class Tarea implements Serializable
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Identificación para la serialización
     */
    private static final long serialVersionUID = -1785042612153526773L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Siguiente tarea de la lista que pertenece a la misma categoría
     */
    private Tarea siguienteTarea;

    /**
     * Nombre de la tarea
     */
    private String nombre;

    /**
     * Descripción de la tarea
     */
    private String descripcion;

    /**
     * Fecha de inicio de la tarea
     */
    private Date fechaInicio;

    /**
     * Fecha de inicio de la tarea
     */
    private Date fechaFin;

    /**
     * Dice si una tarea está terminada o no
     */
    private boolean terminada;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva tarea con los datos básicos requeridos <br>
     * <b> post: </b> Se inicializó la siguiente tarea en null y terminada en false. <br>
     * @param nombreP Nombre de la tarea. nombreP != null y nombreP != ""
     * @param descripcionP Nombre de la tarea. descripcionP != null y descripcionP != ""
     * @param fechaInicioP Nombre de la tarea. fechaInicioP != null 
     * @param fechaFinTarea Fecha final de la tarea. Puede ser null
     */
    public Tarea( String nombreP, String descripcionP, Date fechaInicioP, Date fechaFinTarea )
    {
        nombre = nombreP;
        descripcion = descripcionP;
        fechaInicio = fechaInicioP;
        fechaFin = fechaFinTarea;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna la siguiente tarea
     * @return siguienteTarea Siguiente tarea
     */
    public Tarea darSiguienteTarea( )
    {
        return siguienteTarea;
    }

    /**
     * Retorna el nombre de la tarea
     * @return nombre Nombre de la tarea
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna la descripción de la tarea
     * @return Descripción de la tarea
     */
    public String darDescripcion( )
    {
        return descripcion;
    }

    /**
     * Retorna la fecha de inicio de la tarea
     * @return fechaInicio Fecha de inicio de la tarea
     */
    public Date darFechaInicio( )
    {
        return fechaInicio;
    }

    /**
     * Retorna la fecha de finalización de la tarea
     * @return fechaFin Fecha de finalización de la tarea
     */
    public Date darFechaFin( )
    {
        return fechaFin;
    }

    /**
     * Retorna si la tarea está terminada o no
     * @return terminada true si la tarea está terminada, false en caso contrario
     */
    public boolean estaTerminada( )
    {
        return terminada;
    }

    /**
     * Retorna si la tarea está vencida o no. Una tarea se vence si no se ha terminado antes de la fecha de finalización.
     * @return terminada true si la tarea está vencida, false en caso contrario
     */
    public boolean estaVencida( )
    {
        Date hoy = new Date( );
        return !terminada && fechaFin != null && hoy.after( fechaFin );
    }

    /**
     * Cambia la siguiente tarea.
     * @param siguienteTareaP Siguiente tarea en la lista. Puede ser null
     */
    public void cambiarSiguienteTarea( Tarea siguienteTareaP )
    {
        siguienteTarea = siguienteTareaP;
    }

    /**
     * Edita la información de la tarea.
     * @param descripcionP Descripción de la tarea. descripcionP != null
     * @param fechaInicioP Fecha de Inicio de la tarea. fechaInicioP != null
     * @param fechaFinP Fecha de finalización de la tarea. Puede ser null. Si fechaFinP != null entonces fechaFinP > fechaInicioP. 
     */
    public void editarTarea( String descripcionP, Date fechaInicioP, Date fechaFinP )
    {
        descripcion = descripcionP;
        fechaInicio = fechaInicioP;
        fechaFin = fechaFinP;
        verificarInvariante( );
    }

    /**
     * Termina una tarea. <br>
     * <b> post: </b> Se asigno a terminada true. <br>
     */
    public void terminarTarea( )
    {
        terminada = true;
        verificarInvariante( );
    } 
    
    /**
     * Crea una copia de la tarea actual
     * @return Un nuevo objeto Tarea con la misma información de la tarea
     */
    public Tarea darCopia( )
    {
        Tarea copia = new Tarea( nombre, descripcion, fechaInicio, fechaFin );
        if( estaTerminada( ) )
        {
            copia.terminarTarea( );
        }
        return copia;
    }

    /**
     * Retorna una cadena de caracteres que representa a la tarea
     * @return Se retornó una cadena con el nombre de la tarea
     */
    public String toString( )
    {
        return nombre;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Método que verifica la invariante de la clase <br>
     * <b>inv: </b> <br>
     * nombre != null y nombre != ""<br>
     * descripcion != null y descripcion != ""<br>
     * fechaInicio != null <br>
     * Si fechaFin != null, entonces fechaInicio < fechaFin <br>
     */
    private void verificarInvariante( )
    {
        assert nombre != null : "El nombre es null";
        assert !nombre.equals("") : "El nombre es vacío";
        assert descripcion != null : "La descripción es null";
        assert !descripcion.equals("") : "La descripción es vacía";
        assert fechaInicio != null : "La fecha de inicio no existe";
        if( fechaFin != null )
        {
            assert fechaInicio.compareTo( fechaFin ) <= 0 : "La fecha de finalización es posterior";
        }
    }

}
