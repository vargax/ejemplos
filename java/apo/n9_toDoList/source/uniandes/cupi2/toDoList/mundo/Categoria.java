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
 * Clase que representa una Categoría del toDoList
 */
public class Categoria implements Serializable
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Identificador para la serialización
     */
    private static final long serialVersionUID = 2612921133883523736L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Categoría anterior en la lista.
     */
    private Categoria categoriaAnterior;

    /**
     * Categoría siguiente en la lista.
     */
    private Categoria categoriaSiguiente;

    /**
     * Nombre de la Categoría.
     */
    private String nombreCategoria;

    /**
     * Primera tarea en la lista en esta Categoría.
     */
    private Tarea primeraTarea;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Categoría. No tiene ninguna tarea asociada.
     * @param nombreCategoriaP Nombre de la Categoría. nombreCategoriaP != null && nombreCategoriaP != ""
     */
    public Categoria( String nombreCategoriaP )
    {
        nombreCategoria = nombreCategoriaP;
        primeraTarea = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre de la Categoría
     * @return Nombre de la Categoría
     */
    public String darNombre( )
    {
        return nombreCategoria;
    }

    /**
     * Retorna la Categoría anterior
     * @return anterior Categoría anterior
     */
    public Categoria darAnteriorCategoria( )
    {
        return categoriaAnterior;
    }

    /**
     * Retorna la siguiente Categoría
     * @return siguiente Siguiente Categoría
     */
    public Categoria darSiguienteCategoria( )
    {
        return categoriaSiguiente;
    }

    /**
     * Retorna la primera tarea
     * @return La primera tarea
     */
    public Tarea darPrimeraTarea( )
    {
        return primeraTarea;
    }

    /**
     * Cambia la Categoría anterior en la lista
     * @param anteriorP Categoría anterior. Puede ser null
     */
    public void cambiarAnterior( Categoria anteriorP )
    {
        categoriaAnterior = anteriorP;
    }

    /**
     * Cambia la siguiente categoría en la lista
     * @param siguienteP Siguiente categoría. Puede ser null
     */
    public void cambiarSiguiente( Categoria siguienteP )
    {
        categoriaSiguiente = siguienteP;

    }

    /**
     * Agrega una tarea al final de la lista de tareas de la categoría. No se pueden agregar dos tareas con el mismo nombre. <br>
     * <b> post: </b> Se agregó una nueva tarea al final de la lista de tareas. <br>
     * @param nombreP Nombre de la tarea. nombreP != null y nombreP != ""
     * @param descripcionP Descripción de la tarea. descripcionP != null y descripcionP != ""
     * @param fechaInicioP Fecha de inicio de la tarea. fechaInicioP != null
     * @param fechaFinTarea Fecha de finalización de la tarea. Puede ser null. Si fechaFinTarea != null entonces fechaFinTarea > fechaInicioP.
     * @throws ElementoExisteException Si encuentra una tarea con el mismo nombre y fecha de inicio
     */
    public void agregarTarea( String nombreP, String descripcionP, Date fechaInicioP, Date fechaFinTarea ) throws ElementoExisteException
    {
    	Tarea nuevaTarea = new Tarea(nombreP, descripcionP, fechaInicioP, fechaFinTarea);
    	
    	if (primeraTarea == null) 
    		primeraTarea = nuevaTarea;
    	else if (primeraTarea.darNombre().equals(nombreP) && primeraTarea.darFechaInicio().equals(fechaInicioP)) 
    		throw new ElementoExisteException("Ya existe otra tarea con este nombre.");
    	else {
    		Tarea tempTarea = primeraTarea;
	        while (tempTarea.darSiguienteTarea() != null) {
	        	tempTarea = tempTarea.darSiguienteTarea();
	        	if (tempTarea.darNombre().equals(nombreP) && tempTarea.darFechaInicio().equals(fechaInicioP))
	        		throw new ElementoExisteException("Ya existe otra tarea con este nombre.");
	        }
        tempTarea.cambiarSiguienteTarea(nuevaTarea);
    	}
    }

    /**
     * Busca una tarea con el nombre dado por parámetro. 
     * @param nombreP Nombre de la tarea que se está buscando. nombreP != null y nombreP != ""
     * @return La tarea si la encuentra, en caso contrario retorna null.
     */
    public Tarea buscarTarea( String nombreP )
    {
    	Tarea tempTarea = primeraTarea;
        while (tempTarea != null) {
        	if (tempTarea.darNombre().equals(nombreP)) return tempTarea;
        	tempTarea = tempTarea.darSiguienteTarea();
        }
        return null;
    }

    /**
     * Cambia la descripción, fecha de Inicio y fecha de finalización de la tarea con nombre dado. Se lanza excepción si la tarea no existe.
     * <b> post: </b> Se editó la información de la tarea con el nombre dado. <br>
     * @param nombreTarea Nombre de la tarea que se desea cambiar. nombreTarea != null y nombreTarea != ""
     * @param descripcionP Nueva descripción de la tarea dada. descripcionP != null y descripcionP != ""
     * @param fechaInicioP Fecha de Inicio de la tarea dada. fechaInicioP != null
     * @param fechaFinP Fecha de finalización de la tarea dada. Puede ser null. Si tarea.fechaFinP != null entonces tarea.fechaFinP > tarea.fechaInicio.
     * @throws ElementoNoExisteException Si no existe una tarea con el nombre dado
     */
    public void editarTarea( String nombreTarea, String descripcionP, Date fechaInicioP, Date fechaFinP ) throws ElementoNoExisteException
    {
    	Tarea tempTarea = primeraTarea;
    	
    	boolean continuar = true;
        while (tempTarea != null && continuar) {
        	if (tempTarea.darNombre().equals(nombreTarea)) continuar = false;
        	else tempTarea = tempTarea.darSiguienteTarea();
        }
        if (continuar) throw new ElementoNoExisteException("No se encontró la tarea especificada");
        tempTarea.editarTarea(descripcionP, fechaInicioP, fechaFinP);        
    }

    /**
     * Elimina todas las tareas terminadas de la lista
     * @return Retorna el número de tareas eliminadas
     */
    public int eliminarTareasTerminadas( )
    {
    	if (primeraTarea == null) return 0;
    	int contador = 0;
    	// Buscando primera tarea de la lista sin terminar
        Tarea tempTarea = darPrimeraTarea();
        while (tempTarea != null && tempTarea.estaTerminada()) {
        	tempTarea = tempTarea.darSiguienteTarea();
        	contador++;
        }
        if (contador !=0) primeraTarea = tempTarea;
        // Buscando otras tareas terminadas
        while (tempTarea != null) {
        	Tarea siguienteTarea = tempTarea.darSiguienteTarea();
        	while (siguienteTarea != null && siguienteTarea.estaTerminada()) {
        		siguienteTarea = siguienteTarea.darSiguienteTarea();
        		contador++;
        	}
        	tempTarea.cambiarSiguienteTarea(siguienteTarea);
        	tempTarea = tempTarea.darSiguienteTarea();
        }
        return contador;
    }

    /**
     * Marca una tarea como terminada dado su nombre. Se lanza una excepción si la tarea no existe.
     * @param nombre Nombre de la tarea que se quiere terminar. nombre != null y nombre != ""
     * @throws ElementoNoExisteException Si no existe una tarea con el nombre dado
     */
    public void terminarTarea( String nombre ) throws ElementoNoExisteException
    {
        Tarea tempTarea = buscarTarea(nombre);
        if (tempTarea == null) throw new ElementoNoExisteException("No es posible terminar esta tarea: La tarea "+nombre+" no existe");
        tempTarea.terminarTarea();
    }

    /**
    * Retorna una nueva lista encadenada que contiene las tareas pendientes. Una tarea es considerada pendiente si no ha sido terminada.
    * Cada tarea de la lista que se retorna es una copia de la tarea original.
    * @return Lista encadenada con las tareas pendientes. Si no hay ninguna, se retorna null.
    */
    public Tarea darTareasPendientes( )
    {
        Tarea primeraPendiente = primeraTarea;
        // Busco la primera tarea pendiente
        while (primeraPendiente != null && primeraPendiente.estaTerminada()) 
        	primeraPendiente = primeraPendiente.darSiguienteTarea();
        // Si no encuentro ninguna devuelvo null
        if (primeraPendiente == null) return null;
        // Si sí encuentro alguna creo una nueva lista
        Tarea nuevaLista = new Tarea(primeraPendiente.darNombre(), primeraPendiente.darDescripcion(), primeraPendiente.darFechaInicio(), primeraPendiente.darFechaFin());
        // Guardo la cabeza de la lista
        Tarea cabezaNuevaLista = nuevaLista;
        // Voy buscando más tareas pendientes
        Tarea recorredora = primeraPendiente.darSiguienteTarea();
        while (recorredora != null) {
        	if (!(recorredora.estaTerminada())) {
        		Tarea tempTarea = new Tarea(recorredora.darNombre(), recorredora.darDescripcion(), recorredora.darFechaInicio(), recorredora.darFechaFin());
        		nuevaLista.cambiarSiguienteTarea(tempTarea);
        		nuevaLista = nuevaLista.darSiguienteTarea();
        	}
        	recorredora = recorredora.darSiguienteTarea();
        }
        // Devuelvo la cabeza de la lista
        return cabezaNuevaLista;
    }

    /**
     * Retorna una nueva lista encadenada que contiene las tareas terminadas.
     * Cada tarea de la lista que se retorna es una copia de la tarea original. 
     * @return Lista encadenada con las tareas terminadas. Si no hay ninguna, se retorna null.
     */
    public Tarea darTareasTerminadas( )
    {
    	Tarea primeraTerminada = primeraTarea;
        while (primeraTerminada != null && !(primeraTerminada.estaTerminada())) 
        	primeraTerminada = primeraTerminada.darSiguienteTarea();
        
        if (primeraTerminada == null) return null;
        
        Tarea nuevaLista = new Tarea(primeraTerminada.darNombre(), primeraTerminada.darDescripcion(), primeraTerminada.darFechaInicio(), primeraTerminada.darFechaFin());
        Tarea cabezaNuevaLista = nuevaLista; 
        
        Tarea recorredora = primeraTerminada.darSiguienteTarea();
        while (recorredora != null) {
        	if (recorredora.estaTerminada()) {
        		Tarea tempTarea = new Tarea(recorredora.darNombre(), recorredora.darDescripcion(), recorredora.darFechaInicio(), recorredora.darFechaFin());
        		nuevaLista.cambiarSiguienteTarea(tempTarea);
        		nuevaLista = nuevaLista.darSiguienteTarea();
        	}
        	recorredora = recorredora.darSiguienteTarea();
        }
        return cabezaNuevaLista;
    }

    /**
     * Retorna una nueva lista encadenada que contiene las tareas vencidas. Una tarea se considera vencida si no ha sido terminada y su fecha de
     * finalización es anterior a la fecha actual. Cada tarea de la lista que se retorna es una copia de la tarea original.
     * @return Lista encadenada con las tareas terminadas. Si no hay ninguna, se retorna null.
     */
    public Tarea darTareasVencidas( )
    {
    	Tarea primeraVencida = primeraTarea;
        while (primeraVencida != null && !(primeraVencida.estaVencida())) 
        	primeraVencida = primeraVencida.darSiguienteTarea();
        
        if (primeraVencida == null) return null;
        
        Tarea nuevaLista = new Tarea(primeraVencida.darNombre(), primeraVencida.darDescripcion(), primeraVencida.darFechaInicio(), primeraVencida.darFechaFin());
        Tarea cabezaNuevaLista = nuevaLista; 
        
        Tarea recorredora = primeraVencida.darSiguienteTarea();
        while (recorredora != null) {
        	if (recorredora.estaVencida()) {
        		Tarea tempTarea = new Tarea(recorredora.darNombre(), recorredora.darDescripcion(), recorredora.darFechaInicio(), recorredora.darFechaFin());
        		nuevaLista.cambiarSiguienteTarea(tempTarea);
        		nuevaLista = nuevaLista.darSiguienteTarea();
        	}
        	recorredora = recorredora.darSiguienteTarea();
        }
        return cabezaNuevaLista;
    }
    /**
     * Devuelve el número de tareas de la categoria
     */
    public int darNumeroTareas() {
    	int respuesta = 0;
    	for (Tarea recorredora = primeraTarea; recorredora != null; recorredora = recorredora.darSiguienteTarea())
    		respuesta++;
    	return respuesta;
    }
    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Método que verifica la invariante de la clase <br>
     * <b>inv: </b> <br>
     * nombreCategoriaP != null <br>
     * nombreCategoriaP != "" <br>
     */
    private void verificarInvariante( )
    {
        assert nombreCategoria != null : "El nombre es null";
        assert !nombreCategoria.equals("") : "El nombre es vacío";
    }

}
