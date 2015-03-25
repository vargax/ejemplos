/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_recetario
 * Autor: Catalina Rodríguez - 01-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.recetario.mundo;

import java.util.ArrayList;

/**
 * Clase que representa un Recetario Cupi2
 * <b>inv: </b> <br>
 * recetas != null <br>
 * No existen recetas con el mismo nombre. <br>
 */
public class Recetario
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Lista con las recetas del recetario
	 */
	private ArrayList<Receta> recetas;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye un nuevo recetario sin recetas
     * <b>post: </b> La lista de recetas es inicializada
     */
    public Recetario( )
    {
    	recetas = new ArrayList();
    	verificarInvariante();
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Retorna la lista de recetas del recetario
     * <b>post: </b> La lista de recetas ha sido inicializada
     * @return Lista de recetas
     */
    public ArrayList darRecetas()
    {
    	return recetas;
    }

   
    
    /**
     * Agrega una nueva receta al recetario
     * @param nombre Nombre del plato. nombre != null y nombre != ""
     * @param foto Ruta de la imagen del plato. foto != null y foto != ""
     * @param dificultad Dificultad de la preparación del plato. dificultad > 0 y dificultad <= 10
     * @param categoria Categoría de la receta. Debe corresponder a algunas de las constantes de Recetario
     * @param tiempoPreparacion Tiempo de preparación del plato. tiempoPreparacion > 0
     * @param ingredientes Lista de los ingredientes de la receta. ingredientes != null
     * @param instrucciones Instrucciones de preparación del plato. instrucciones != null y instrucciones != "" 
     * @return True si la receta es agregada al recetario. False en caso de existir una receta con el mismo nombre.
     */
    public boolean agregarReceta(String nombre, String foto, int dificultad, String categoria, int tiempoPreparacion, String[] ingredientes, String instrucciones)
    {
    	int pos = buscarReceta(nombre);
    	boolean agregada = false;
    	
    	//No existe en el recetario una receta con el nombre dado
    	if( pos == -1)
    	{
    		Receta c = new Receta(nombre, foto, dificultad, categoria, tiempoPreparacion, ingredientes, instrucciones);
    		recetas.add(c);
    		agregada = true;
    		

    	}
    	verificarInvariante();
    	return agregada;
    }
    
    /**
     * Organiza la lista de recetas por nombre usando el algoritmo de burbuja. <br>
     * <b> post: </b>La lista de recetas está ordenada por nombre (orden ascendente)
     */
    public void ordenarPorNombre( )
    {
    	for (int i = recetas.size(); i > 0; i--) 
    		for (int j = 0; j < i-1; j++) {
    			Receta unaReceta = recetas.get(j);
    			Receta otraReceta = recetas.get(j+1);
    			
    			if (unaReceta.compararPorNombre(otraReceta) == 1) {
    				recetas.set(j, otraReceta);
    				recetas.set(j+1, unaReceta);
    			} else if (unaReceta.compararPorNombre(otraReceta)== -1) {
    				recetas.set(j+1, otraReceta);
    				recetas.set(j, unaReceta);
    			}
    		}
    }
    
    /**
     * Organiza la lista de recetas por dificultad usando el algoritmo de burbuja. <br>
     * <b> post: </b>La lista de recetas está ordenada por dificultad (orden ascendente)
     */
    public void ordenarPorDificultad()
    {
    	for (int i = recetas.size(); i > 0; i--) 
    		for (int j = 0; j < i-1; j++) {
    			Receta unaReceta = recetas.get(j);
    			Receta otraReceta = recetas.get(j+1);
    			
    			if (unaReceta.compararPorDificultad(otraReceta) == 1) {
    				recetas.set(j, otraReceta);
    				recetas.set(j+1, unaReceta);
    			} else if (unaReceta.compararPorDificultad(otraReceta)== -1) {
    				recetas.set(j+1, otraReceta);
    				recetas.set(j, unaReceta);
    			}
    		}
    }
    
    /**
     * Organiza la lista de recetas por número de ingredientes usando el algoritmo de selección. <br>
     * <b> post: </b>La lista de recetas está ordenada por número de ingredientes (orden ascendente)
     */
    public void ordenarPorNumeroIngredientes()
    {
    	for (int i = 0; i < recetas.size(); i++) {
    		Receta laMenor = recetas.get(i);
    		int posMenor = i;
    		for (int j = i+1; j < recetas.size(); j++) {
    			if (recetas.get(j).compararPorNumeroIngredientes(laMenor) == -1) {
    				laMenor = recetas.get(j);
    				posMenor = j;
    			}
    		}
    		recetas.set(posMenor, recetas.get(i));
    		recetas.set(i, laMenor);
    	}
    }
    
    /**
     * Organiza la lista de recetas por categoría usando el algoritmo de selección. <br>
     * <b> post: </b>La lista de recetas está ordenada por categoría (orden ascendente)
     */
    public void ordenarPorCategoria()
    {
    	for (int i = 0; i < recetas.size(); i++) {
    		Receta laMenor = recetas.get(i);
    		int posMenor = i;
    		for (int j = i+1; j < recetas.size(); j++) {
    			if (recetas.get(j).compararPorCategoria(laMenor) == 1) {
    				laMenor = recetas.get(j);
    				posMenor = j;
    			}
    		}
    		recetas.set(posMenor, recetas.get(i));
    		recetas.set(i, laMenor);
    	}
    }
    
    /**
     * Organiza la lista de recetas por tiempo de preparación usando el algoritmo de inserción. <br>
     * <b> post: </b>La lista de recetas está ordenada por tiempo de preparación (orden ascendente)
     */
    public void ordenarPorTiempoPreparacion()
    {
    	for (int i = 0; i < recetas.size(); i++)
    		for (int j = i; j > 0 && (recetas.get(j-1).compararPorTiempoPreparacion(recetas.get(j)) == 1); j--) {
    			Receta tempReceta = recetas.get(j);
    			recetas.set(j, recetas.get(j-1));
    			recetas.set(j-1,tempReceta);
    		}
    }
    
    /**
     * Busca una receta utilizando una búsqueda binaria. <br>
     * <b>pre: </b> La lista de recetas se encuentra ordenada por nombre.
     * @param nombre Es el nombre de la receta que se va a buscar - nombre != null
     * @return La posición de la receta con el nombre dado. Si la receta no existe se retorna -1.
     */
    public int buscarBinarioPorNombre(String nombre)
    {
    	ordenarPorNombre();
    	
    	int inicio = 0;
    	int fin = recetas.size()-1;
    	int medio = (inicio + fin)/2;
    	boolean encontre = false;
    	
    	while (inicio <= fin && !encontre) {
    		medio = (inicio + fin)/2;
    		int comparacion = (recetas.get(medio).darNombre()).compareToIgnoreCase(nombre);
    		if (comparacion > 0) fin = medio - 1;
    		else if (comparacion == 0) encontre = true;
    		else inicio = medio + 1;
    	}
    	if (!encontre) return -1;
    	return medio;
    }
    
    /**
     * Busca la receta más difícil del recetario. <br>
     * @return La posición de la receta más difícil de recetario. Si el recetario no tiene recetas se retorna -1.
     *   Si existen varias recetas con la dificultad mas alta, se retorna la primera receta encontrada
     */
    public int buscarRecetaMasDificil()
    {
    	if (recetas == null) return -1;
    	
    	int posMasDificil = 0;
    	int dificultad = recetas.get(posMasDificil).darDificultad();
    	
    	for (int i = 1; i < recetas.size(); i++)
    		if (recetas.get(i).darDificultad() > dificultad){
    			posMasDificil = i;
    			dificultad = recetas.get(i).darDificultad(); 
    		}
    			
    	
    	return posMasDificil;
    }
    
    /**
     * Busca la receta más fácil del recetario. <br>
     * @return La posición de la receta más fácil de recetario. Si el recetario no tiene recetas se retorna -1.
     *   Si existen varias recetas con la dificultad mas baja, se retorna la primera receta encontrada
     */
    public int buscarRecetaMasFacil()
    {
    	if (recetas == null) return -1;
    	
    	int posMasFacil = 0;
    	int dificultad = recetas.get(posMasFacil).darDificultad();
    	
    	for (int i = 1; i < recetas.size(); i++)
    		if (recetas.get(i).darDificultad() < dificultad) {
    			posMasFacil = i;
    			dificultad = recetas.get(posMasFacil).darDificultad();
    		}
    	return posMasFacil;
    }
    
    /**
     * Busca una receta con el nombre que se pasa por parámetro
     * @param nombre Nombre de la receta. nombre != null y nombre != ""
     * @return Retorna la posición donde se encuentra la receta con el nombre dado. 
     * 	Si no se encuentra ninguna receta con ese nombre retorna -1
     */
    public int buscarReceta(String nombre)
    {
    	for (int i = 0; i < recetas.size(); i++)
    		if (recetas.get(i).darNombre().compareToIgnoreCase(nombre) == 0)
    			return i;
    	return -1;
    }
    
    /**
     * Busca las recetas con el ingrediente dado por parámetro <br>
     * @param ingrediente Ingrediente que se desea buscar en la receta. ingrediente != null e ingrediente != ""
     * @return Lista de las recetas con el ingrediente dado.
     */
    public ArrayList buscarRecetasConIngrediente(String ingrediente)
    {
    	ArrayList respuesta = new ArrayList();
    	for (int i = 0; i < recetas.size(); i ++)
    		if (recetas.get(i).tieneIngrediente(ingrediente))
    			respuesta.add(recetas.get(i));
    	return respuesta;
    }
        
    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------
    
    private void verificarInvariante() {
    	
    	assert (recetas != null) : "El arreglo de recetas no ha sido correctamente inicializado.";
    	
    	for (int i = 0; i < recetas.size(); i++) {
    		String nombre1 = ((Receta)(recetas.get(i))).darNombre();
    		for (int j = i+1; j < recetas.size(); j++) {
    			String nombre2 = ((Receta)(recetas.get(j))).darNombre();
    			assert !(nombre1.equals(nombre2)) : "Existen dos recetas con el mismo nombre";
    		}
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


}