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

package uniandes.cupi2.bodyCupi2.test;

import junit.framework.TestCase;
import uniandes.cupi2.bodyCupi2.excepciones.FormatoArchivoException;
import uniandes.cupi2.bodyCupi2.excepciones.HoraSalidaExistenteParaRegistroException;
import uniandes.cupi2.bodyCupi2.excepciones.PersistenciaException;
import uniandes.cupi2.bodyCupi2.excepciones.RegistroTiempoExisteException;
import uniandes.cupi2.bodyCupi2.excepciones.RegistroTiempoNoExisteException;
import uniandes.cupi2.bodyCupi2.excepciones.UsuarioExisteException;
import uniandes.cupi2.bodyCupi2.excepciones.UsuarioNoExisteException;
import uniandes.cupi2.bodyCupi2.mundo.BodyCupi2;
import uniandes.cupi2.bodyCupi2.mundo.Suscripcion;
import uniandes.cupi2.bodyCupi2.mundo.Usuario;

/**
 * Esta es la clase usada para verificar que los métodos de la clase BodyCupi2 estén correctamente implementados
 */
public class BodyCupi2Test extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private BodyCupi2 bodyCupi2;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Construye una nueva BodyCupi2 vacía
     *  
     */
    public void setupEscenario1( )
    {
        bodyCupi2 = new BodyCupi2( "");
    }
    
    /**
     * Construye una nueva BodyCupi2 con un usuario registrado
     */
    public void setupEscenario2()
    {
        setupEscenario1( );
        try
        {
            bodyCupi2.agregarUsuario( 7549234, "Pedro perez", 24, 2815633, Usuario.GENERO_MASCULINO, "rutafoto1.jpg", "Ninguna historia médica", Suscripcion.SUSCRIPCION_AMATEUR, 2011,2, 22, 0, 0 );
        }
        catch( UsuarioExisteException e )
        {
            fail("No debio fallar al guardar un usuario");
        }
    }
    
    /**
     * Construye una nueva BodyCupi2 con un usuario registrado.
     * A dicho usuario se le agrega un registro de tiempo
     */
    public void setupEscenario3()
    {
        setupEscenario2( );
        try
        {
            bodyCupi2.registrarEntrada( 7549234, 2011, 2, 22, 8, 30 );  
        }
        catch (UsuarioNoExisteException ex)
        {
            fail("Para registrar una entrada primero debe existir el usuario ");
        }
        catch(RegistroTiempoExisteException ex2)
        {
            fail("Error al validar que no exista ya un registro de tiempo");
        }
    }
    
    /**
     * Construye una nueva BodyCupi2 con tres usuarios registrados.
     */
    public void setupEscenario4()
    {
        setupEscenario1( );
        try
        {
            bodyCupi2.agregarUsuario( 7549234, "Pedro perez", 24, 2815633, Usuario.GENERO_MASCULINO, "rutafoto1.jpg", "Ninguna historia médica", Suscripcion.SUSCRIPCION_AMATEUR, 2011,2, 22, 0, 0 );
            bodyCupi2.agregarUsuario( 1234567, "Camila paez", 21, 3119834, Usuario.GENERO_FEMENINO, "rutafoto2.jpg", "Ninguna historia médica", Suscripcion.SUSCRIPCION_REGULAR, 2011,2, 23, 0, 0 );
            bodyCupi2.agregarUsuario( 3466245, "Juan gomez",  25, 4235645, Usuario.GENERO_MASCULINO, "rutafoto3.jpg", "Ninguna historia médica", Suscripcion.SUSCRIPCION_MASTER, 2011,2, 24, 0, 0 );
        }
        catch( UsuarioExisteException e )
        {
            fail("Error al guardar un usuario");
        }
        
        
    }
    
    /**
     * Prueba 1
     */
    public void testBodyCupi2( )
    {
        setupEscenario1( );

    }
    
    /**
     * Valida que se pueda guardar un usuario
     * Valida que no se pueda guardar un usuario si no existe otro con el mismo ID
     */
    public void testAgregarUsuario()
    {
        setupEscenario1( );
        try
        {
        bodyCupi2.agregarUsuario( 7549234, "Pedro perez", 24, 2815633, Usuario.GENERO_MASCULINO, "rutafoto1.jpg", "Ninguna historia médica", Suscripcion.SUSCRIPCION_AMATEUR, 2011,2, 22, 0, 0 );
        Usuario usuario = bodyCupi2.darUsuario( 7549234 );
        //Valida que se haya guardado el usuario
        assertNotNull( "El usuario no se creó o no fue agregado a la lista de usuarios",usuario );
        }
        catch (UsuarioExisteException ex)
        {
            fail("No debio fallar al guardar un usuario");
        }
        //Valida que no se pueda guardar dos usuarios con el mismo ID
        try
        {
            bodyCupi2.agregarUsuario( 7549234, "Pedro perez", 24, 2815633, Usuario.GENERO_MASCULINO, "rutafoto1.jpg", "Ninguna historia médica", Suscripcion.SUSCRIPCION_AMATEUR, 2011,2, 22, 0, 0 );
            fail("Debió lanzar una excepción al guardar un usuario con un ID ya registrado");
        }
        catch (UsuarioExisteException ex2)
        {
            
        }
        
        
    }
    
    /**
     * Valida que se pueda crear un registro de entrada
     */
    public void testRegistrarEntrada()
    {
        setupEscenario2( );
        try
        {
            bodyCupi2.registrarEntrada( 7549234, 2011, 2, 22, 8, 30 );
            
        }
        catch (UsuarioNoExisteException ex)
        {
            fail("Para registrar una entrada primero debe existir el usuario ");
        }
        catch(RegistroTiempoExisteException ex2)
        {
            fail("Error al validar que no exista ya un registro de tiempo");
        }
        
  
    }
    
    /**
     * Valida que no se pueda crear un registro de entrada si existe ya un registro en la misma fecha
     */
    public void testRegistrarEntrada2()
    {
        setupEscenario2( );
        try
        {
            bodyCupi2.registrarEntrada( 7549234, 2011, 2, 22, 8, 30 );
            bodyCupi2.registrarEntrada( 7549234, 2011, 2, 22, 10, 0 );
            fail("No debe permitir crear un registro de tiempo si ya hay uno en la misma fecha");
            
        }
        catch (UsuarioNoExisteException ex)
        {
            fail("Para registrar una entrada primero debe existir el usuario ");
        }
        catch(RegistroTiempoExisteException ex2)
        {
           //Debe entrar acá 
        }
    }
    
    /**
     * Validar que se pueda crear un registro de salida
     */
    public void testRegistrarSalida()
    {
        setupEscenario3( );
        try
        {
            bodyCupi2.registrarSalida( 7549234, 2011, 2, 22, 14, 50 );
        }
        catch( UsuarioNoExisteException e )
        {
            fail("Para registrar una entrada primero debe existir el usuario ");
        }
        catch( RegistroTiempoNoExisteException e )
        {
            fail("Para registrar un tiempo de salida ya debe existir un registro de tiempo");
        }
        catch( HoraSalidaExistenteParaRegistroException e )
        {
            fail("Error al validar que no exista una hora de salida para un registro de tiempo");
        }

    }
    
    /**
     * Valida que no se puedan crear dos registro de salida para un mismo registro de tiempo
     */
    public void testRegistrarSalida2()
    {
        setupEscenario3( );
            try
            {
                bodyCupi2.registrarSalida( 7549234, 2011, 2, 22, 14, 50 );
                bodyCupi2.registrarSalida( 7549234, 2011, 2, 22, 15, 0 );
                fail("No se debe permitir crear dos registros de salida para la misma fecha");
            }
            catch( UsuarioNoExisteException e )
            {
                fail("Para registrar una entrada primero debe existir el usuario ");
            }
            catch( RegistroTiempoNoExisteException e )
            {
                fail("Para registrar un tiempo de salida ya debe existir un registro de tiempo");
            }
            catch( HoraSalidaExistenteParaRegistroException e )
            {
                //Debe entra acá
            }
            
      
    }
    
    
    /**
     * Valida que el método funcione cuando existe el usuario buscado
     */
    public void testExisteUsuario()
    {
        setupEscenario2( );
        boolean existeUsuario=bodyCupi2.existeUsuario( 7549234 );
        assertTrue( "El usuario sí existe",existeUsuario ); 
    }
    
    /**
     * Valida que el método funcione cuando no existe el usuario buscado 
     */
    public void testExisteUsuario2()
    {
        setupEscenario2( );
        boolean existeUsuario=bodyCupi2.existeUsuario( 123 );
        assertFalse( "El usuario no existe",existeUsuario );
    }
    
    /**
     * Valida que el método existenUsuarios() funcione correctamente cuando hay usuarios y cuando no hay
     */
    public void testExistenUsuarios()
    {
        setupEscenario1( );
        boolean existenUsuarios=bodyCupi2.existenUsuarios( );
        assertFalse( "No deben existir usuarios", existenUsuarios );
        try
        {
            bodyCupi2.agregarUsuario( 7549234, "Pedro perez", 24, 2815633, Usuario.GENERO_MASCULINO, "rutafoto1.jpg", "Ninguna historia médica", Suscripcion.SUSCRIPCION_AMATEUR, 2011,2, 22, 0, 0 );
            existenUsuarios=bodyCupi2.existenUsuarios( );
            assertTrue( "Debe existir un usuario",existenUsuarios );
        }
        catch( UsuarioExisteException e )
        {
           fail("No deben existir usuarios");
        }
        
    }
    
    /**
     * Valida que el usuario inicial sea el esperado
     */
    public void testDarUsuarioInicial()
    {
        setupEscenario4( );
        Usuario usuarioInicial=bodyCupi2.darUsuarioInicial( );
        int id=usuarioInicial.darId();
        assertEquals("El usuario inicial no es el esperado",7549234, id );   
    }
    
    /**
     * Verifica que el método retorne el usuario anterior
     */
    public void testDarUsuarioAnterior()
    {
        setupEscenario4( );
        Usuario usuarioAnterior=bodyCupi2.darUsuarioAnterior( 1234567 );
        int idUsuarioAnterior=usuarioAnterior.darId( );
        assertEquals( "El usuario anterior no es el esperado",7549234, idUsuarioAnterior ); 
    }

    /**
     * Verifica que el método retorne el siguiente usuario
     */
    public void testDarSiguienteUsuario()
    {
        setupEscenario4( );
        Usuario usuarioSiguiente=bodyCupi2.darSiguienteUsuario( 1234567 );
        int idUsuarioSiguiente=usuarioSiguiente.darId( );
        assertEquals("El usuario siguiente no es el esperado",3466245,idUsuarioSiguiente);
    }
    
    /**
     * Verifica que se cargue correctamente un archivo serializado
     */
    public void testCargarDatos()
    {
    	setupEscenario1();
    	try 
    	{
			bodyCupi2.cargarDatos("./test/data/pruebaCargarDatos.data");
			Usuario usuario1=bodyCupi2.darUsuario(123);
			Usuario usuario2=bodyCupi2.darUsuario(456);
			assertNotNull("En el archivo serializado hay un usuario con ID=123",usuario1);
			assertNotNull("En el archivo serializado hay un usuario con ID=456",usuario2);
			assertEquals("El número de registros de tiempo del primer usuario no es el esperado",2, usuario1.darNumRegistrosTiempo());
			assertEquals("El número de suscripciones del primer usuario no es el esperado",3,usuario1.darNumSuscripciones());
			
			
		} 
    	catch (PersistenciaException e)
    	{
			fail("error al cargar un archivo serializado");
		}
    }
    
    /**
     * Verifica que se puedan cargar los datos de usuarios desde un archivo de texto
     */
    public void testImportarUsuarios()
    {
    	setupEscenario1();
    	try
    	{
			bodyCupi2.importarUsuarios("./test/data/usuariosParaImportar.txt");
			Usuario usuario1=bodyCupi2.darUsuario(123);
			Usuario usuario2=bodyCupi2.darUsuario(456);
			assertNotNull("En el archivo serializado hay un usuario con ID=123",usuario1);
			assertNotNull("En el archivo serializado hay un usuario con ID=456",usuario2);
			assertEquals("El número de suscripciones del primer usuario no es el esperado",2,usuario1.darNumSuscripciones());
			assertEquals("El número de suscripciones del segundo usuario no es el esperado",1,usuario2.darNumSuscripciones());
			assertEquals("El número de registros de tiempo del primer usuario no es el esperado",2, usuario1.darNumRegistrosTiempo());
			assertEquals("El número de registros de tiempo del segundo usuario no es el esperado",0, usuario2.darNumRegistrosTiempo());
		} 
    	catch (FormatoArchivoException e) 
    	{
			fail("Error al cargar el archivo de propiedades");
		} 
    	
    }
    
   
    
    
    
}