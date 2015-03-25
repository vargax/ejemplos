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

package uniandes.cupi2.toDoList.interfaz;

import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.toDoList.mundo.ElementoExisteException;
import uniandes.cupi2.toDoList.mundo.ElementoNoExisteException;
import uniandes.cupi2.toDoList.mundo.PersistenciaException;
import uniandes.cupi2.toDoList.mundo.Categoria;
import uniandes.cupi2.toDoList.mundo.Tarea;
import uniandes.cupi2.toDoList.mundo.ToDoList;

/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazToDoList extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Ruta del archivo en donde se va a guardar la lista de tareas
     */
    public static final String RUTA_ARCHIVO = "data/toDoList.data";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private ToDoList toDoList;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las extensiones
     */
    private PanelOperaciones panelOperaciones;

    /**
     * Panel con la imagen del encabezado
     */
    private PanelImagen panelImagen;

    /**
     * Panel que muestra las categorías y permite navegar en ellas
     */
    private PanelCategoria panelCategoria;

    /**
     * Panel que muestra las tareas
     */
    private PanelTareas panelTareas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva ventana de la aplicación <br>
     * <b>post: </b> Se construye una ventana con la información de la última sesión cargada
     */
    public InterfazToDoList( )
    {
        // Crea la clase principal
        try
        {
            toDoList = new ToDoList( RUTA_ARCHIVO );
        }
        catch( PersistenciaException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }

        // Construye la forma
        setLayout( new BorderLayout( ) );
        setSize( 570, 670 );
        setResizable( false );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setTitle( "Cupi-ToDo List" );

        // Creación de los paneles aquí
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        JPanel aux = new JPanel( );
        aux.setLayout( new BorderLayout( ) );
        panelCategoria = new PanelCategoria( this );
        aux.add( panelCategoria, BorderLayout.NORTH );

        panelTareas = new PanelTareas( this );
        aux.add( panelTareas, BorderLayout.CENTER );

        add( aux );

        panelOperaciones = new PanelOperaciones( this );
        add( panelOperaciones, BorderLayout.SOUTH );

        // Centrar la ventana
        setLocationRelativeTo( null );

        Categoria p = toDoList.darPrimeraCategoria( );
        if( p != null )
        {
            actualizar( p );
        }

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Muesta la categoría anterior. En caso que no exista se muestra un mensaje que informe al usuario
     */
    public void anteriorCategoria( )
    {
        // Busca la categoría actual
        String nombreCategoria = panelCategoria.darNombreCategoria( );
        Categoria actual = toDoList.buscarCategoria( nombreCategoria );

        if( actual != null )
        {
            // Revisa si tiene una categoría anterior
            Categoria anterior = actual.darAnteriorCategoria( );
            if( anterior != null )
            {
                actualizar( anterior );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "No hay una categoría anterior", "Anterior Categoría", JOptionPane.INFORMATION_MESSAGE );
            }
        }
        else
        {
            JOptionPane.showMessageDialog( this, "No hay categorías", "Anterior Categoría", JOptionPane.INFORMATION_MESSAGE );
        }
    }

    /**
     * Agrega una nueva categoría. Se pide el nombre. Se agrega la nueva categoría. Se actualiza la interfaz. Si se produce un error agregando la categoría se muestra un
     * mensaje que informe al usuario
     */
    public void agregarCategoria( )
    {
        String nombre = JOptionPane.showInputDialog( "Escriba el nombre de la categoría" );
        if( nombre != null && !nombre.equals( "" ) )
        {
            try
            {
                toDoList.agregarCategoria( nombre );
                // Muestra la nueva categoría
                Categoria actual = toDoList.buscarCategoria( nombre );
                actualizar( actual );
            }
            catch( ElementoExisteException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Agregando Categoría", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Muesta la categoría siguiente. En caso que no exista se muestra un mensaje que informe al usuario
     */
    public void siguienteCategoria( )
    {
        // Busca la categoría actual
        String nombreCategoria = panelCategoria.darNombreCategoria( );
        Categoria actual = toDoList.buscarCategoria( nombreCategoria );
        if( actual != null )
        {
            // Revisa si tiene una categoría siguiente
            Categoria siguiente = actual.darSiguienteCategoria( );
            if( siguiente != null )
            {
                actualizar( siguiente );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "No hay categoría siguiente", "Siguiente Categoría", JOptionPane.INFORMATION_MESSAGE );
            }
        }
    }

    /**
     * Muestra el diálogo para agregar una nueva tarea en la categoría actual. Si no existe ninguna categoría se muestra un mensaje de error. Si se produce un error se muestra
     * un mensaje que informe al usuario
     */
    public void mostrarDialogoAgregarTarea( )
    {
        String nombreCategoria = panelCategoria.darNombreCategoria( );
        if( nombreCategoria.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "Primero debe agregar una categoría", "Agregar Tarea", JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            DialogoAgregarTarea dialogo = new DialogoAgregarTarea(this);
            dialogo.setLocationRelativeTo(this);
            dialogo.setVisible(true);
        }
    }

    /**
     * Agrega una nueva tarea en la lista de tareas. Se deben verificar los campos obligatorios. Se agrega la tarea. Se actualiza la interfaz.
     * 
     * @param dialogo Diálogo que contiene la información de la nueva tarea
     * @param nombreTarea Nombre de la nueva tarea. nombreTarea != null
     * @param descripcionTarea Descripción de la nueva tarea. descripcionTarea != null
     * @param fechaInicio Fecha de inicio de la nueva tarea. fechaInicio != null
     * @param fechaFin Fecha de finalización de la nueva tarea. Puede ser null
     */
    public void agregarTarea( DialogoAgregarTarea dialogo, String nombreTarea, String descripcionTarea, Date fechaInicio, Date fechaFin )
    {
        String nombreCategoria = panelCategoria.darNombreCategoria( );
        if( nombreCategoria.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "Primero debe agregar una categoría", "Agregar Tarea", JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            try
            {
                boolean agregar = true;
                // Verifica que tenga un nombre
                if( nombreTarea.equals( "" ) )
                {
                    JOptionPane.showMessageDialog( this, "Debe escribir un nombre", "Error Agregar Tarea", JOptionPane.ERROR_MESSAGE );
                    agregar = false;
                }
                // Luego verifica que tenga una descripción
                if( agregar && descripcionTarea.equals( "" ) )
                {
                    JOptionPane.showMessageDialog( this, "Debe escribir una descripción", "Error Agregar Tarea", JOptionPane.ERROR_MESSAGE );
                    agregar = false;
                }

                // Luego verifica que la fecha de finalización sea posterior a
                // la fecha de inicio
                if( agregar && fechaFin != null )
                {
                    if( fechaFin.compareTo( fechaInicio ) <= 0 )
                    {
                        agregar = false;
                        JOptionPane.showMessageDialog( this, "La fecha de finalización debe ser\nposterior a la fecha de inicio", "Error Agregar Tarea", JOptionPane.ERROR_MESSAGE );
                    }
                }

                if( agregar )
                {
                    toDoList.agregarTarea( panelCategoria.darNombreCategoria( ), nombreTarea, descripcionTarea, fechaInicio, fechaFin );
                    actualizar( toDoList.buscarCategoria( nombreCategoria ) );
                }
            }
            catch( ElementoExisteException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Agregar Tarea", JOptionPane.ERROR_MESSAGE );
            }
            catch( ElementoNoExisteException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Agregar Tarea", JOptionPane.ERROR_MESSAGE );
            }
        }
        dialogo.dispose( );
    }

    /**
     * Muestra el diálogo para editar la tarea seleccionada Si se produce un error se muestra un mensaje que informe al usuario
     */
    public void mostrarDialogoEditarTarea( )
    {
        String nombreCategoria = panelCategoria.darNombreCategoria( );
        if( nombreCategoria.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "Primero debe agregar una categoría", "Editar Tarea", JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            String nombreTarea = panelTareas.darNombreTareaSeleccionada( );
            if( nombreTarea == null )
            {
                JOptionPane.showMessageDialog( this, "Debe seleccionar una tarea", "Editar Tarea", JOptionPane.INFORMATION_MESSAGE );
            }
            else
            {
                DialogoEditarTarea d = new DialogoEditarTarea( this );
                Categoria p = toDoList.buscarCategoria( nombreCategoria );
                Tarea t = p.buscarTarea( nombreTarea );
                d.actualizar( t.darNombre( ), t.darDescripcion( ), t.darFechaInicio( ), t.darFechaFin( ) );
                d.setLocationRelativeTo( this );
                d.setVisible( true );
            }
        }
    }

    /**
     * Edita la tarea seleccionada en la lista de tareas. Se valida que si la tarea tiene fecha de finalización, ésta no sea anterior a la fecha de inicio
     * 
     * @param dialogo Diálogo que contiene la información de la tarea
     * @param nombreTarea Nombre de la tarea. nombreTarea != null
     * @param descripcionTarea Descripción de la tarea. descripcionTarea != null
     * @param fechaInicio Fecha de inicio de la tarea. fechaInicio != null
     * @param fechaFin Fecha de finalización de la tarea. Puede ser null
     */
    public void editarTarea( DialogoEditarTarea dialogo, String nombreTarea, String descripcionTarea, Date fechaInicio, Date fechaFin )
    {
        String nombreCategoria = panelCategoria.darNombreCategoria( );
        if( nombreCategoria.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "Primero debe agregar una categoría", "Editar Tarea", JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            try
            {
                boolean editar = true;
                if( fechaFin != null )
                {
                    if( fechaFin.compareTo( fechaInicio ) <= 0 )
                    {
                        editar = false;
                        JOptionPane.showMessageDialog( this, "La fecha de finalización debe ser\nposterior a la fecha de inicio", "Editar Tarea", JOptionPane.ERROR_MESSAGE );
                    }
                }

                if( editar )
                {
                    toDoList.editarTarea( panelCategoria.darNombreCategoria( ), nombreTarea, descripcionTarea, fechaInicio, fechaFin );
                    actualizar( toDoList.buscarCategoria( nombreCategoria ) );
                }

            }
            catch( ElementoNoExisteException e )
            {
                e.printStackTrace( );
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Editar Tarea", JOptionPane.ERROR_MESSAGE );
            }
        }
        dialogo.dispose( );
    }

    /**
     * Termina la tarea seleccionada en la lista de tareas. Se verifica que la tarea actual exista. Se cambia el estado de la tarea a Terminada. Se actualiza la interfaz. Si
     * se produce un error se muestra un mensaje que informe al usuario.
     */
    public void terminarTarea( )
    {
        String nombreCategoria = panelCategoria.darNombreCategoria( );
        if( nombreCategoria.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "Primero debe agregar una categoría", "Terminar Tarea", JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            String nombreTarea = panelTareas.darNombreTareaSeleccionada( );
            if( nombreTarea != null )
            {
                try
                {
                    toDoList.terminarTarea( nombreCategoria, nombreTarea );
                    actualizar( toDoList.buscarCategoria( nombreCategoria ) );
                }
                catch( ElementoNoExisteException e )
                {
                    JOptionPane.showMessageDialog( this, e.getMessage( ), "Terminar Tarea", JOptionPane.ERROR_MESSAGE );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Primero debe agregar una tarea", "Terminar Tarea", JOptionPane.INFORMATION_MESSAGE );
            }
        }
    }

    /**
     * Muestra un diálogo con las tareas pendientes en la lista de tareas. Si no hay tareas pendientes se muestra un mensaje que indique "No hay tareas pendientes"
     */
    public void verTareasPendientes( )
    {
        Tarea primera = toDoList.darTareasPendientes( );
        String mensaje = "";
        if( primera == null )
        {
            mensaje = "No hay tareas pendientes";
        }
        else
        {
            Tarea actual = primera;
            while( actual != null )
            {
                mensaje += actual.darNombre( ) + "\n";
                actual = actual.darSiguienteTarea( );
            }
        }
        JOptionPane.showMessageDialog( this, mensaje, "Tareas Pendientes", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra un diálogo con las tareas terminadas en la lista de tareas. Si no hay tareas terminadas se muestra un mensaje que indique "No hay tareas terminadas"
     */
    public void verTareasTerminadas( )
    {
        Tarea primera = toDoList.darTareasTerminadas( );
        String mensaje = "";
        if( primera == null )
        {
            mensaje = "No hay tareas terminadas";
        }
        else
        {
            Tarea actual = primera;
            while( actual != null )
            {
                mensaje += actual.darNombre( ) + "\n";
                actual = actual.darSiguienteTarea( );
            }
        }
        JOptionPane.showMessageDialog( this, mensaje, "Tareas Terminadas", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra un diálogo con las tareas vencidas en la lista de tareas. Si no hay tareas vencidas se muestra un mensaje que indique "No hay tareas vencidas"
     */
    public void verTareasVencidas( )
    {
        Tarea primera = toDoList.darTareasVencidas( );
        String mensaje = "";
        if( primera == null )
        {
            mensaje = "No hay tareas vencidas";
        }
        else
        {
            Tarea actual = primera;
            while( actual != null )
            {
                mensaje += actual.darNombre( ) + "\n";
                actual = actual.darSiguienteTarea( );
            }
        }
        JOptionPane.showMessageDialog( this, mensaje, "Tareas Vencidas", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Elimina todas las tareas terminadas en la lista de tareas. Se actualiza la interfaz gráfica. Muestra un mensaje con el número de tareas eliminadas.
     */
    public void eliminarTareasTerminadas( )
    {
        int num = toDoList.eliminarTareasTerminadas( );

        String nombreCategoria = panelCategoria.darNombreCategoria( );

        actualizar( toDoList.buscarCategoria( nombreCategoria ) );
        JOptionPane.showMessageDialog( this, "Se han eliminado " + num + " las tareas terminadas", "Tareas Terminadas", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Actualiza los paneles que muestran la información de la lista de tareas
     * @param actual
     */
    public void actualizar( Categoria actual )
    {
        if( actual != null )
        {
            panelCategoria.actualizar( actual.darNombre( ) );
            panelTareas.actualizarLista( actual.darPrimeraTarea( ) );
        }
        else
        {
            panelCategoria.limpiar( );
            panelTareas.limpiarInformacion( );
        }
    }

    /**
     * Método que se llama cuando se cierra la ventana principal de la aplicación. Antes de cerrar se guarda el estado de la lista de tareas. Si se produce un error se muestra
     * un mensaje que informe al usuario.
     */
    public void dispose( )
    {
        try
        {
        	
            toDoList.guardar( );
            super.dispose( );
        }
        catch( Exception e )
        {
            setVisible( true );
            int respuesta = JOptionPane.showConfirmDialog( this, "Problemas salvando la información de la lista de tareas:\n" + e.getMessage( ) + "\n¿Quiere cerrar el programa sin salvar?", "Error", JOptionPane.YES_NO_OPTION );
            if( respuesta == JOptionPane.YES_OPTION )
            {
                super.dispose( );
            }
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = toDoList.darNombresCategoríasConTareasCompartidas( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
    	
    	DialogoOpcion2 dialogo = new DialogoOpcion2(this);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }
    public void eliminarCategorias(String limInferior, String limSuperior){
    	int limiteInferior = Integer.parseInt(limInferior);
    	int limiteSuperior = Integer.parseInt(limSuperior);
    	
    	if (limiteInferior < 0 || limiteSuperior < limiteInferior)
    		JOptionPane.showMessageDialog(this, "Los límites ingresados no son validos", "Eliminar Categorias", JOptionPane.ERROR_MESSAGE);
    	else {
    		int respuesta = toDoList.eliminarCategoriasPorNumeroDeTareas(limiteInferior, limiteSuperior);
    		JOptionPane.showMessageDialog(this,"Se han eliminado "+respuesta+" categorias", "Eliminar Categorias", JOptionPane.INFORMATION_MESSAGE);
    	}
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * 
     * @param args
     */
    public static void main( String[] args )
    {

        InterfazToDoList interfaz = new InterfazToDoList( );
        interfaz.setVisible( true );
    }

}