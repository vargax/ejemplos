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

package uniandes.cupi2.recetario.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.recetario.mundo.Receta;
import uniandes.cupi2.recetario.mundo.Recetario;


/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazRecetario extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante con la ubicación del archivo con los datos de las recetas de la aplicación
     */
    private final static String RUTA_ARCHIVO = "./data/recetas.properties";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private Recetario recetario;

    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;
    
    /**
     * Panel con la imagen del encabezado
     */
    private PanelImagen panelImagen;
    
    /**
     * Panel con la lista de recetas del recetario
     */
    private PanelListaRecetas panelListaRecetas;
    
    /**
     * Panel con la información detalla de una receta
     */
    private PanelInformacionReceta panelInformacionReceta;
    
    /**
     * Panel con las operaciones de ordenamiento y búsqueda
     */
    private PanelOperaciones panelOperaciones;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye la interfaz e inicializa todos sus componentes.
     */
    public InterfazRecetario()
    {
        // Crea la clase principal
        recetario = new Recetario();
        cargarRecetas();
        
        // Construye la forma
        setLayout( new BorderLayout( ) );
        setSize( 880, 530 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle("Cupi-Recetario");
        
        //Creación de los paneles aquí
        panelImagen = new PanelImagen();
        add( panelImagen, BorderLayout.NORTH );
        
        panelListaRecetas = new PanelListaRecetas(this);
        add( panelListaRecetas, BorderLayout.WEST );
        
        panelInformacionReceta = new PanelInformacionReceta();
        add( panelInformacionReceta, BorderLayout.CENTER );
        
        JPanel aux = new JPanel();
        aux.setLayout(new GridLayout(2, 1));
        
        panelOperaciones = new PanelOperaciones(this);
        aux.add(panelOperaciones);
        
        panelExtension = new PanelExtension( this );
        aux.add(panelExtension);
        
        add( aux, BorderLayout.SOUTH );
        
        //Centrar la ventana
        setLocationRelativeTo(null);
        
        actualizarLista();
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Carga las recetas iniciales del recetario a partir de un archivo de propiedades.
     */
    private void cargarRecetas()
    {
        try
        {
            FileInputStream fis = new FileInputStream( new File( RUTA_ARCHIVO ) );
            Properties propiedades = new Properties( );
            propiedades.load( fis );

            // Cargar las recetas
            String aux = propiedades.getProperty("total.recetas");
            int numRecetas = Integer.parseInt(aux);
            
            String dato;
            String nombre;
            String foto;
            int dificultad;
            String categoria;
            int tiempoPreparacion;
            String[] ingredientes;
            String instrucciones;
                        
            for( int i = 1; i < numRecetas+1; i++ )
            {
                // Carga una receta
                dato = "receta" + i + ".nombre";
                nombre = propiedades.getProperty( dato );
                
                dato = "receta" + i + ".foto";
                foto = propiedades.getProperty( dato );
                
                dato = "receta" + i + ".dificultad";
                dificultad = Integer.parseInt(propiedades.getProperty( dato ));
                
                dato = "receta" + i + ".categoria";
                categoria = propiedades.getProperty( dato );
                
                dato = "receta" + i + ".tiempoPreparacion";
                tiempoPreparacion =  Integer.parseInt(propiedades.getProperty( dato ));
                
                dato = "receta" + i + ".ingredientes";
                ingredientes = propiedades.getProperty( dato ).split(",");
                
                dato = "receta" + i + ".instrucciones";
                instrucciones = propiedades.getProperty( dato );

                // Sólo se agrega la receta si los datos son correctos
                if( nombre != null && !nombre.isEmpty() && foto != null && !foto.isEmpty() && categoria != null && 
                		!categoria.isEmpty() && instrucciones != null && !instrucciones.isEmpty() && dificultad > 0
                		&& dificultad < 10 && tiempoPreparacion > 0 && ingredientes != null )
                    recetario.agregarReceta(nombre, foto, dificultad, categoria, tiempoPreparacion, ingredientes, instrucciones);
                fis.close( );
            }
        }
        catch( FileNotFoundException e )
        {
            e.printStackTrace( );
        }
        catch( IOException e )
        {

            e.printStackTrace( );
        }
    }
    
    /**
     * Actualiza la lista de recetas mostrada.
     */
    private void actualizarLista( )
    {
        panelListaRecetas.refrescarLista( recetario.darRecetas() );
    }
    
    /**
     * Actualiza la información de la receta seleccionada
     * @param receta Receta que ha sido seleccionada y cuya información debe ser mostrada. receta != null
     */
    public void actualizarInfoReceta(Receta receta)
    {
    	panelInformacionReceta.actualizarInformacion(receta);
    }
    
    /**
     * Ordena la lista de recetas por su nombre
     */
	public void ordenarPorNombre() 
	{
		recetario.ordenarPorNombre();
		actualizarLista();
	}
	
    /**
     * Ordena la lista de recetas por su categoria
     */
	public void ordenarPorCategoria() 
	{
		recetario.ordenarPorCategoria();
		actualizarLista();
	}
    
    /**
     * Ordena la lista de recetas por su dificultad
     */
	public void ordenarPorDificultad() 
	{
		recetario.ordenarPorDificultad();
		actualizarLista();
	}
	
    /**
     * Ordena la lista de recetas por su número de ingredientes
     */
	public void ordenarPorNumeroIngredientes() 
	{
		recetario.ordenarPorNumeroIngredientes();
		actualizarLista();
	}
	
    /**
     * Ordena la lista de recetas por su tiempo de preparación
     */
	public void ordenarPorTiempoPreparacion() 
	{
		recetario.ordenarPorTiempoPreparacion();
		actualizarLista();
	}
	
	/**
	 * Busca la receta con el nombre dado por parámetro
	 * @param nombre Nombre de la receta a buscar. nombre != null y nombre != ""
	 */
	public void buscarPorNombre(String nombre)
	{
		recetario.ordenarPorNombre();
		actualizarLista();
		int receta = recetario.buscarBinarioPorNombre(nombre);
		if(receta > -1)
		{
			panelListaRecetas.seleccionar(receta);			
		}
		else
		{
			JOptionPane.showMessageDialog(this, "No se encontró una receta con el nombre dado", "Buscar Receta", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Busca las recetas con el ingrediente dado por parámetro
	 * @param ingrediente Ingrediente de la receta. ingrediente != null y ingrediente != ""
	 */
	public void buscarPorIngrediente(String ingrediente) 
	{
		ArrayList recetas = recetario.buscarRecetasConIngrediente(ingrediente);
		panelListaRecetas.refrescarLista( recetas );
	}
	
	/**
	 * Agrega una nueva receta al recetario con los valores dados
	 * @param nombre Nombre del plato. nombre != null y nombre != ""
     * @param foto Ruta de la imagen del plato. foto != null y foto != ""
     * @param dificultad Dificultad de la preparación del plato. dificultad > 0 y dificultad <= 10
     * @param categoria Categoría de la receta. Debe corresponder a algunas de las constantes de Recetario
     * @param tiempoPreparacion Tiempo de preparación del plato. tiempoPreparacion > 0
     * @param ingredientes Lista de los ingredientes de la receta. ingredientes != null
     * @param instrucciones Instrucciones de preparación del plato. instrucciones != null y instrucciones != "" 
     */
	public void agregarReceta(String nombre, String foto, int dificultad, String categoria, int tiempoPreparacion, String[] ingredientes, String instrucciones)
	{
		boolean agregada = recetario.agregarReceta(nombre, foto, dificultad, categoria, tiempoPreparacion, ingredientes, instrucciones);
		if(agregada)
		{
			actualizarLista();
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Ya existe en el recetario una receta con el nombre dado", "Agregar Receta", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Busca la receta más fácil del recetario
	 */
	public void buscarRecetaMasFacil()
	{
		int indice = recetario.buscarRecetaMasFacil();
		if(indice > -1)
		{
			actualizarLista();
			panelListaRecetas.seleccionar(indice);
		}
	}
	
	/**
	 * Busca la recete más difícil del recetario
	 */
	public void buscarRecetaMasDificil()
	{
		int indice = recetario.buscarRecetaMasDificil();
		if(indice > -1)
		{
			actualizarLista();
			panelListaRecetas.seleccionar(indice);
		}
	}
	
    //-----------------------------------------------------------------
    // Puntos de Extensión
    //-----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = recetario.metodo1();
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = recetario.metodo2();
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    //-----------------------------------------------------------------
    // Main
    //-----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {

        InterfazRecetario interfaz = new InterfazRecetario();
        interfaz.setVisible( true );
    }
}