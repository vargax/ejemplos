/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n1_estacionServicio
 * Autor: Catalina Rodriguez - 02-ago-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.estacionServicio.mundo;

/**
 *  Clase que representa la estación de servicio
 */
public class EstacionServicio
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Surtidor número 1
	 */
	private Surtidor surtidor1;
	
	/**
	 * Surtidor número 2
	 */
	private Surtidor surtidor2;
	
	/**
	 * Surtidor número 3
	 */
	private Surtidor surtidor3;	
	
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Inicializa los surtidores de combustible de la estación de servicio <br>
     * <b>post: </b> Se inicializan tres surtidores
     */
    public void inicializar()
    {
    	//
    	//Inicializar el surtidor 1
    	surtidor1 = new Surtidor();
    	surtidor1.inicializar("Corriente", 7800);
    	
    	//
    	//Inicializar el surtidor 2
    	surtidor2 = new Surtidor();
    	surtidor2.inicializar("Extra", 9500);
    	
    	//
    	//Inicializar el surtidor 3
    	surtidor3 = new Surtidor();
    	surtidor3.inicializar("Diesel", 6700);
    }
    
    /**
     * Devuelve el surtidor 1
     * @return Surtidor 1
     */
    public Surtidor darSurtidor1()
    {
    	return surtidor1;
    }
    
    /**
     * Devuelve el surtidor 2
     * @return Surtidor 2
     */
    public Surtidor darSurtidor2()
    {
    	return surtidor2;
    }
    
    /**
     * Devuelve el surtidor 3
     * @return Surtidor 3
     */
    public Surtidor darSurtidor3()
    {
    	return surtidor3;
    }
    
    /**
     * Registra una nueva venta a vehículo particular del surtidor 1 <br>
     * <b>post: </b> Se registra la venta al surtidor 1
     * @param dinero Cantidad de dinero. dinero > 0.
     * @return Numero de galones vendidos
     */
    public double registrarVentaParticularSurtidor1( double dinero )
    {
    	return surtidor1.registarVentaParticular(dinero);
    }
    
    /**
     * Registra una nueva venta a vehículo particular del surtidor 2 <br>
     * <b>post: </b> Se registra la venta al surtidor 2
     * @param dinero Cantidad de dinero. dinero > 0.
     * @return Numero de galones vendidos
     */
    public double registrarVentaParticularSurtidor2( double dinero )
    {
    	return surtidor2.registarVentaParticular(dinero);
    }
    
    /**
     * Registra una nueva venta a vehículo particular del surtidor 3 <br>
     * <b>post: </b> Se registra la venta al surtidor 3
     * @param dinero Cantidad de dinero. dinero > 0.
     * @return Numero de galones vendidos
     */
    public double registrarVentaParticularSurtidor3( double dinero )
    {
    	return surtidor3.registarVentaParticular(dinero);
    }
    
    /**
     * Registra una nueva venta a vehículo de servicio público del surtidor 1
     * <b>post: </b> Se registra la venta del surtidor 1
     * @param dinero Cantidad de dinero. dinero>0
     * @return Numero de galones vendidos
     */
    public double registrarVentaPublicoSurtidor1( double dinero )
    {
    	return surtidor1.registrarVentaServicioPublico(dinero);
    }
    
    /**
     * Registra una nueva venta a vehículo de servicio público del surtidor 2
     * <b>post: </b> Se registra la venta del surtidor 2
     * @param dinero Cantidad de dinero. dinero>0
     * @return Numero de galones vendidos
     */
    public double registrarVentaPublicoSurtidor2( double dinero )
    {
    	return surtidor2.registrarVentaServicioPublico(dinero);
    }
    
    /**
     * Registra una nueva venta a vehículo de servicio público del surtidor 3
     * <b>post: </b> Se registra la venta del surtidor 3
     * @param dinero Cantidad de dinero. dinero>0
     * @return Numero de galones vendidos
     */
    public double registrarVentaPublicoSurtidor3( double dinero )
    {
    	return surtidor3.registrarVentaServicioPublico(dinero);
    }
    
    /**
     * Devuelve el total de galones vendidos por los tres surtidores
     * @return total de galones de los tres surtidores
     */
    public double darTotalGalones( )
    {
    	return surtidor1.darNumeroGalonesVendidos() + surtidor2.darNumeroGalonesVendidos() + surtidor3.darNumeroGalonesVendidos(); 
    }

    /**
     * Devuelve el dinero total recaudado por las ventas realizadas
     * @return dinero recaudado de los tres surtidores
     */
    public double darTotalDineroRecaudado( )
    {
    	return surtidor1.darDineroRecaudado() + surtidor2.darDineroRecaudado() + surtidor3.darDineroRecaudado();
    }

    /**
     * Devuelve el costo promedio de un galón, según los galones vendidos y el dinero recaudado<br>
     * <b> pre: </b> darTotalGalones > 0
     * @return Costo promedio por galón.
     */
    public double darCostoPromedioGalon( )
    {
    	return darTotalDineroRecaudado()/darTotalGalones();
    }

    /**
     * Reinicia todos los surtidores
     */
    public void reiniciar( )
    {
    	surtidor1.reiniciar();
    	surtidor2.reiniciar();
    	surtidor3.reiniciar();
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