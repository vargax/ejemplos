package uniandes.cupi2.bodyCupi2.test;
import junit.framework.TestCase;
import uniandes.cupi2.bodyCupi2.excepciones.RegistroTiempoExisteException;
import uniandes.cupi2.bodyCupi2.mundo.Fecha;
import uniandes.cupi2.bodyCupi2.mundo.RegistroTiempo;
import uniandes.cupi2.bodyCupi2.mundo.Suscripcion;
import uniandes.cupi2.bodyCupi2.mundo.Usuario;

/**
 * Clase para probar los métodos de la clase Usuario
 *
 */
public class UsuarioTest extends TestCase
{
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Usuario usuario;
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    
    /**
     * Crea un nuevo usuario
     */
    public void setupEscenario1()
    {
    	usuario=new Usuario(123,"nombre usuario1", 24, 6109834,Usuario.GENERO_MASCULINO,"/data/imagenes/usuario1.jpg", "Ninguno");
    }
    
    /**
     * Crea un nuevo usuario con dos registros de tiempos
     */
    public void setupEscenario2()
    {
        setupEscenario1( );
        Fecha fechaEntrada= new Fecha( 2011, 2, 25, 10, 30 );
        Fecha fechaEntrada2= new Fecha( 2011, 3, 12, 23, 0 );
        try
        {
            usuario.crearRegistroTiempo( fechaEntrada );
            usuario.crearRegistroTiempo( fechaEntrada2 );
        }
        catch( RegistroTiempoExisteException e )
        {
            fail("Error al crear un registro de tiempo");
        } 
    }
    
    /**
     * Crea un nuevo usuario con dos suscripciones
     */
    public void setupEscenario3()
    {
        setupEscenario1( );
        usuario.crearSuscripcion( 2011, 1, 2, 0, 0, Suscripcion.SUSCRIPCION_AMATEUR  );
        usuario.crearSuscripcion(  2011, 1, 7, 0, 0 , Suscripcion.SUSCRIPCION_REGULAR );
    }
    
    /**
     * Verifica que el usuario se inicialice correctamente
     */
    public void testUsuario()
    {
    	setupEscenario1( );
    	int id=usuario.darId( );
    	String nombre=usuario.darNombre( );
    	int edad=usuario.darEdad( );
    	int telefono=usuario.darTelefono( );
    	String genero=usuario.darGenero( );
    	String foto=usuario.darFoto( );
    	String registroMedico=usuario.darRegistroMedico( );
    	assertEquals( "El ID es incorrecto",123, id );
    	assertEquals( "El nombre es incorrecto","nombre usuario1", nombre );
    	assertEquals( "La edad es incorrecta",24, edad );
    	assertEquals( "El teléfono es incorrecto",6109834, telefono );
    	assertEquals( "El género es incorrecto",Usuario.GENERO_MASCULINO, genero );
    	assertEquals( "La foto es incorrecto","/data/imagenes/usuario1.jpg", foto );
    	assertEquals( "El registro médico es incorrecto","Ninguno", registroMedico );
    	assertEquals( "Se debe inicializar las suscripciones",0,usuario.darNumSuscripciones( ) );
    	assertEquals( "Se debe inicializar los registros de tiempo",0,usuario.darNumRegistrosTiempo( ) );
    }
    
    /**
     * Verifica que se agregue una suscripción al vector de suscripciones
     */
    public void testCrearSuscripcion()
    {
        setupEscenario1( );
        int numSuscripciones=usuario.darNumSuscripciones( );
        usuario.crearSuscripcion( 2011, 02, 10, 0, 0, Suscripcion.SUSCRIPCION_AMATEUR  );
        int numSuscripciones2=usuario.darNumSuscripciones( );
        assertEquals("No se agregó la suscripción", 1, numSuscripciones2-numSuscripciones );
    }
    
    /**
     * Verifica que se agregue un registro de tiempo cuando no hay otro registro existente en la
     * misma fecha
     */
    public void testCrearRegistroTiempo()
    {
        setupEscenario1( );
        Fecha fechaEntrada= new Fecha( 2011, 02, 25, 10, 30 );
        try
        {
            usuario.crearRegistroTiempo( fechaEntrada );
            RegistroTiempo registro=usuario.darRegistroTiempo( fechaEntrada );
            String idRegistro=registro.darId( );
            assertEquals( "El ID del registro no se asignó correctamente" ,usuario.darId( )+""+0, idRegistro );
            assertEquals("El registro no se agregó al vector de registros",1,usuario.darNumRegistrosTiempo( ));
        }
        catch( RegistroTiempoExisteException e )
        {
            fail("No debe haber conflicto de horarios al agregar el primer registro de tiempo");
        }
        
    }
    
    
    /**
     * Verifica el caso en que no existe un registro de tiempo en la fecha del nuevo registro
     */
    public void testExisteRegistroTiempo()
    {
        setupEscenario2( );
        Fecha fechaEntrada2=new Fecha( 2011, 02, 26, 11, 0 );
        boolean existe=usuario.existeRegistroTiempo( fechaEntrada2 );
        assertFalse( "No existe un registro en esa fecha", existe );
    }
    
    /**
     * Verifica el caso en que ya existe un registro de tiempo en la misma fecha del nuevo registro
     */
    public void testExisteRegistroTiempo2()
    {
        setupEscenario2( );
        Fecha fechaEntrada2=new Fecha( 2011, 2, 25, 11, 0 );
        boolean existe=usuario.existeRegistroTiempo( fechaEntrada2 );
        assertTrue( "Ya existe un registro en esa fecha", existe );
        
    }
    
    /**
     * Verifica que se retorne un registro de tiempo dado su fecha de entrada
     */
    public void testDarRegistroTiempo()
    {
        setupEscenario2( );
        Fecha fechaEntrada= new Fecha( 2011, 2, 25, 10, 30 );
        RegistroTiempo registro=usuario.darRegistroTiempo( fechaEntrada );
        assertNotNull( "El registro no fue encontrado", registro );
        assertEquals("El ID del usuario retornado no es el esperado",usuario.darId( )+""+0,registro.darId( ));
          
    }
    
    /**
     * Verifica que se retorne correctamente el anterior registro de tiempo
     */
    public void testDarAnteriorRegistroTiempo()
    {
        setupEscenario2( );
        RegistroTiempo registro=usuario.darAnteriorRegistroTiempo( usuario.darId( )+""+1 );
        Fecha tiempoEntrada=registro.darTiempoEntrada( );
        assertEquals("El registro retornado no es el esperado", 2011,tiempoEntrada.darAnio( ));
        assertEquals("El registro retornado no es el esperado", 2,tiempoEntrada.darMes( ));
        assertEquals("El registro retornado no es el esperado", 25,tiempoEntrada.darDia( )); 
        assertEquals("El registro retornado no es el esperado", 10, tiempoEntrada.darHora( ));
        assertEquals("El registro retornado no es el esperado", 30, tiempoEntrada.darMinutos( ));
    }
    
    /**
     * Verifica que se retorne correctamente el siguiente registro de tiempo
     */
    public void testDarSiguienteRegistroTiempo()
    {
        setupEscenario2( );
        RegistroTiempo registro=usuario.darSiguienteRegistroTiempo( usuario.darId( )+""+0 );
        Fecha tiempoEntrada=registro.darTiempoEntrada( );
        assertEquals("El registro retornado no es el esperado", 2011,tiempoEntrada.darAnio( ));
        assertEquals("El registro retornado no es el esperado", 3,tiempoEntrada.darMes( ));
        assertEquals("El registro retornado no es el esperado", 12,tiempoEntrada.darDia( )); 
        assertEquals("El registro retornado no es el esperado", 23, tiempoEntrada.darHora( ));
        assertEquals("El registro retornado no es el esperado", 0, tiempoEntrada.darMinutos( ));
    }
    
    /**
     * Verifica que se retorne correctamente el último registro de tiempo
     */
    public void testDarUltimoRegistroTiempo()
    {
        setupEscenario2( );
        RegistroTiempo registro=usuario.darUltimoRegistroTiempo( );
        Fecha fechaEntrada=registro.darTiempoEntrada( );
        assertEquals("El registro retornado no es el esperado", 2011,fechaEntrada.darAnio( ));
        assertEquals("El registro retornado no es el esperado", 3,fechaEntrada.darMes( ));
        assertEquals("El registro retornado no es el esperado", 12,fechaEntrada.darDia( )); 
    }
    
    /**
     * Verifica que se retorne correctamente la suscripción anterior de un usuario
     */
    public void testDarAnteriorSuscripcion()
    {
      setupEscenario3();
      Suscripcion suscripcion=usuario.darAnteriorSuscripcion( usuario.darId( )+""+1 );
      String tipoSuscripcion=suscripcion.darTipoSuscripcion( );
      assertEquals( "El tipo de la suscripción anterior no es el esperado", Suscripcion.SUSCRIPCION_AMATEUR ,tipoSuscripcion );
      String fechaInicio=suscripcion.darFechaInicioConFormato( );
      assertEquals("La fecha de inicio de la suscripción anterior no es la esperada","2-1-2011 0:0 hh", fechaInicio);
    }
    
    
    /**
     * Verifica que se retorne correctamente la suscripción siguiente de un usuario
     */
    public void testDarSiguienteSuscripcion()
    {
        
        setupEscenario3( );
        Suscripcion suscripcion=usuario.darSiguienteSuscripcion( usuario.darId( )+""+0 );
        String tipoSuscripcion=suscripcion.darTipoSuscripcion( );
        assertEquals( "El tipo de la suscripción siguiente no es el esperado", Suscripcion.SUSCRIPCION_REGULAR ,tipoSuscripcion );
        String fechaInicio=suscripcion.darFechaInicioConFormato( );
        assertEquals("La fecha de inicio de la suscripción anterior no es la esperada","7-1-2011 0:0 hh", fechaInicio);
    }
    
    /**
     * Verifica que se retorne correctamente la posición de una suscripción
     */
    public void testDarPosicionSuscripcion()
    {
        Fecha fechaInicio1= new Fecha( 2011, 1, 2, 0, 0 );
        Fecha fechaInicio2= new Fecha( 2011, 1, 7, 0, 0 );
        Suscripcion suscripcion1=new Suscripcion( "11", fechaInicio1, Suscripcion.SUSCRIPCION_AMATEUR );
        Suscripcion suscripcion2=new Suscripcion( "11", fechaInicio2, Suscripcion.SUSCRIPCION_REGULAR );
        setupEscenario3( );
        int pos=usuario.darPosicionSuscripcion( usuario.darId( )+""+1 );
        assertEquals( "La posición de la suscripción no es la esperada",1, pos );
    }
    
    /**
     * Verifica que el número de suscripciones sea correcto
     */
    public void testDarNumSuscripciones()
    {
        setupEscenario3( );
        int numSuscripciones=usuario.darNumSuscripciones( );
        assertEquals("El número de suscripciones es incorrecto" ,2, numSuscripciones );
    }
    
    /**
     * Verifica que el número de registros de tiempo sea correcto
     */
    public void testDarNumRegistrosTiempo()
    {
        setupEscenario2( );
        int numRegistros=usuario.darNumRegistrosTiempo( );
        assertEquals("El número de suscripciones es incorrecto" ,2, numRegistros );
    }
    
    /**
     * Verifica que se retorne correctamente la última suscripción
     */
    public void testDarUltimaSuscripcion()
    {
        setupEscenario3( );
        Suscripcion suscripcion=usuario.darUltimaSuscripcion( );
        String elId=suscripcion.darId( );
        assertEquals( "La última suscripción no es la esperada",usuario.darId( )+""+1, elId );
    }

    

}
