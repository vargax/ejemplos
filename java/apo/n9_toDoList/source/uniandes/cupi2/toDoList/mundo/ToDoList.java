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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * Clase principal que representa una lista de tareas organizadas por categorías.
 * <b> inv: </b> <br>
 * rutaArchivo != null && rutaArchivo no es una cadena vacía<br>
 */
public class ToDoList
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Primera categoría en la lista de tareas
     */
    private Categoria primeraCategoria;

    /**
     * Ruta del archivo
     */
    private String rutaArchivo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva lista de tareas
     * @param rutaArchivoP Ruta al archivo serializado con la información rutaArchivoP != null y rutaArchivoP != ""
     * @throws PersistenciaException Si ocurre algún error al restaurar el estado del mundo
     */
    public ToDoList( String rutaArchivoP ) throws PersistenciaException
    {
        rutaArchivo = rutaArchivoP;
        abrir( );
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que recupera el estado de la lista de tareas a partir del archivo serializado. 
     * @throws PersistenciaException Si se presenta algún problema al cargar la información
     */
    public void abrir() throws PersistenciaException
    {
    	File archivo = new File(rutaArchivo);
    	if (archivo.exists()){
	        try {
	        	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
	        	primeraCategoria = (Categoria)ois.readObject();
	        	ois.close();
	        } catch (Exception e) {
	        	primeraCategoria = null;
				throw new PersistenciaException("No fue posible cargar la lista de tareas: \n"+e.getMessage());
			}
    	} else primeraCategoria = null;
    }
    
    /**
     * Retorna la primera categoría
     * @return Primera categoría
     */
    public Categoria darPrimeraCategoria( )
    {
        return primeraCategoria;
    }

    /**
     * Agrega una nueva categoria en orden alfabético. No pueden existir dos categorías con el mismo nombre.
     * @param nombreP Nombre de la categoría. nombreP != null y nombreP != ""
     * @throws ElementoExisteException Si ya existe una categoría con ese nombre
     */
    public void agregarCategoria( String nombreP ) throws ElementoExisteException
    {
    	// Creo la nueva categoria
    	Categoria nuevaCategoria = new Categoria(nombreP);
    	// Si no tengo ninguna categoría, la asigno a la primera y termino
    	if (primeraCategoria == null)  primeraCategoria = nuevaCategoria;
    	// Si sí tengo alguna categoría, debo buscar donde insertar la nueva
    	else {
    		// Empiezo en la primera categoría
    		Categoria tempCategoria = primeraCategoria;
    		// Mientras la categoría sea diferente de NULL y este antes de la que estoy buscando... 
    		while (tempCategoria.darSiguienteCategoria() != null 
    				&& tempCategoria.darSiguienteCategoria().darNombre().compareToIgnoreCase(nombreP) <= 0) {
    			// Avanzo...    			
    			tempCategoria = tempCategoria.darSiguienteCategoria();
    		}
    		// Si resulta ser la misma, lanzo excepción...
    		if (tempCategoria != null && tempCategoria.darNombre().equals(nombreP)) throw new ElementoExisteException("Ya existe una categoría con este nombre");
    		// Si no es la misma, aquí debo agregar la nueva categoría...
    		nuevaCategoria.cambiarAnterior(tempCategoria);
    		nuevaCategoria.cambiarSiguiente(tempCategoria.darSiguienteCategoria());
    		tempCategoria.cambiarSiguiente(nuevaCategoria);
    	}
    }

    /**
     * Busca una categoría con el nombre dado.
     * @param nombre Nombre de la categoría que se está buscando. nombre != null y nombre != ""
     * @return La categoría con el nombre dado si existe, null en caso contrario.
     */
    public Categoria buscarCategoria( String nombre )
    {
    	Categoria tempApuntador = primeraCategoria;
    		
    	if (primeraCategoria == null) return null;
        
        Categoria tempCategoria = primeraCategoria;
        while (tempCategoria != null && tempCategoria.darNombre().compareToIgnoreCase(nombre) < 0) {
        	tempCategoria = tempCategoria.darSiguienteCategoria();
        }

        return tempCategoria != null && tempCategoria.darNombre().equals(nombre) ? tempCategoria : null;
    }

    /**
     * Agrega una nueva Tarea al final de la lista de tareas de la categoría dada.
     * @param nombreCategoria Categoría a la que pertenece la nueva tarea. nombreCategoria != null y nombreCategoria != ""
     * @param nombreTarea Nombre de la nueva tarea. nombreTarea != null y nombreTarea != ""
     * @param descripcionTarea Descripción de la nueva tarea. descripcionTarea != null && descripcionTarea != ""
     * @param fechaInicioTarea Fecha de inicio de la nueva tarea. fechaInicioTarea != null
     * @param fechaFinTarea Fecha de finalización de la nueva tarea. Puede ser null. Si fechaFinTarea != null entonces fechaFinTarea > fechaInicioTarea.
     * @throws ElementoExisteException Se lanza si ya existe una tarea con ese nombre
     * @throws ElementoNoExisteException Se lanza si no existe ninguna categoría con ese nombre
     */
    public void agregarTarea( String nombreCategoria, String nombreTarea, String descripcionTarea, Date fechaInicioTarea, Date fechaFinTarea ) throws ElementoExisteException, ElementoNoExisteException
    {
    	Categoria tempCategoria = buscarCategoria(nombreCategoria);
    	if (tempCategoria == null) throw new ElementoNoExisteException("La categoría "+nombreCategoria+" no existe");
    	tempCategoria.agregarTarea(nombreTarea, descripcionTarea, fechaInicioTarea, fechaFinTarea);
    }

    /**
     * Edita una tarea con la información dada por parámetro. El nombre de la tarea no cambia. <br> 
     * La información que se puede editar es su descripción, su fecha de inicio y su fecha de finalización.
     * @param nombreCategoria Nombre de la categoría a la que pertenece la tarea que se quiere editar. nombreCategoria != null y nombreCategoria != ""
     * @param nombreTarea Nombre de la tarea que se quiere editar. nombreTarea != null y nombreTarea != ""
     * @param descripcionTarea Nueva descripción de la tarea. descripcionTarea != null y descripcionTarea != ""
     * @param fechaInicio Nueva fecha de inicio de la tarea. fechaInicio != null
     * @param fechaFin Nueva fecha de finalización de la tarea. Puede ser null. Si fechaFin != null entonces fechaFin > fechaInicio.
     * @throws ElementoNoExisteException Se lanza si:<br>
     *         - No existe una categoría con el nombre dado. - No existe una tarea con el nombre dado.
     */
    public void editarTarea( String nombreCategoria, String nombreTarea, String descripcionTarea, Date fechaInicio, Date fechaFin ) throws ElementoNoExisteException
    {
    	Categoria tempCategoria = buscarCategoria(nombreCategoria);
    	if (tempCategoria == null) throw new ElementoNoExisteException("La categoría "+nombreCategoria+" no existe");
    	tempCategoria.editarTarea(nombreTarea, descripcionTarea, fechaInicio, fechaFin);
    }

    /**
     * Termina una tarea dado su nombre y la categoría a la que pertenece. 
     * @param nombreCategoria Nombre de la categoría a la que pertenece. nombreCategoria != null y nombreCategoria != ""
     * @param nombreTarea Nombre de la tarea que se quiere terminar. nombreTarea != null y nombreTarea != ""
     * @throws ElementoNoExisteException Se lanza si:<br>
     *         - No existe una categoría con el nombre dado. - No existe una tarea con el nombre dado.
     */
    public void terminarTarea( String nombreCategoria, String nombreTarea ) throws ElementoNoExisteException
    {
    	Categoria tempCategoria = buscarCategoria(nombreCategoria);
    	if (tempCategoria == null) throw new ElementoNoExisteException("La categoría "+nombreCategoria+" no existe");
    	tempCategoria.terminarTarea(nombreTarea);
    }

    /**
     * Retorna una nueva lista encadenada que contiene las tareas pendientes. Se considera una tarea como pendiente si no ha sido terminada y aún no ha pasado la fecha de
     * finalización. Cada tarea de la lista que se retorna es una copia de tarea respectiva para evitar problemas en las referencias (siguienteTarea) de la lista de tareas
     * original.
     * @return Lista encadenada con las tareas pendientes. Si no hay ninguna se retorna null.
     */
    public Tarea darTareasPendientes( )
    {	// Si no tengo ninguna categoría devuelvo null...
    	if (primeraCategoria == null) return null;
    	// Si sí tengo categorias, empiezo por la primera...
    	Categoria tempCategoria =  primeraCategoria;
    	// creo una nueva lista de tareas...
    	Tarea nuevaLista = null;
    	// y busco la primera categoría con tareas pendientes...
    	while (tempCategoria != null && tempCategoria.darTareasPendientes() == null) tempCategoria = tempCategoria.darSiguienteCategoria();
    	// depronto no encuentro ninguna, entonces devuelvo null...
    	if (tempCategoria == null) return null;
    	// si encuentro alguna, vuelvo sus tareas pendientes las primeras de la nueva lista...
    	nuevaLista = tempCategoria.darTareasPendientes();
    	// guardo su cabeza...
    	Tarea cabezaNuevaLista = nuevaLista;
    	// y paso a la siguiente categoria...
    	tempCategoria = tempCategoria.darSiguienteCategoria();
    	// Ahora debo recorrer el resto de las categorias...
    	while (tempCategoria != null) {
    		// Primero debo encontrar el final de la nueva lista...
    		while(nuevaLista.darSiguienteTarea() != null) nuevaLista = nuevaLista.darSiguienteTarea();
    		// Agrego al final las tareas pendientes las de la categoría actual...
    		nuevaLista.cambiarSiguienteTarea(tempCategoria.darTareasPendientes());
    		// y avanzo en las categorias...
    		tempCategoria = tempCategoria.darSiguienteCategoria();
    	}
    	// devuelvo la cabeza de la nueva lista...
    	return cabezaNuevaLista;
    }

    /**
     * Retorna una nueva lista encadenada que contiene las tareas terminadas. 
     * Cada tarea de la lista que se retorna es una copia de tarea respectiva para evitar problemas en <br>
     * la lista de tareas original y deben estar ordenadas por categorías.
     * @return Lista encadenada con las tareas terminadas. Si no hay ninguna se retorna null.
     */
    public Tarea darTareasTerminadas( ){
    	if (primeraCategoria == null) return null;
    	Categoria tempCategoria =  primeraCategoria;
    	Tarea nuevaLista = null;
    	while (tempCategoria != null && tempCategoria.darTareasTerminadas() == null) tempCategoria = tempCategoria.darSiguienteCategoria();
    	if (tempCategoria == null) return null;
    	nuevaLista = tempCategoria.darTareasTerminadas();
    	Tarea cabezaNuevaLista = nuevaLista;
    	tempCategoria = tempCategoria.darSiguienteCategoria();
    	while (tempCategoria != null) {
    		while(nuevaLista.darSiguienteTarea() != null) nuevaLista = nuevaLista.darSiguienteTarea();
    		nuevaLista.cambiarSiguienteTarea(tempCategoria.darTareasTerminadas());
    		tempCategoria = tempCategoria.darSiguienteCategoria();
    	}
    	return cabezaNuevaLista;
    }

    /**
     * Retorna una nueva lista encadenada que contiene las tareas vencidas. Se considera una tarea como vencida si no ha sido terminada y su fecha de finalización es anterior
     * a la fecha actual. Cada tarea de la lista que se retorna es una copia de tarea respectiva para evitar problemas en la lista de tareas original y deben 
     * estar ordenadas por categorías.
     * @return Lista encadenada con las tareas terminadas. Si no hay ninguna se retorna null.
     */
    public Tarea darTareasVencidas() {	
    	if (primeraCategoria == null) return null;
    	Categoria tempCategoria =  primeraCategoria;
    	Tarea nuevaLista = null;
    	while (tempCategoria != null && tempCategoria.darTareasVencidas() == null) tempCategoria = tempCategoria.darSiguienteCategoria();
    	if (tempCategoria == null) return null;
    	nuevaLista = tempCategoria.darTareasVencidas();
    	Tarea cabezaNuevaLista = nuevaLista;
    	tempCategoria = tempCategoria.darSiguienteCategoria();
    	while (tempCategoria != null) {
    		while(nuevaLista.darSiguienteTarea() != null) nuevaLista = nuevaLista.darSiguienteTarea();
    		nuevaLista.cambiarSiguienteTarea(tempCategoria.darTareasVencidas());
    		tempCategoria = tempCategoria.darSiguienteCategoria();
    	}
    	return cabezaNuevaLista;
    }

    /**
     * Elimina las tareas terminadas
     * @return Retorna el número de tareas eliminadas
     */
    public int eliminarTareasTerminadas() {
    	if (primeraCategoria == null) return 0;
    	Categoria tempCategoria = primeraCategoria;
    	int contador = 0;
    	while(tempCategoria != null) {
    		contador += tempCategoria.eliminarTareasTerminadas();
    		tempCategoria = tempCategoria.darSiguienteCategoria();
    	}
    	return contador;
    }

   

    /**
     * Método que guarda el estado del mundo en un archivo serializado. 
     * @throws PersistenciaException Se lanza excepción si ocurre algún problema al guardar.
     */
    public void guardar( ) throws PersistenciaException
    {
    	try {
    		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo));
    		oos.writeObject(primeraCategoria);
    		oos.close();
    	} catch (IOException e) {
    		throw new PersistenciaException("No fue posible persistir la lista de tareas: \n"+e.getMessage());
    	}
    }
   /**
    * Método que permite conocer el nombre de las categorias que tengan al menos una tarea compartida entre si.
    */
    public String darNombresCategoríasConTareasCompartidas() {
    	if (primeraCategoria == null) return "";
    	
    	String respuesta = "";
    	
    	for (Categoria recorredoraPrincipal = primeraCategoria; recorredoraPrincipal != null; 
    		recorredoraPrincipal = recorredoraPrincipal.darSiguienteCategoria())
    		for(Categoria recorredoraSecundaria= recorredoraPrincipal.darSiguienteCategoria(); 
    			recorredoraSecundaria != null; recorredoraSecundaria = recorredoraSecundaria.darSiguienteCategoria()) {
    			boolean encontreAlguna = false;
    			for (Tarea listaTareasPrincipal = recorredoraPrincipal.darPrimeraTarea(); 
    				!encontreAlguna && listaTareasPrincipal != null; listaTareasPrincipal = listaTareasPrincipal.darSiguienteTarea())
    				for (Tarea listaTareasSecundaria = recorredoraSecundaria.darPrimeraTarea(); 
    					!encontreAlguna && listaTareasSecundaria != null; listaTareasSecundaria = listaTareasSecundaria.darSiguienteTarea())
    						encontreAlguna = listaTareasPrincipal.darNombre().equals(listaTareasSecundaria.darNombre()) 
    										&& listaTareasPrincipal.darFechaInicio().equals(listaTareasSecundaria.darFechaInicio());
    			respuesta = encontreAlguna ? respuesta += recorredoraPrincipal.darNombre()+","+recorredoraSecundaria.darNombre(): respuesta;
    		}
    	return respuesta;
    }
    /**
    * Método que elimina las categorías cuyo número de tareas sea mayor al valor que
    * ingresa como primer parámetro y menor al que ingresa como segundo parámetro
    * @param rangoInicial Valor mínimo del rango - rangoInicial >= 0
    * @param rangoFinal Valor máximo del rango - rangoFinal >= 0
    * && rangoFinal >= rangoInicial
    * @return Número de categorías eliminadas
    */
    public int eliminarCategoriasPorNumeroDeTareas( int rangoInicial, int rangoFinal ) {
    	int respuesta = 0;
    	for (Categoria recorredora = primeraCategoria; recorredora != null; recorredora = recorredora.darSiguienteCategoria())
    		if (rangoInicial < recorredora.darNumeroTareas() && recorredora.darNumeroTareas() < rangoFinal) {
    			respuesta++;
    			recorredora.darAnteriorCategoria().cambiarSiguiente(recorredora.darSiguienteCategoria());
    		}
    	return respuesta;
    }
    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Método que verifica la invariante de la clase <br>
     * <b>inv: </b> <br>
     * rutaArchivo != null && rutaArchivo no es una cadena vacía<br>
     */
    private void verificarInvariante( )
    {
        assert rutaArchivo != null : "La ruta del archivo es null";
        assert !rutaArchivo.equals("") : "La ruta del archivo es vacía";
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * 
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * 
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}