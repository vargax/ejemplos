/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n4_procesadoraCafe
 * Autor: Catalina Rodríguez - 01-sep-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.procesadoraCafe.interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import uniandes.cupi2.procesadoraCafe.mundo.Cliente;
import uniandes.cupi2.procesadoraCafe.mundo.ProcesadoraCafe;
import uniandes.cupi2.procesadoraCafe.mundo.Producto;
import uniandes.cupi2.procesadoraCafe.mundo.Proveedor;


/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazProcesadoraCafe extends JFrame
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private ProcesadoraCafe procesadoraCafe;

    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------

    /**
     * Contenedor de paneles (por tabs)
     */
    private JTabbedPane contenedor;

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;
    
    /**
     * Panel con la imagen del encabezado
     */
    private PanelImagen panelImagen;
    
    /**
     * Panel con la información de los productos
     */
    private PanelProductos panelProductos;
    
    /**
     * Panel con la información de los clientes
     */
    private PanelClientes panelClientes;
    
    /**
     * Panel con la información de los proveedores
     */
    private PanelProveedores panelProveedores;
    
    /**
     * Panel con la información general de la Procesadora de Café
     */
    private PanelGeneral panelGeneral;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación <br>
     * <b>post: </b> Se creó la ventana de la aplicación, se han inicializado los paneles
     */
    public InterfazProcesadoraCafe()
    {
        // Crea la clase principal
        procesadoraCafe = new ProcesadoraCafe( 5000000.0 );
        
        // Construye la forma
        setLayout( new BorderLayout( ) );
        setSize( 675, 470 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle("Procesadora de Café");
        setResizable(false);
        
        //Creación de los paneles aquí
        panelImagen = new PanelImagen();
        add( panelImagen, BorderLayout.NORTH );
        
        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );
        
        panelProductos = new PanelProductos(this);
        panelClientes = new PanelClientes(this);
        panelProveedores = new PanelProveedores(this);
        panelGeneral = new PanelGeneral(this);
        
        // Creación de los tabs
        contenedor = new JTabbedPane( );
        contenedor.addTab( "Información General", null, panelGeneral, null );
        contenedor.addTab( "Productos", null, panelProductos, null );
        contenedor.addTab( "Clientes", null, panelClientes, null );
        contenedor.addTab( "Proveedores", null, panelProveedores, null );
        add( contenedor, java.awt.BorderLayout.CENTER );
        
        //Centrar la ventana
        setLocationRelativeTo(null);
        panelGeneral.actualizar();        
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Método que valida y agrega un nuevo producto
     * @param nombre - Es el nombre del nuevo producto - nombre != null y nombre != ""
	 * @param aroma - Es el aroma del nuevo producto - aroma != null y aroma != ""
	 * @param acidez - Es la acidez del nuevo producto - acidez != null y acidez != ""
	 * @param cuerpo - Es el cuerpo del nuevo producto - cuerpo != null y cuerpo != ""
	 * @param precio - Es el precio por kilo del nuevo producto - precio > 0
	 * @param conversion - Es la cantidad de kilos insumo necesarios para obtener 1 kilo del nuevo producto - conversion > 0
	 */
    public void agregarProducto(String nombre, String aroma, String acidez, String cuerpo, double precio, double conversion)
    {
    	try 
    	{
			procesadoraCafe.agregarProducto(nombre, aroma, acidez, cuerpo, precio, conversion);
			actualizarProductos();
			panelGeneral.actualizar();
			JOptionPane.showMessageDialog(this, "Producto agregado", "Agregar Producto", JOptionPane.INFORMATION_MESSAGE);
		} 
    	catch (Exception e) 
    	{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    /**
     * Método que valida y elimina producto
     * @param nombre - Es el nombre del producto - nombre != null y nombre != ""
	 */
    public void eliminarProducto(String nombre)
    {
    	try 
    	{
			procesadoraCafe.eliminarProducto(nombre);
			actualizarProductos();
			panelGeneral.actualizar();
			JOptionPane.showMessageDialog(this, "Producto eliminado", "Eliminar Producto", JOptionPane.INFORMATION_MESSAGE);
		} 
    	catch (Exception e) 
    	{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    /**
     * Método que valida y agrega un nuevo cliente
     * @param nombre Es el nombre del nuevo cliente. nombre != null y nombre != “”
     * @param nit Es el NIT del nuevo cliente. nit != null, nit != ""
     * @param telefono Es el teléfono de contacto del nuevo cliente. telefono != null, telefono != “”
     */
    public void agregarCliente(String nombre, String nit, String telefono)
    {
    	try 
    	{
			procesadoraCafe.agregarCliente(nombre, nit, telefono);
			panelClientes.actualizarClientes(procesadoraCafe.darClientes());
			panelGeneral.actualizar();
			JOptionPane.showMessageDialog(this, "Cliente agregado", "Agregar Cliente", JOptionPane.INFORMATION_MESSAGE);
		} 
    	catch (Exception e) 
    	{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    /**
     * Método que valida y agrega un nuevo proveedor
     * @param nombre Es el nombre del nuevo proveedor. nombre != null y nombre != “”
     * @param cedula Es la cédula del nuevo proveedor. Número entero positivo
     * @param telefono Es el teléfono de contacto del nuevo proveedor. telefono != null, telefono != “”
     * @param origenCafe Es el departamento origen del café que se provee. origen != null, origen != “”
     * @param precioKilo Es el precio por kilo del café que se provee. precioKilo > 0
     */
    public void agregarProveedor(String nombre, int cedula, String telefono, String origenCafe, double precioKilo)
    {
    	try 
    	{
			procesadoraCafe.agregarProveedor(nombre, cedula, telefono, origenCafe, precioKilo);
			panelProveedores.actualizarProveedores(procesadoraCafe.darProveedores());
			panelGeneral.actualizar();
			JOptionPane.showMessageDialog(this, "Proveedor agregado", "Agregar Proveedor", JOptionPane.INFORMATION_MESSAGE);
		} 
    	catch (Exception e) 
    	{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
    }
        
    /**
     * Método que valida y permite producir cierto producto
     */
    public void producirCafe()
    {
    	try 
    	{
    		Object[] productos = procesadoraCafe.darProductos().toArray();
    		if(productos.length > 0)
    		{
    			String nombre = ((Producto) JOptionPane.showInputDialog(this, "Producto: ", "Producir Café", JOptionPane.QUESTION_MESSAGE, null, productos, productos[0])).darNombre();
        		String cantidadS = JOptionPane.showInputDialog(this, "Cantidad de kilos a producir: ", "Producir Café", JOptionPane.QUESTION_MESSAGE);
    			double cantidad = Double.parseDouble(cantidadS);
        		
    			if(cantidad>0)
    			{
    				procesadoraCafe.producir(nombre, cantidad);
    				actualizarProductos();
    				panelGeneral.actualizar();
        			JOptionPane.showMessageDialog(this, "Se han producido " + cantidad + " kilos de " + nombre, "Producir Café", JOptionPane.INFORMATION_MESSAGE);
    			}
    			else
    			{
    				JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
    	    	}
        	}
    		else
    		{
    			JOptionPane.showMessageDialog(this, "No existe ningún producto", "Producir Café", JOptionPane.INFORMATION_MESSAGE);
    		}
       	} 
    	catch(NumberFormatException e)
    	{
    		JOptionPane.showMessageDialog(this, "La cantidad debe ser un valor numérico", "Error", JOptionPane.ERROR_MESSAGE);
    	}
    	catch (Exception e) 
    	{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    /**
     * Método que valida y permite vender un producto
     */
    public void venderCafe()
    {
    	try 
    	{
    		Object[] productos = procesadoraCafe.darProductos().toArray();
    		Object[] clientes = procesadoraCafe.darClientes().toArray();
    		if(productos.length > 0)
    		{
    			if(clientes.length > 0)
    			{
        			String nombre = ((Producto) JOptionPane.showInputDialog(this, "Producto: ", "Vender Café", JOptionPane.QUESTION_MESSAGE, null, productos, productos[0])).darNombre();
            		String nitCliente = ((Cliente) JOptionPane.showInputDialog(this, "Cliente: ", "Vender Café", JOptionPane.QUESTION_MESSAGE, null, clientes, clientes[0])).darNit();
        			String cantidadS = JOptionPane.showInputDialog(this, "Cantidad de kilos vendidos: ", "Vender Café", JOptionPane.QUESTION_MESSAGE);
        			double cantidad = Double.parseDouble(cantidadS);
            		
        			if(cantidad>0)
        			{		
    					procesadoraCafe.vender(nombre, nitCliente, cantidad);
    					actualizarProductos();
    					panelGeneral.actualizar();
    					panelClientes.actualizarClientes(procesadoraCafe.darClientes());
    					JOptionPane.showMessageDialog(this, "Se han vendido " + cantidad + " kilos de " + nombre, "Vender Café", JOptionPane.INFORMATION_MESSAGE);
        			}
        			else
        			{
        				JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
        	    	}
    			}
    			else
    			{
    	    		JOptionPane.showMessageDialog(this, "No existe ningún cliente", "Vender Café", JOptionPane.INFORMATION_MESSAGE);
    			}
        	}
    		else
    		{
    			JOptionPane.showMessageDialog(this, "No existe ningún producto", "Vender Café", JOptionPane.INFORMATION_MESSAGE);
    	
    		}
    	}
    	catch(NumberFormatException e)
    	{
    		JOptionPane.showMessageDialog(this, "La cantidad debe ser un valor numérico", "Error", JOptionPane.ERROR_MESSAGE);
    	}
    	catch (Exception e) 
    	{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    /**
     * Método que valida y permite comprar insumos a un proveedor
     */
    public void comprarInsumos()
    {
    	try 
    	{
    		Object[] proveedores = procesadoraCafe.darProveedores().toArray();
    		if(proveedores.length > 0)
    		{
    			int cedula = ((Proveedor) JOptionPane.showInputDialog(this, "Proveedor: ", "Comprar Insumos", JOptionPane.QUESTION_MESSAGE, null, proveedores, proveedores[0])).darCedula();
        		String cantidadS = JOptionPane.showInputDialog(this, "Cantidad de kilos comprados: ", "Comprar Insumos", JOptionPane.QUESTION_MESSAGE);
    			double cantidad = Double.parseDouble(cantidadS);
        		
    			if(cantidad>0)
    			{
    				procesadoraCafe.comprarInsumo(cedula, cantidad);
    				panelGeneral.actualizar();
        			JOptionPane.showMessageDialog(this, "Se han comprado " + cantidad + " kilos", "Comprar Insumos", JOptionPane.INFORMATION_MESSAGE);
    			}
    			else
    			{
    				JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
    	    	}
        	}
    		else
    		{
    			JOptionPane.showMessageDialog(this, "No existe ningún proveedor", "Comprar Insumos", JOptionPane.INFORMATION_MESSAGE);
    		}
       	} 
    	catch(NumberFormatException e)
    	{
    		JOptionPane.showMessageDialog(this, "La cantidad debe ser un valor numérico", "Error", JOptionPane.ERROR_MESSAGE);
    	}
    	catch (Exception e) 
    	{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
    }
    
    /**
     * Método que busca el cliente con mayor cantidad de kilos de vendidos 
     * @return Nombre del cliente más fiel
     */
    public String darClienteFiel()
    {
    	String respuesta = procesadoraCafe.darClienteFiel();
    	if(respuesta == null)
    	{
    		respuesta = "No existen clientes";
    	}
    	return respuesta;
    }
   
    /**
     * Método que busca el proveedor que ofrece el precio más barato de café tostado
     * @return El nombre del proveedor que ofrece el precio más barato
     */
    public String darProveedorMasBarato()
    {
    	String respuesta = procesadoraCafe.darProveedorMasBarato();
    	if(respuesta == null)
    	{
    		respuesta = "No existen proveedores";
    	}
    	return respuesta;
    }
    
    /**
     * Método que busca el producto con mayor disponibilidad en la Procesadora
     * @return Nombre del producto con mayor disponibilidad
     */
    public String darProductoMayorDisponibilidad()
    {
    	String respuesta = procesadoraCafe.darProductoMayorDisponibilidad();
    	if(respuesta == null)
        {
        	respuesta = "No existen productos";
        }
        return respuesta;
    }
    
    /**
     * Método que actualiza la información de los productos
     */
	public void actualizarProductos() 
	{
		panelProductos.actualizarProductos(procesadoraCafe.darProductos());
	}
	
	/**
	 * Método que devuelve el dinero en caja de la Procesadora
	 * @return Dinero en caja
	 */
	public double darDinero() 
	{
		return procesadoraCafe.darDineroEnCaja();
	}
	
	/**
	 * Método que devuelve la cantidad de insumos disponible
	 * @return Kilos de insumo disponible
	 */
	public double darInsumoDisponible()
	{
		return procesadoraCafe.darInsumoDisponible();
	}	
    
	
    //-----------------------------------------------------------------
    // Puntos de Extensión
    //-----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = procesadoraCafe.metodo1();
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = procesadoraCafe.metodo2();
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

        InterfazProcesadoraCafe interfaz = new InterfazProcesadoraCafe();
        interfaz.setVisible( true );
    }
}