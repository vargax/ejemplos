package uniandes.cupi2.bodyCupi2.test;

import junit.framework.TestCase;
import uniandes.cupi2.bodyCupi2.mundo.Fecha;

/**
 * Clase de pruebas para la clase Fecha
 */
public class FechaTest extends TestCase
{
	
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Fecha fecha;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Crea una nueva fecha
     */
    public void setupEscenario1()
    {
        fecha=new Fecha(2011,2,22,9,40);
    }
    
    /**
     * Valida que se asignen correctamente los atributos de la fecha
     */
    public void testFecha()
    {
        setupEscenario1( );
        int anio=fecha.darAnio( );
        int mes=fecha.darMes( );
        int dia=fecha.darDia( );
        int hora=fecha.darHora( );
        int minutos=fecha.darMinutos( );
        assertEquals( "El año no es correto", 2011 ,anio );
        assertEquals( "El mes no es correcto", 2,mes );
        assertEquals( "El día no es correcto", 22, dia );
        assertEquals( "La hora no es correcta", 9, hora );
        assertEquals( "Los minutos no son correctos", 40, minutos );
        
    }
    
    /**
     * Verifica que la diferencia entre dos fechas, en horas y minutos sea correcta
     */
    public void testDarDiferenciaHorasYMinutos()
    {
        setupEscenario1( );
        Fecha fecha2= new Fecha(2011,2, 22, 15,10);
        String diferenciaHorasConMinutos=fecha.darDiferenciaHorasYMinutos( fecha2 );
        assertEquals( "La diferencia de horas y minutos no es la correcta o no tiene el formato correcto", "5 horas y 30 minutos", diferenciaHorasConMinutos );
    }
    
    /**
     * Compara dos fechas para el caso en que ambas son iguales
     */
    public void testCompararFechas()
    {
        setupEscenario1( );
        Fecha fecha2=new Fecha(2011,2,22,9,40);
        int resultadoComparacion=fecha.compararFechas( fecha2 );
        assertEquals("Las fechas son iguales",0,resultadoComparacion);
    }
    
    /**
     * Compara dos fechas para el caso en que la recibida por parámetro sea posterior
     */
    public void testCompararFechas2()
    {
        setupEscenario1( );
        Fecha fecha2= new Fecha(2011,3, 22, 15,10);
        int resultadoComparacion=fecha.compararFechas( fecha2 );
        assertEquals("Las fecha recibida por parámetro es posterior",-1,resultadoComparacion);
    }
    
    /**
     * Compara dos fechas para el caso en que la recibida por parámetro sea anterior
     */
    public void testCompararFechas3()
    {
        setupEscenario1( );
        Fecha fecha2= new Fecha(2011,2, 21, 9,0);
        int resultadoComparacion=fecha.compararFechas( fecha2 );
        assertEquals("Las fecha recibida por parámetro es anterior",1,resultadoComparacion);
        
    }
    
    /**
     * Valida que se retorne la fecha en el formato correcto
     */
    public void testDarFechaConFormato()
    {
        setupEscenario1( );
        String fechaConFormato=fecha.darFechaConFormato( );
        System.out.println(fechaConFormato);
        String fechaConFormatoEsperado=fecha.darDia( )+"-"+fecha.darMes( )+"-"+fecha.darAnio( );
        assertEquals( "La fecha no se retornó en el formato esperado",fechaConFormatoEsperado, fechaConFormato );
    }
    
    

}
