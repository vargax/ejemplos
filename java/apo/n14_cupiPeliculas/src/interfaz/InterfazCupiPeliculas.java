package interfaz;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import javax.swing.event.*;
import mundo.CupiPeliculas;
import mundo.ICupiPeliculas;

public class InterfazCupiPeliculas extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel panelBusqueda;
    private JPanel panelIndices;
    private JPanel panelVisualizacionInfo;
    private JPanel panelBotones;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JPanel panelTodosIndices;
    private JScrollPane scrollPane;
    private JPanel panelInfo;
    private JPanel panel_1;
    private JScrollPane scrollPane_1;
    private JTextArea txtrDescripcionPelcula;
    private JPanel panelTitulo;
    private JLabel lblTitulo;
    private JLabel lblAno;

    private final static String[] columnasPeliculas = { "id", "Título", "Año", "Descripción" };
    private String[][] infoPeliculas = { { "0", "Titulo película 0", "2000", "Descripción película 0" }, { "1", "Titulo película 1", "2001", "Descripción película 1" }, { "2", "Titulo película 2", "2002", "Descripción película 2" },
            { "3", "Titulo película 3", "2003", "Descripción película 3" }, { "4", "Titulo película 4", "2004", "Descripción película 4" } };
    private DefaultTableModel modeloTablaPeliculas;
    private JTable tablaPeliculas;
    private int idPelicualActual;

    private DefaultTableModel modeloResultadosBusqueda;
    private JTable tablaResultadosBusqueda;
    private int idPeliculaResultados;
    private String[][] infoResultados = { { "0", "Titulo resultado 0", "2000", "Descripción resultado 0" }, { "1", "Titulo resultado 1", "2001", "Descripción resultado 1" }, { "2", "Titulo resultado 2", "2002", "Descripción resultado 2" },
            { "3", "Titulo resultado 3", "2003", "Descripción resultado 3" }, { "4", "Titulo resultado 4", "2004", "Descripción resultado 4" } };

    private JList list;
    private JPanel panelResultado;
    private JPanel panelCriteriosBusqueda;
    private JButton btnNombre;
    private JButton btnDescripcion;
    private JButton btnAoProduccin;
    private JButton btnRango;
    private JLabel lblTodosLosIndices;
    private JPanel panelTodasPeliculas;
    private JPanel panel;
    private JScrollPane scrollPane_2;
    private JPanel panel_2;
    private JPanel panel_4;
    private JLabel lblNombre;
    private JTextField txtNombrePelicula;
    private JLabel lblAo;
    private JTextField txtAñoPelicula;
    private JTextField txtAreaDescrPelicula;
    private JScrollPane scrollPane_3;

    //
    // CONSTANTES DE MANEJO DE EVENTOS
    //
    public final static String BUSCAR_NOMBRE = "nombre";
    public final static String BUSCAR_ANIO = "año";
    public final static String BUSCAR_RANGO = "rango";
    public final static String BUSCAR_DESCRIPCION = "descripcion";
    public final static String ANTERIOR = "anterior";
    public final static String SIGUIENTE = "siguiente";

    //
    // ATRIBUTO QUE CONECTA EL MUNDO CON LA INTERFAZ
    //
    private CupiPeliculas principal;
    /**
     * Launch the application.
     */
    public static void main( String[] args )
    {
        EventQueue.invokeLater( new Runnable( )
        {
            public void run( )
            {
                try
                {
                    CupiPeliculas c = new CupiPeliculas();// "ruta" );
                    InterfazCupiPeliculas frame = new InterfazCupiPeliculas( c );
                    frame.setVisible( true );
                }
                catch( Exception e )
                {
                    e.printStackTrace( );
                }
            }
        } );
    }

    /**
     * Create the frame.
     */
    public InterfazCupiPeliculas( CupiPeliculas c )throws Exception
    {
        principal = c;
//        principal.cargarPeliculas( "./docs/plot.list" );

        setTitle( "Indices" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setBounds( 100, 100, 809, 528 );
        contentPane = new JPanel( );
        contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
        setContentPane( contentPane );
        contentPane.setLayout( new BorderLayout( 0, 0 ) );

        JTabbedPane tabbedPane = new JTabbedPane( JTabbedPane.TOP );
        contentPane.add( tabbedPane, BorderLayout.CENTER );

        panelTodasPeliculas = new JPanel( );
        tabbedPane.addTab( "Todas las pel\u00EDculas", null, panelTodasPeliculas, null );
        panelTodasPeliculas.setLayout( new BorderLayout( 0, 0 ) );

        panel = new JPanel( );
        panel.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
        panelTodasPeliculas.add( panel, BorderLayout.CENTER );
        panel.setLayout( new GridLayout( 2, 1, 0, 0 ) );

        scrollPane_2 = new JScrollPane( );
        modeloTablaPeliculas = new DefaultTableModel( );
        modeloTablaPeliculas.setDataVector( infoPeliculas, columnasPeliculas );
        tablaPeliculas = new JTable( modeloTablaPeliculas );
        tablaPeliculas.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        tablaPeliculas.getSelectionModel( ).addListSelectionListener( new ListSelectionListener( )
        {
            @Override
            public void valueChanged( ListSelectionEvent arg0 )
            {
                idPelicualActual = Integer.parseInt( ( String )modeloTablaPeliculas.getValueAt( tablaPeliculas.getSelectedRow( ), 0 ) );
                cargarInfoPelicula( );
            }
        } );

        scrollPane_2.setViewportView( tablaPeliculas );
        panel.add( scrollPane_2 );

        panel_2 = new JPanel( );
        panel_2.setBorder( new TitledBorder( UIManager.getBorder( "TitledBorder.aquaVariant" ), "Informacion de la pel\u00EDcula", TitledBorder.LEADING, TitledBorder.TOP, null, null ) );
        panel.add( panel_2 );
        panel_2.setLayout( new BorderLayout( 0, 0 ) );

        panel_4 = new JPanel( );
        panel_4.setBorder( new EmptyBorder( 0, 12, 0, 12 ) );
        panel_2.add( panel_4, BorderLayout.NORTH );
        panel_4.setLayout( new FlowLayout( FlowLayout.CENTER, 5, 5 ) );

        lblNombre = new JLabel( "Nombre" );
        panel_4.add( lblNombre );

        txtNombrePelicula = new JTextField( );
        txtNombrePelicula.setEditable( false );
        panel_4.add( txtNombrePelicula );
        txtNombrePelicula.setColumns( 40 );

        lblAo = new JLabel( "A\u00F1o" );
        panel_4.add( lblAo );

        txtAñoPelicula = new JTextField( );
        txtAñoPelicula.setEditable( false );
        panel_4.add( txtAñoPelicula );
        txtAñoPelicula.setColumns( 6 );

        txtAreaDescrPelicula = new JTextField( );
        txtAreaDescrPelicula.setBackground( Color.WHITE );
        txtAreaDescrPelicula.setEditable( false );
        panel_2.add( txtAreaDescrPelicula, BorderLayout.CENTER );
        txtAreaDescrPelicula.setColumns( 10 );

        panelIndices = new JPanel( );
        panelIndices.setBounds( new Rectangle( 60, 60, 60, 60 ) );
        panelIndices.setName( "indices" );
        tabbedPane.addTab( "Indices de pel\u00EDculas", null, panelIndices, null );
        tabbedPane.setEnabledAt( 1, true );

        panelIndices.setLayout( new BorderLayout( 0, 0 ) );

        panelVisualizacionInfo = new JPanel( );
        panelIndices.add( panelVisualizacionInfo, BorderLayout.CENTER );
        panelVisualizacionInfo.setLayout( new BorderLayout( 0, 0 ) );

        panelBotones = new JPanel( );
        panelBotones.setBorder( new EmptyBorder( 0, 15, 0, 15 ) );
        panelVisualizacionInfo.add( panelBotones, BorderLayout.SOUTH );
        panelBotones.setLayout( new BorderLayout( 0, 0 ) );

        btnNewButton = new JButton( "Anterior" );
        btnNewButton.setActionCommand( ANTERIOR );
        btnNewButton.addActionListener( this );

        panelBotones.add( btnNewButton, BorderLayout.WEST );

        btnNewButton_1 = new JButton( "Siguiente" );
        btnNewButton_1.setActionCommand( SIGUIENTE );
        btnNewButton_1.addActionListener( this );

        panelBotones.add( btnNewButton_1, BorderLayout.EAST );

        panelInfo = new JPanel( );
        panelVisualizacionInfo.add( panelInfo, BorderLayout.CENTER );
        panelInfo.setLayout( new BorderLayout( 0, 0 ) );

        panel_1 = new JPanel( );
        panel_1.setBorder( new CompoundBorder( new EmptyBorder( 5, 0, 2, 5 ), UIManager.getBorder( "InsetBorder.aquaVariant" ) ) );
        panelInfo.add( panel_1, BorderLayout.CENTER );
        panel_1.setLayout( new BorderLayout( 0, 0 ) );

        scrollPane_1 = new JScrollPane( ( Component )null );
        panel_1.add( scrollPane_1, BorderLayout.CENTER );

        txtrDescripcionPelcula = new JTextArea( );
        txtrDescripcionPelcula.setText( "Descripcion pel\u00EDcula" );
        scrollPane_1.setViewportView( txtrDescripcionPelcula );

        panelTitulo = new JPanel( );
        panelTitulo.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
        panelVisualizacionInfo.add( panelTitulo, BorderLayout.NORTH );
        panelTitulo.setLayout( new BorderLayout( 0, 0 ) );

        lblTitulo = new JLabel( "T\u00EDtulo" );
        lblTitulo.setHorizontalTextPosition( SwingConstants.CENTER );
        lblTitulo.setHorizontalAlignment( SwingConstants.CENTER );
        lblTitulo.setFont( new Font( "Lucida Grande", Font.BOLD, 25 ) );
        panelTitulo.add( lblTitulo, BorderLayout.CENTER );

        lblAno = new JLabel( "1994" );
        lblAno.setHorizontalAlignment( SwingConstants.CENTER );
        lblAno.setFont( new Font( "Lucida Grande", Font.PLAIN, 16 ) );
        panelTitulo.add( lblAno, BorderLayout.SOUTH );

        panelTodosIndices = new JPanel( );
        panelTodosIndices.setBorder( new CompoundBorder( new EmptyBorder( 6, 6, 6, 6 ), UIManager.getBorder( "InsetBorder.aquaVariant" ) ) );
        panelIndices.add( panelTodosIndices, BorderLayout.WEST );
        panelTodosIndices.setLayout( new BorderLayout( 0, 0 ) );
        list = new JList( );

        scrollPane = new JScrollPane( list );
        scrollPane.setBounds( new Rectangle( 70, 70, 0, 0 ) );
        panelTodosIndices.add( scrollPane, BorderLayout.CENTER );

        lblTodosLosIndices = new JLabel( "TODOS LOS INDICES" );
        lblTodosLosIndices.setHorizontalAlignment( SwingConstants.CENTER );
        lblTodosLosIndices.setBorder( new LineBorder( new Color( 0, 0, 0 ) ) );
        scrollPane.setColumnHeaderView( lblTodosLosIndices );
        panelBusqueda = new JPanel( );
        tabbedPane.addTab( "Busqueda", null, panelBusqueda, null );
        panelBusqueda.setLayout( new BorderLayout( 0, 0 ) );

        panelResultado = new JPanel( );
        panelResultado.setBorder( new CompoundBorder( new EmptyBorder( 3, 4, 3, 4 ), new TitledBorder( UIManager.getBorder( "TitledBorder.aquaVariant" ), "Resultados", TitledBorder.CENTER, TitledBorder.TOP, null, new Color( 0, 0, 0 ) ) ) );
        panelBusqueda.add( panelResultado, BorderLayout.CENTER );
        panelResultado.setLayout( new BorderLayout( 0, 0 ) );

        scrollPane_3 = new JScrollPane( );
        modeloResultadosBusqueda = new DefaultTableModel( );
        modeloResultadosBusqueda.setDataVector( infoResultados, columnasPeliculas );
        tablaResultadosBusqueda = new JTable( modeloResultadosBusqueda );
        tablaResultadosBusqueda.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        tablaResultadosBusqueda.getSelectionModel( ).addListSelectionListener( new ListSelectionListener( )
        {
            @Override
            public void valueChanged( ListSelectionEvent arg0 )
            {
                idPeliculaResultados = Integer.parseInt( ( String )modeloResultadosBusqueda.getValueAt( tablaResultadosBusqueda.getSelectedRow( ), 0 ) );
            }
        } );
        scrollPane_3.setViewportView( tablaResultadosBusqueda );
        panelResultado.add( scrollPane_3, BorderLayout.CENTER );

        panelCriteriosBusqueda = new JPanel( );
        panelCriteriosBusqueda.setBorder( new CompoundBorder( new EmptyBorder( 3, 3, 3, 3 ), new TitledBorder( UIManager.getBorder( "TitledBorder.aquaVariant" ), "Criterio de b\u00FAsqueda", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(
                0, 0, 0 ) ) ) );
        panelBusqueda.add( panelCriteriosBusqueda, BorderLayout.WEST );
        panelCriteriosBusqueda.setLayout( new GridLayout( 4, 1, 5, 18 ) );

        btnNombre = new JButton( "Nombre" );
        btnNombre.setActionCommand( BUSCAR_NOMBRE );
        btnNombre.addActionListener( this );

        panelCriteriosBusqueda.add( btnNombre );

        btnDescripcion = new JButton( "Descripcion" );
        btnDescripcion.setActionCommand( BUSCAR_DESCRIPCION );
        btnDescripcion.addActionListener( this );

        panelCriteriosBusqueda.add( btnDescripcion );

        btnAoProduccin = new JButton( "A\u00F1o Producci\u00F3n" );
        btnAoProduccin.setActionCommand( BUSCAR_ANIO );
        btnAoProduccin.addActionListener( this );

        panelCriteriosBusqueda.add( btnAoProduccin );

        btnRango = new JButton( "Rango a\u00F1os" );
        btnRango.setActionCommand( BUSCAR_RANGO );
        btnRango.addActionListener( this );

        panelCriteriosBusqueda.add( btnRango );
    }
    /**
     * Carga la información de la película seleccionada
     */
    public void cargarInfoPelicula( )
    {
        txtNombrePelicula.setText( infoPeliculas[ idPelicualActual ][ 1 ] );
        txtAñoPelicula.setText( infoPeliculas[ idPelicualActual ][ 2 ] );
        txtAreaDescrPelicula.setText( infoPeliculas[ idPelicualActual ][ 3 ] );
    }

    /**
     * 
     * @param arg0
     */
    public void actionPerformed( ActionEvent event )
    {
        try
        {
            String command = event.getActionCommand( );
            if( command.equals( SIGUIENTE ) )
            {
//                principal.darSiguiente( );
            }
            else if( command.equals( BUSCAR_ANIO ) )
            {
                principal.buscarPeliculasPorAño( 12 );
            }
            else if( command.equals( BUSCAR_DESCRIPCION ) )
            {
                principal.buscarPeliculasPorDescripción( "" );
            }
            else if( command.equals( BUSCAR_NOMBRE ) )
            {
               principal.buscarPeliculasPorNombre( "" );
            }
            else if( command.equals( BUSCAR_RANGO ) )
            {
                principal.buscarPeliculasPorRangoAños( 1, 2 );
            }
            else if( command.equals( ANTERIOR ) )
            {
//                principal.darAnterior( );
            }

        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "Información inválida: intente de nuevo..." );
        }

    }
}