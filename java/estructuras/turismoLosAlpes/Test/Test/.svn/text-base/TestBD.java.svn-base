package Test;

import conexionBDdao.ConsultaDaoTest;
import junit.framework.TestCase;

public class TestBD extends TestCase {
	
	
	
	
	
	
	
	private ConsultaDaoTest dao;

	public void  escenario1(){
		
		
		dao=new ConsultaDaoTest();
		
		dao.inicializar("D:/ISIS/TI/LabTIenlasOrg1/caches/zonasis/turismoLosAlpes/data");
		
		
	}
	
	//----------------------------------------------------------------------------------------------------------
	//test tablas
	//----------------------------------------------------------------------------------------------------------
	public void testTablaUsuario(){
		escenario1();
	   try {
		assertEquals("no se cargo el usuario",true,dao.cargarUsuario());
		
	 } catch (Exception e) {
		// TODO Auto-generated catch block
		assertFalse(false);
	}
	
		
	   try {
		assertEquals(false,dao.cargarUsuariodoble());
	 } catch (Exception e) {
		// TODO Auto-generated catch block
		assertFalse(false);
	}
	
	}
	
	public void testTablaCiudad(){
		escenario1();
	   try {
		assertEquals("no se cargo la ciudad",true,dao.cargarCiudad());
		
	 } catch (Exception e) {
		// TODO Auto-generated catch block
		assertFalse(false);
	}
	
		
	   try {
		assertEquals(false,dao.cargarCiudaddoble());
	 } catch (Exception e) {
		// TODO Auto-generated catch block
		assertFalse(false);
	}
	
	}
	
	public void testTablavuelo(){
		escenario1();
	   try {
		assertEquals("no se cargo elvuelo",true,dao.cargarVuelo());
		
	 } catch (Exception e) {
		// TODO Auto-generated catch block
		assertFalse(false);
	}
	
		
	   try {
		assertEquals(false,dao.cargarVuelodoble());
	 } catch (Exception e) {
		// TODO Auto-generated catch block
		assertFalse(false);
	}
	
	}
	
	//----------------------------------------------------------------------------------------------------------
	//test tablas
	//----------------------------------------------------------------------------------------------------------

}
