package uniandes.cupi2.bodyCupi2.test;


import junit.framework.TestCase;
import uniandes.cupi2.bodyCupi2.mundo.Fecha;
import uniandes.cupi2.bodyCupi2.mundo.Suscripcion;

/**
 * Clase para probar los métodos de la clase Suscripción   
 *
 */
public class SuscripcionTest extends TestCase
{
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Suscripcion suscripcion;
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Construye una nueva suscripción de tipo NOMBRE_SUSCRIPCION_CORTA
     */
    public void setupEscenario1()
    {
        String tipoSuscripcion=Suscripcion.SUSCRIPCION_AMATEUR;
        Fecha laFechaApertura=new Fecha(2011,2,22,0,0);
        suscripcion=new Suscripcion( "11", laFechaApertura, tipoSuscripcion );
    }
    
    /**
     * Verifica que al crear una suscripción el tipo asignado sea correcto
     */
    public void testSuscripcion()
    {
        setupEscenario1();
        String tipo=suscripcion.darTipoSuscripcion( );
        assertEquals("El tipo de suscripción no es el esperado",Suscripcion.SUSCRIPCION_AMATEUR, tipo );
        int duracion=suscripcion.darDuracion();
        assertEquals("La duración de la suscripción no es la esperada",Suscripcion.NUM_DIAS_AMATEUR,duracion);
        assertEquals("El ID no se asignó correctamente","11",suscripcion.darId( ));

    }
        
    /**
     * Valida que se calcule correctamente si la suscripción está vencida
     */
    public void testDarEstado()
    {
        setupEscenario1();
        String estado=suscripcion.darEstado( );
        assertEquals( "La suscripción debería estar vencida", Suscripcion.ESTADO_VENCIDA,estado );   
    }
    
    /**
     * Valida que se retorne la fecha de inicio de la suscripción con un formato apropiado
     */
    public void testDarFechaInicioConFormato()
    {
        setupEscenario1( );
        new Fecha(2011,2,22,0,0);
        String fechaFormato=suscripcion.darFechaInicioConFormato( );
        assertEquals( "La fecha no se está retornando en el formato esperado" ,"22-2-2011 0:0 hh", fechaFormato );
    }
    

}
