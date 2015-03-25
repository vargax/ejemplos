package uniandes.cupi2.bodyCupi2.test;


import junit.framework.TestCase;
import uniandes.cupi2.bodyCupi2.mundo.Fecha;
import uniandes.cupi2.bodyCupi2.mundo.RegistroTiempo;

/**
 * Clase para probar los métodos de la clase RegistroTiempo
 */
public class RegistroTiempoTest extends TestCase
{
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private RegistroTiempo registroTiempo;
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Constuye un nuevo registro de tiempo
     */
    public void setupEscenario1()
    {
        Fecha fechaEntrada=new Fecha(2011,2,22,10,0);
        registroTiempo=new RegistroTiempo(fechaEntrada);
    }
    
    
    /**
     * Construye un registro de tiempo con un registro de entrada y un registro de salida
     */
    public void setupEscenario2()
    {
        Fecha fechaEntrada=new Fecha(2011,2,22,10,0);
        Fecha fechaSalida=new Fecha(2011,2,22,19,30);
        registroTiempo=new RegistroTiempo(fechaEntrada);
        registroTiempo.agregarTiempoSalida( fechaSalida );
    }
    
    /**
     * Valida que al crear un registro de tiempo de asigne el atributo tiempoEntrada
     */
    public void testRegistroTiempo()
    {
        setupEscenario1( );
        assertNotNull( "No se asigno el atributo tiempoEntrada", registroTiempo.darTiempoEntrada( ));
    }
    
    /**
     * Valida que se asigne correctamente la fecha de salida al registro de tiempo
     */
    public void testAgregarHoraSalida()
    {
        setupEscenario1( );
        Fecha fechaSalida=new Fecha(2011,2,22,19,30);
        registroTiempo.agregarTiempoSalida( fechaSalida );
        Fecha fechaSalidaGuardada=registroTiempo.darTiempoSalida( );
        assertEquals( "La hora de salida no se asignó al atributo correcto",2011, fechaSalidaGuardada.darAnio( ) );
        assertEquals( "La hora de salida no se asignó al atributo correcto",2, fechaSalidaGuardada.darMes( ) );
        assertEquals( "La hora de salida no se asignó al atributo correcto",22, fechaSalidaGuardada.darDia( ) );
        assertEquals( "La hora de salida no se asignó al atributo correcto",19, fechaSalidaGuardada.darHora( ) );
        assertEquals( "La hora de salida no se asignó al atributo correcto",30, fechaSalidaGuardada.darMinutos( ) );    
    }
    
    /**
     * Valida que la fecha de entrada sea correcta
     */
    public void testDarTiempoEntrada()
    {
        setupEscenario2( );
        Fecha registroEntrada=registroTiempo.darTiempoEntrada( );
        assertEquals( "El año no es correcto",2011, registroEntrada.darAnio( ) );
        assertEquals( "El mes no es correcto",2, registroEntrada.darMes( ) );
        assertEquals( "El dia no es correcto",22, registroEntrada.darDia( ) );
    }
    
    
    /**
     * Valida que la fecha de salida sea correcta
     */
    public void testDarTiempoSalida()
    {
        setupEscenario2( );
        Fecha registroSalida=registroTiempo.darTiempoSalida( ); 
        assertEquals( "El año no es correcto",2011, registroSalida.darAnio( ) );
        assertEquals( "El mes no es correcto",2, registroSalida.darMes( ) );
        assertEquals( "El dia no es correcto",22, registroSalida.darDia( ) );
        
    }
    
    /**
     * Valida que exista un registro de salida para el registro de tiempo
     */
    public void testExisteTiempoSalida()
    {
        setupEscenario2( );
        boolean existeRegistroSalida=registroTiempo.existeTiempoSalida( );
        assertEquals( "", existeRegistroSalida, true);
    }
    
    /**
     * Valida que se asigne correctamente el atributo ID de un registro de tiempo
     */
    public void testAsignarId()
    {
    	setupEscenario1();
    	registroTiempo.asignarId("1231");
    	assertNotNull("No se asignó el atributo ID",registroTiempo.darId());
    }
    
    /**
     * Valida que se retorne correctamente el atributo ID
     */
    public void testDarId()
    {
    	setupEscenario1();
    	registroTiempo.asignarId("1231");
    	assertEquals("El atributo ID no se asignó correctamente","1231", registroTiempo.darId());
    }

}
