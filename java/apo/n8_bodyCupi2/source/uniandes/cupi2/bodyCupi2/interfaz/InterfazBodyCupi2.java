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

package uniandes.cupi2.bodyCupi2.interfaz;

import java.awt.BorderLayout;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.bodyCupi2.excepciones.FormatoArchivoException;
import uniandes.cupi2.bodyCupi2.excepciones.HoraSalidaExistenteParaRegistroException;
import uniandes.cupi2.bodyCupi2.excepciones.PersistenciaException;
import uniandes.cupi2.bodyCupi2.excepciones.RegistroTiempoExisteException;
import uniandes.cupi2.bodyCupi2.excepciones.RegistroTiempoNoExisteException;
import uniandes.cupi2.bodyCupi2.excepciones.UsuarioExisteException;
import uniandes.cupi2.bodyCupi2.excepciones.UsuarioNoExisteException;
import uniandes.cupi2.bodyCupi2.mundo.BodyCupi2;
import uniandes.cupi2.bodyCupi2.mundo.Fecha;
import uniandes.cupi2.bodyCupi2.mundo.RegistroTiempo;
import uniandes.cupi2.bodyCupi2.mundo.Suscripcion;
import uniandes.cupi2.bodyCupi2.mundo.Usuario;

/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazBodyCupi2 extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante asociada a la serialización
     */
    private static final long serialVersionUID = 700L;

    /**
     * Constante donde se guarda el archivo serializado con los datos de la aplicación
     */
    private final static String RUTA_ARCHIVO_SERIALIZADO = "./data/bodycupi2.data";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private BodyCupi2 bodyCupi2;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;

    /**
     * Panel con la imagen del encabezado
     */
    private PanelImagen panelImagen;

    /**
     * Panel con los detalles de un usuario
     */
    private PanelDetallesUsuario panelDetallesUsuario;

    /**
     * Panel para realizar un registro de tiempo de un usuario
     */
    private PanelIngresarRegistroTiempo panelIngresarRegistroTiempo;

    /**
     * Panel con los detalles de un registro de tiempo de un usuario
     */
    private PanelRegistroTiempo panelDetallesRegistroTiempo;

    /**
     * Panel con los detalles de una suscripción de un usuario
     */
    private PanelSuscripcion panelDetallesSuscripcion;

    /**
     * Panel con el menu principal de la aplicación
     */
    private PanelMenu panelMenu;

    /**
     * Dialogo usado para crear un nuevo usuario
     */
    private DialogoCreacionUsuario dialogoCreacionUsuario;

    /**
     * Dialogo usado para crear una nueva suscripción
     */
    private DialogoCreacionSuscripcion dialogoCreacionSuscripcion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * 1. Construye la ventana principal de la aplicación.<br>
     * 2. Inicializa la clase principal del mundo para que cargue los datos de la aplicación <br>
     * desde un archivo serializado. En caso de un error al cargar los datos se le muestra <br>
     * un mensaje al usuario. 3. Actualiza los paneles creados para que en caso que existan usuarios se muestren sus datos al iniciar la aplicación
     */
    public InterfazBodyCupi2( )
    {

        // Construye la forma
        setSize( 800, 600 );
        setLayout( new BorderLayout( ) );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setTitle( "Body Cupi2" );

        // Crea la clase principal
		bodyCupi2 = new BodyCupi2( RUTA_ARCHIVO_SERIALIZADO );

		// Creación de los paneles aquí
		panelImagen = new PanelImagen( );
		add( panelImagen, BorderLayout.NORTH );

		panelExtension = new PanelExtension( this );
		add( panelExtension, BorderLayout.SOUTH );

		panelMenu = new PanelMenu( this );
		add( panelMenu, BorderLayout.WEST );

		panelIngresarRegistroTiempo = new PanelIngresarRegistroTiempo( this );
		panelDetallesRegistroTiempo = new PanelRegistroTiempo( this );
		panelDetallesUsuario = new PanelDetallesUsuario( this );
		panelDetallesSuscripcion = new PanelSuscripcion( this );

		// Contiene a los paneles: panelIngresarResgistroTiempo, panelDetallesRegistroTiempo y a panelDetallesSuscripcion
		JPanel panelContenedor1 = new JPanel( );
		panelContenedor1.setLayout( new BorderLayout( ) );
		panelContenedor1.setBorder( new TitledBorder( "Registros y suscripciones" ) );
		panelContenedor1.add( panelIngresarRegistroTiempo, BorderLayout.NORTH );
		panelContenedor1.add( panelDetallesRegistroTiempo, BorderLayout.CENTER );
		panelContenedor1.add( panelDetallesSuscripcion, BorderLayout.SOUTH );

		// Panel que contiene a panel contenedor 1 y a panelDetallesUsuario,
		JPanel panelContenedor2 = new JPanel( );
		panelContenedor2.setLayout( new BorderLayout( ) );
		panelContenedor2.add( panelDetallesUsuario, BorderLayout.WEST );
		panelContenedor2.add( panelContenedor1, BorderLayout.CENTER );
		add( panelContenedor2, BorderLayout.CENTER );

		// Actualizar los paneles una vez se han cargado los datos
		actualizarPaneles( );
		setLocationRelativeTo(null);

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Crea un dialogo para registrar un nuevo usuario
     */
    public void crearUsuario( )
    {
        dialogoCreacionUsuario = new DialogoCreacionUsuario( this );
        dialogoCreacionUsuario.setVisible( true );
    }

    /**
     * 1. Crea un dialogo para registrar un nuevo usuario en el gimnasio y asignarle una suscripción <br>
     * 2. En caso que ya exista un usuario con el mismo id se le muestra un mensaje al usuario 3. Después de crear el usuario se actualizan los paneles con el detalle del
     * usuario y con el detalle de sú última suscripción
     * @param historiaMedica Historia medica del usuario
     * @param tipoSuscripcion Tipo suscripcion del usuario
     * @param id El id del usuario
     * @param nombre El nombre del usuario
     * @param edad La edad del usuario
     * @param telefono El telefono del usuario
     * @param genero El genero del usuario
     * @param foto La foto del usuario
     * @param anio El año en que comienza la suscripción
     * @param mes El mes en que comienza la suscripción
     * @param dia El día en que comienza la suscripción
     * @param hora La hora en que comienza la suscripción, por defecto es 0
     * @param minutos Los minutos en que comienza la suscripción, por defecto es 0
     */
    public void guardarDatosUsuario( int id, String nombre, int edad, int telefono, String genero, String foto, String historiaMedica, String tipoSuscripcion, int anio, int mes, int dia, int hora, int minutos )
    {
        try
        {
            bodyCupi2.agregarUsuario( id, nombre, edad, telefono, genero, foto, historiaMedica, tipoSuscripcion, anio, mes, dia, hora, minutos );
            dialogoCreacionUsuario.dispose( );
            JOptionPane.showMessageDialog( this, "Usuario creado exitosamente", "Creación usuario", JOptionPane.INFORMATION_MESSAGE );
            panelDetallesUsuario.refrescar( id, nombre, edad, genero, telefono, historiaMedica, foto );
            Suscripcion suscripcion = bodyCupi2.darUltimaSuscripcionUsuario( id );
            actualizarPanelSuscripcion( suscripcion );
            RegistroTiempo elRegistro = bodyCupi2.darUltimoRegistroTiempo( id );
            if( elRegistro != null )
            {
                Fecha fechaEntrada = elRegistro.darTiempoEntrada( );
                Fecha fechaSalida = elRegistro.darTiempoSalida( );
                panelDetallesRegistroTiempo.actualizar( id, elRegistro.darId( ), fechaEntrada.toString( ), fechaSalida.toString( ), fechaEntrada.darDiferenciaHorasYMinutos( fechaSalida ) );
            }
            else
            {
                panelDetallesRegistroTiempo.actualizar( id, "", "El usuario no tiene registro de tiempo", "El usuario no tiene registro de tiempo", "El usuario no tiene registro de tiempo" );

            }

        }
        catch( UsuarioExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Usuario duplicado", JOptionPane.ERROR_MESSAGE );
        }
    }
    
    /**
     * Actualiza el panel que muestra los detalles de una suscripción,
     * con la recibida por parámetro
     * @param suscripcion La suscripción con la cual se actuliza el panel
     */
    public void actualizarPanelSuscripcion(Suscripcion suscripcion)
    {
        String elIdSuscripcion = suscripcion.darId( );
        String laFecha = suscripcion.darFechaInicioConFormato( );
        String elTipoSuscripcion = suscripcion.darTipoSuscripcion( );
        String elEstado = suscripcion.darEstado( );
        panelDetallesSuscripcion.actualizar( elIdSuscripcion, elTipoSuscripcion, laFecha, elEstado );
    }

    /**
     * 1. Registra el tiempo de salida de un usuario del gimnasio 2. Actualiza el panel con los detalles del registro de tiempo de un usuario 3. Muestra un mensaje al usuario
     * en caso que: a. No exista un registro de tiempo con la misma fecha b. Ya se haya registrado una fecha de salida para el registro de tiempo c. No exista el usuario
     * @param idUsuario El id del usuario
     */
    public void registrarSalida( int idUsuario )
    {
        Calendar fechaActual = Calendar.getInstance( );
        int diaMes = fechaActual.get( Calendar.DAY_OF_MONTH );
        int mes = fechaActual.get( Calendar.MONTH ) + 1;
        int anio = fechaActual.get( Calendar.YEAR );
        int hora = fechaActual.get( Calendar.HOUR_OF_DAY );
        int minutos = fechaActual.get( Calendar.MINUTE );

        try
        {
            try
            {
                bodyCupi2.registrarSalida( idUsuario, anio, mes, diaMes, hora, minutos );
            }
            catch( RegistroTiempoNoExisteException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "ERROR", JOptionPane.ERROR_MESSAGE );
                return;
            }
            catch( HoraSalidaExistenteParaRegistroException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "ERROR", JOptionPane.ERROR_MESSAGE );
                return;
            }
            RegistroTiempo elRegistro = bodyCupi2.darRegistroTiempo( idUsuario, anio, mes, diaMes );
            actualizarPanelRegistroTiempo( idUsuario, elRegistro );
            JOptionPane.showMessageDialog( this, "Registro creado correctamente", "EXITO", JOptionPane.INFORMATION_MESSAGE );

        }
        catch( UsuarioNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "ERROR", JOptionPane.ERROR_MESSAGE );
            return;
        }
    }

    /**
     * 1. Registrar el tiempo de entrada de un usuario al gimansio 2. Actualiza el panel con los detalles del registro de tiempo de un usuario: a. Cómo para el registro de
     * tiempo únicamente se tiene el registro de entrada, en los campos correspondientes al registro de salida, se muestra como mensaje: Tiempo salida: Aún no ha salido y
     * Duración ejercicio: Pendiente 3. Muestra un mensaje en caso que: a. No exista el usuario b. Ya exista un registro de tiempo en la misma fecha
     * @param idUsuario El id del usuario
     */
    public void registrarEntrada( int idUsuario )
    {
        Calendar fechaActual = Calendar.getInstance( );
        int diaMes = fechaActual.get( Calendar.DAY_OF_MONTH );
        int mes = fechaActual.get( Calendar.MONTH ) + 1;
        int anio = fechaActual.get( Calendar.YEAR );
        int hora = fechaActual.get( Calendar.HOUR_OF_DAY );
        int minutos = fechaActual.get( Calendar.MINUTE );
        try
        {
            bodyCupi2.registrarEntrada( idUsuario, anio, mes, diaMes, hora, minutos );
            RegistroTiempo registroCreado = bodyCupi2.darRegistroTiempo( idUsuario, anio, mes, diaMes );
            actualizarPanelRegistroTiempo( idUsuario, registroCreado );
            JOptionPane.showMessageDialog( this, "Registro creado correctamente", "EXITO", JOptionPane.INFORMATION_MESSAGE );
        }
        catch( UsuarioNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "ERROR", JOptionPane.ERROR_MESSAGE );
            return;
        }
        catch( RegistroTiempoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "ERROR", JOptionPane.ERROR_MESSAGE );
            return;
        }

    }

    /**
     * Exportar los usuarios a un archivo de texto En caso de un error se le muestra un mensaje al usuario
     * @param nombreArchivo El nombre del archivo
     * @param pathArchivo La ruta del archivo
     */
    public void exportarUsuarios( String pathArchivo, String nombreArchivo )
    {
        try
        {
            bodyCupi2.exportarUsuarios( pathArchivo, nombreArchivo );
            JOptionPane.showMessageDialog( this, "Usuarios exportados correctamente" );

        }
        catch( PersistenciaException e )
        {
            
            JOptionPane.showMessageDialog( this, e.getMessage( ) + ": " + e.darNombreArchivo( ), "Error al exportar datos", JOptionPane.ERROR_MESSAGE );
        }

    }


    
    /**
     * Exporta en un archivo de texto la lista de los usuarios con suscripciones vencidas
     * @param pathArchivo La ruta del archivo en el cual se van a guardar los datos
     * @param nombreArchivo El nombre del archivo en el cual se van a guardar los datos
     */
    public void exportarUsuariosConSuscripcionesVencidas( String pathArchivo, String nombreArchivo )
    {
        try
        {
            bodyCupi2.exportarUsuariosConSuscripcionesVencidas( pathArchivo, nombreArchivo );
            JOptionPane.showMessageDialog( this, "Reporte de suscripciones vencidas creado" );
        }
        catch( PersistenciaException e )
        {
            
            JOptionPane.showMessageDialog( this, e.getMessage( ) + ": " + e.darNombreArchivo( ), "Error al exportar datos", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Actualiza el panel de suscripción luego que una nueva suscripción es creada
     * @throws Exception En caso de error al parsear el ID de un usuario
     */
    public void actualizarDetallesSuscripcion( ) throws Exception
    {
        int idUsuarioActual;
        idUsuarioActual = darIdUsuario( );
        Usuario usuario = bodyCupi2.darUsuario( idUsuarioActual );
        Suscripcion suscripcion = usuario.darUltimaSuscripcion( );
        actualizarPanelSuscripcion( suscripcion );

    }

    /**
     * Actualiza el panel de los detalles de un usuario con los del siguiente usuario.
     * @param idUsuarioActual El id del usuario actual
     * 
     */
    public void actualizarUsuarioSiguiente( int idUsuarioActual )
    {
        Usuario usuarioSiguiente = bodyCupi2.darSiguienteUsuario( idUsuarioActual );

        panelDetallesUsuario.refrescar( usuarioSiguiente.darId( ), usuarioSiguiente.darNombre( ), usuarioSiguiente.darEdad( ), usuarioSiguiente.darGenero( ), usuarioSiguiente.darTelefono( ), usuarioSiguiente.darRegistroMedico( ), usuarioSiguiente
                .darFoto( ) );

    }

    /**
     * Actualiza toda la interfaz cuando en el panel con los detalles del usuario se pasa al anterior
     * @param idUsuario El ID del usuario
     */
    public void pasarUsuarioAnterior( int idUsuario )
    {
        Usuario usuario = bodyCupi2.darUsuarioAnterior( idUsuario );
        if( usuario != null )
        {
            actualizarUsuarioAnterior( idUsuario );
            actualizarSuscripcionUsuarioAnterior( idUsuario );
            actualizarRegistroTiempoUsuarioAnterior( idUsuario );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Está en el primer usuario", "Atención", JOptionPane.INFORMATION_MESSAGE );
        }
    }

    /**
     * Actualiza toda la interfaz cuando en el panel con los detalles del usuario se pasa al siguiente
     * @param idUsuario El ID del usuario
     */
    public void pasarSiguienteUsuario( int idUsuario )
    {
        Usuario usuario = bodyCupi2.darSiguienteUsuario( idUsuario );
        if( usuario != null )
        {
            actualizarUsuarioSiguiente( idUsuario );
            actualizarSuscripcionUsuarioSiguiente( idUsuario );
            actualizarRegistroTiempoSiguienteUsuario( idUsuario );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Está en el último usuario", "Atención", JOptionPane.INFORMATION_MESSAGE );
        }

    }

    /**
     * Actualiza el panel de los detalles de un usuario con los del anterior usuario.
     * @param idUsuarioActual El id del usuario actual
     * 
     */
    public void actualizarUsuarioAnterior( int idUsuarioActual )
    {
        Usuario usuarioAnterior = bodyCupi2.darUsuarioAnterior( idUsuarioActual );
        panelDetallesUsuario.refrescar( usuarioAnterior.darId( ), usuarioAnterior.darNombre( ), usuarioAnterior.darEdad( ), usuarioAnterior.darGenero( ), usuarioAnterior.darTelefono( ), usuarioAnterior.darRegistroMedico( ), usuarioAnterior.darFoto( ) );
    }

    /**
     * Actualiza el panel que muestra los detalles de una suscripción, cuando el usuario oprime el botón para ver el usuario anterior al seleccionado actualmente
     * @param idUsuarioActual El id del usuario actual
     */
    public void actualizarSuscripcionUsuarioAnterior( int idUsuarioActual )
    {
        Usuario usuarioAnterior = bodyCupi2.darUsuarioAnterior( idUsuarioActual );
        Suscripcion suscripcion = usuarioAnterior.darUltimaSuscripcion( );
        actualizarPanelSuscripcion( suscripcion );

    }

    /**
     * Actualiza el panel que muestra los detalles de una suscripción, cuando el usuario oprime el botón para ver el usuario siguiente al seleccionado actualmente
     * @param idUsuarioActual El id del usuario actual
     */
    public void actualizarSuscripcionUsuarioSiguiente( int idUsuarioActual )
    {
        Usuario usuarioSiguiente = bodyCupi2.darSiguienteUsuario( idUsuarioActual );
        Suscripcion suscripcion = usuarioSiguiente.darUltimaSuscripcion( );
        actualizarPanelSuscripcion( suscripcion );
    }

    /**
     * Retorna el id de un usuario
     * @return El id del usuario
     */
    public int darIdUsuario( )
    {
        int idUsuario = panelDetallesUsuario.darIdusuario( );
        return idUsuario;
    }

    /**
     * Actualiza el panel que muestra los detalles de una suscripción, cuando el usuario oprime el botón para ver el siguiente usuario al seleccionado actualmente
     * @param idUsuarioActual El id del usuario actual
     */
    public void actualizarSuscripcionSiguienteDelUsuarioActual( int idUsuarioActual )
    {
        Usuario usuario = bodyCupi2.darUsuario( idUsuarioActual );
        String idSuscripcion = panelDetallesSuscripcion.darIdSuscripcionActual( );
        Suscripcion suscripcion = usuario.darSiguienteSuscripcion( idSuscripcion );
        if( suscripcion != null )
        {
            actualizarPanelSuscripcion( suscripcion );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Está en la última suscripción", "Atención", JOptionPane.INFORMATION_MESSAGE );
        }

    }

    /**
     * Actualiza el panel que muestra los detalles de una suscripción, con los detalles de la suscripción anterior, del usuario actual
     * @param idUsuarioActual El id del usuario actual
     */
    public void actualizarSuscripcionAnteriorDelUsuarioActual( int idUsuarioActual )
    {
        Usuario usuario = bodyCupi2.darUsuario( idUsuarioActual );
        String idSuscripcion = panelDetallesSuscripcion.darIdSuscripcionActual( );
        Suscripcion suscripcion = usuario.darAnteriorSuscripcion( idSuscripcion );
        if( suscripcion != null )
        {
            actualizarPanelSuscripcion( suscripcion );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Está en la primera suscripción", "Atención", JOptionPane.INFORMATION_MESSAGE );
        }

    }

    /**
     * Actualiza los paneles con los datos del usuario inicial, al momento de iniciar la aplicación. Refresca el panel con el detalle de su última suscripción y el panel con
     * su último registro de tiempo
     */
    public void actualizarPaneles( )
    {
        boolean existenUsuarios = bodyCupi2.existenUsuarios( );
        if( existenUsuarios )
        {
            // Obtiene al primer usuario
            Usuario usuarioInicial = bodyCupi2.darUsuarioInicial( );
            // Obtiene los datos de la última suscripción del usuario
            Suscripcion suscripcion = bodyCupi2.darUltimaSuscripcionUsuario( usuarioInicial.darId( ) );
            // Actualiza el panel detalles del usuario
            panelDetallesUsuario.refrescar( usuarioInicial.darId( ), usuarioInicial.darNombre( ), usuarioInicial.darEdad( ), usuarioInicial.darGenero( ), usuarioInicial.darTelefono( ), usuarioInicial.darRegistroMedico( ), usuarioInicial.darFoto( ) );
            // Actualiza el panel detalles suscripción
            actualizarPanelSuscripcion( suscripcion );
            // Actualiza el panel registro de tiempo
            RegistroTiempo registro = bodyCupi2.darUltimoRegistroTiempo( usuarioInicial.darId( ) );
            if( registro != null )
            {
                String idRegistro = registro.darId( );
                Fecha tiempoEntrada = registro.darTiempoEntrada( );

                if( registro.existeTiempoSalida( ) )
                {
                    Fecha tiempoSalida = registro.darTiempoSalida( );
                    panelDetallesRegistroTiempo.actualizar( usuarioInicial.darId( ), idRegistro, tiempoEntrada.toString( ), tiempoSalida.toString( ), tiempoEntrada.darDiferenciaHorasYMinutos( tiempoSalida ) );
                }
                else
                {
                    panelDetallesRegistroTiempo.actualizar( usuarioInicial.darId( ), idRegistro, tiempoEntrada.toString( ), "Aún no ha salido", "Pendiente" );

                }
            }
            else
            {
                panelDetallesRegistroTiempo.actualizar( usuarioInicial.darId( ), "", "El usuario no tiene registro de tiempo", "El usuario no tiene registro de tiempo", "El usuario no tiene registro de tiempo" );
            }

        }
    }

  

    /**
     * Construye un dialogo para crear una suscripción
     */
    public void crearDialogoNuevaSuscripcion( )
    {
        int idUsuario;
        try
        {
            idUsuario = panelDetallesUsuario.darIdusuario( );
            dialogoCreacionSuscripcion = new DialogoCreacionSuscripcion( this, idUsuario );
            dialogoCreacionSuscripcion.setVisible( true );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "El ID de un usuario debe ser un valor numérico", "Advertencia", JOptionPane.INFORMATION_MESSAGE );
        }

    }

    /**
     * Construye un dialogo para crear una nueva suscripción
     * @param idUsuario El id del usuario
     * @param tipoSuscripcion El tipo de la suscripción
     * @param diaMes El día del mes de la suscripción
     * @param mes El mes de la suscripción
     * @param anho El año de la suscripción
     */
    public void crearSuscripcion( int idUsuario, String tipoSuscripcion, int diaMes, int mes, int anho )
    {
        
    	Calendar fechaActualTemp = Calendar.getInstance( );
        int diaActual = fechaActualTemp.get( Calendar.DAY_OF_MONTH );
        int mesActual = fechaActualTemp.get( Calendar.MONTH ) + 1;
        int anioActual = fechaActualTemp.get( Calendar.YEAR );
       
        
        Fecha fechaActual= new Fecha(anioActual, mesActual, diaActual, 0, 0);
        Fecha fechaSuscripcion=new Fecha(anho, mes,diaMes,0,0);
        
        int comparacion=fechaSuscripcion.compararFechas(fechaActual);
        if(comparacion>=0)
        {
        	bodyCupi2.crearSuscripcion( idUsuario, tipoSuscripcion, diaMes, mes, anho );
            dialogoCreacionSuscripcion.dispose( );
            try
            {
                actualizarDetallesSuscripcion( );
                JOptionPane.showMessageDialog( this, "Suscripción creada correctamente", "EXITO", JOptionPane.INFORMATION_MESSAGE );
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, "El ID del usuario debe ser un valor numérico", "Advertencia", JOptionPane.INFORMATION_MESSAGE );
            }
        }
        else
        {
        	JOptionPane.showMessageDialog( this, "La fecha de la suscripción debe ser posterior o igual a la fecha actual", "ERROR", JOptionPane.ERROR_MESSAGE );
        }
        
        

    }

    /**
     * Actualiza el panel que muestra los detalles de un registro de tiempo, con los datos del último registro de tiempo del usuario anterior.
     * @param idUsuario El id del siguiente usuario
     */
    public void actualizarRegistroTiempoUsuarioAnterior( int idUsuario )
    {
        Usuario usuario = bodyCupi2.darUsuarioAnterior( idUsuario );
        RegistroTiempo registro = usuario.darUltimoRegistroTiempo( );

        if( registro != null )
        {
            actualizarPanelRegistroTiempo(usuario.darId( ),registro);

        }
        else
        {
            panelDetallesRegistroTiempo.actualizar( usuario.darId( ), "", "El usuario no tiene registro de tiempo", "El usuario no tiene registro de tiempo", "El usuario no tiene registro de tiempo" );
        }

    }

    
    /**
     * Actualiza el panel con los detalles de un registro de tiempo
     * @param idUsuario El ID del usuario
     * @param registro El registro de tiempo
     */
    public void actualizarPanelRegistroTiempo(int idUsuario,RegistroTiempo registro)
    {
        String idRegistro = registro.darId( );
        Fecha tiempoEntrada = registro.darTiempoEntrada( );
        if( registro.existeTiempoSalida( ) )
        {
            Fecha tiempoSalida = registro.darTiempoSalida( );
            panelDetallesRegistroTiempo.actualizar( idUsuario, idRegistro, tiempoEntrada.toString( ), tiempoSalida.toString( ), tiempoEntrada.darDiferenciaHorasYMinutos( tiempoSalida ) );
        }
        else
        {
            panelDetallesRegistroTiempo.actualizar( idUsuario, idRegistro, tiempoEntrada.toString( ), "Aún no ha salido", "Pendiente" );

        }
    }
    
    /**
     * Actualiza el panel con los detalles del anterior registro de tiempo
     * @param idRegistroTiempo El ID del registro de tiempo
     */
    public void actualizarRegistroTiempoAnterior( String idRegistroTiempo )
    {
            int idUsuario = darIdUsuario( );
            if( bodyCupi2.darNumRegistrosTiempo( idUsuario ) > 0 )
            {
                RegistroTiempo registroTiempoAnterior = bodyCupi2.darAnteriorRegistroTiempo( darIdUsuario( ), idRegistroTiempo );
                if( registroTiempoAnterior != null )
                {
                    actualizarPanelRegistroTiempo(idUsuario,registroTiempoAnterior);
                }
                else
                {
                    JOptionPane.showMessageDialog( this, "Está en el primer registro de tiempo", "Atención", JOptionPane.INFORMATION_MESSAGE );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "El usuario no tiene registros de tiempo", "Atención", JOptionPane.INFORMATION_MESSAGE );

            }
        
        

    }

    /**
     * Actualiza el panel con los detalles del siguiente registro de tiempo
     * @param idRegistroTiempo El ID del registro de tiempo
     */
    public void actualizarRegistroTiempoSiguiente( String idRegistroTiempo )
    {
            int idUsuario = darIdUsuario( );
            if( bodyCupi2.darNumRegistrosTiempo( idUsuario ) > 0 )
            {
                RegistroTiempo registroTiempoSiguiente = bodyCupi2.darSiguienteRegistroTiempo( darIdUsuario( ), idRegistroTiempo );
                if( registroTiempoSiguiente != null )
                {
                    actualizarPanelRegistroTiempo(idUsuario,registroTiempoSiguiente);
                }
                else
                {
                    JOptionPane.showMessageDialog( this, "Está en el último registro de tiempo", "Atención", JOptionPane.INFORMATION_MESSAGE );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "El usuario no tiene registros de tiempo", "Atención", JOptionPane.INFORMATION_MESSAGE );
            }
        
    }

    /**
     * Actualiza el panel que muestra los detalles de un registro de tiempo, con los datos del último registro de tiempo del siguiente usuario.
     * @param idUsuario El id del siguiente usuario
     */
    public void actualizarRegistroTiempoSiguienteUsuario( int idUsuario )
    {
        Usuario usuario = bodyCupi2.darSiguienteUsuario( idUsuario );
        RegistroTiempo registro = usuario.darUltimoRegistroTiempo( );

        if( registro != null )
        {
            actualizarPanelRegistroTiempo(usuario.darId( ),registro);
        }
        else
        {
            panelDetallesRegistroTiempo.actualizar( usuario.darId( ), "", "El usuario no tiene registro de tiempo", "El usuario no tiene registro de tiempo", "El usuario no tiene registro de tiempo" );
        }

    }

    /**
     * Exporta en un archivo de texto los registros de tiempo de un usuario
     * @param elIdUsuario El id del usuario para el cual se van a exportar los registros de tiempos
     * @param pathArchivo La ruta del archivo donde se van a guardar los datos
     * @param nombreArchivo El nombre del archivo donde se van a guardar los datos
     */
    public void exportarRegistrosTiempoUsuario( int elIdUsuario, String pathArchivo, String nombreArchivo )
    {

        try
        {
            bodyCupi2.exportarRegistrosTiempoUsuario( elIdUsuario, pathArchivo, nombreArchivo );
            JOptionPane.showMessageDialog( this, "Reporte creado exitosamente", "EXITO", JOptionPane.INFORMATION_MESSAGE );
        }
        catch( PersistenciaException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ) + ": " + e.darNombreArchivo( ), "Error al exportar datos", JOptionPane.ERROR_MESSAGE );
        }

    }
    


    /**
     * Carga de un archivo de texto plano datos de los usuarios de la aplicación
     * @param rutaArchivo La ruta donde se encuentran los datos
     */
    public void cargarUsuarios( String rutaArchivo )
    {
        try
        {
            bodyCupi2.importarUsuarios( rutaArchivo );
            JOptionPane.showMessageDialog( this, "Usuarios importados correctamente", "EXITO", JOptionPane.INFORMATION_MESSAGE );
            actualizarPaneles( );
        }
        catch( FormatoArchivoException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error al importar usuarios", JOptionPane.ERROR_MESSAGE );
        }
          
    }
    
    /**
     * Método llamado cuando se cierra la aplicación:<br>
     * Guarda los datos de los servicios en un archivo serializado <br>
     * Si se presenta una excepción en el proceso de serialización del estado del modelo del mundo, este método debe avisar al usuario que la aplicación va a cerrarse sin salvar la información. 
     */
    public void dispose( )
    {
    	try {
    		bodyCupi2.persistirDatos(RUTA_ARCHIVO_SERIALIZADO);
    		super.dispose();
    	} catch (PersistenciaException e) {
    		JOptionPane.showMessageDialog(this, "No ha sido posible guardar la información de los usuarios. /n La siguiente razón fue dada: /n"+e.getMessage(), "Error de persistencia", JOptionPane.ERROR_MESSAGE);
    		super.dispose();
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
        String resultado = bodyCupi2.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = bodyCupi2.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {

        InterfazBodyCupi2 interfaz = new InterfazBodyCupi2( );
        interfaz.setVisible( true );
    }

    


}