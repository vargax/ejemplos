package uniandes.cupi2.componenteCompartir.mundo;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Observer;

import javax.mail.MessagingException;

import uniandes.cupi2.componenteContactos.mundo.Contacto;
import uniandes.cupi2.cupIphone.componentes.excepciones.EjecucionException;
import uniandes.cupi2.mailer.EnvioException;

import estructurasDatos.IListaEncadenada;

public interface IComponenteCompartir {
	/**
	 * RF1: Conectarse a otro cupiPhone
	 * @param ip: La ip del cupiPhone
	 * @param puerto: El puerto del cupiPhone
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public void conectar(String ip, int puerto) throws UnknownHostException, IOException;
	/**
	 * RF2: Descargar un archivo seleccionado
	 * @param nombreArchivo: El nombre del archivo a descargar
	 * @throws Exception 
	 */
	public void descargar(String nombreArchivo) throws Exception;
	/**
	 * RF3: Buscar por nombre
	 * @param criterioBusqueda: El nombre del archivo que se está buscando
	 * @return Una lista que contiene los nombres de los archivos coincidentes
	 */
	public IListaEncadenada<String> buscarArchivo(String criterioBusqueda);
	/**
	 * RF4: Ver usuarios conectados
	 * @return Una lista con el nombre de los usuarios conectados
	 */
	public IListaEncadenada<String> usuariosConectados();
	/**
	 * RF4.1: Desconectar un usuario
	 * @param nombreUsuario: El nombre del usuario a desconectar
	 */
	public void desconectarUsuario(String nombreUsuario);
	/**
	 * RF5: Estadísticas Archivo
	 * @param nombreArchivo: El nombre del archivo del cual se requieren las estadísticas
	 * @return Una lista con las estadísticas del archivo
	 */
	public IListaEncadenada<String> estadisticasArchivo(String nombreArchivo);
	/**
	 * RF6: Enviar archivo por correo electrónico
	 * @param nombreArchivo: El nombre del archivo a enviar
	 * @throws Exception 
	 */
	public void enviarCorreo(String nombreArchivo, String email) throws MessagingException;
	/**
	 * RF6.1: Recuperar direcciones de correo del componente contactos
	 * @throws Exception 
	 */
	public IListaEncadenada<Contacto> darContactos() throws Exception;
	//-----------------------------------------------------------------
    // Métodos Observador
    //-----------------------------------------------------------------
	/**
	 * Dar archivos locales
	 */
	public IListaEncadenada<String> getLocales();
	/**
	 * Dar archivos remotos
	 */
	public IListaEncadenada<String> getRemotos();
	/**
	 * Dar descargas actuales
	 */
	public float[] getDescargas();
	//-----------------------------------------------------------------
    // Métodos auxiliares
    //-----------------------------------------------------------------
	/**
	 * Entrega las configuraciones actuales de cupiShare
	 * @return Arreglo de Strings. String[0] El nombre del cliente. String[1] el puerto de escucha del servidor 
	 */
	public String[] darConfiguracion();
	/**
	 * Define los nuevos parámetros de configuración para el cupiPhone
	 * @param nuevoNombre: El nombre del cliente que se le enviará al servidor
	 * @param nuevoPuerto: El puerto de escucha del socket
	 */
	public void definirConfiguracion(String nuevoNombre, String nuevoPuerto);
}
