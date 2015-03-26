package co.edu.uniandes.arquesoft.exp3.fsdb.plataforma.persistencia;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.persistencia.PersistenciaException;
import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.persistencia.Registro;

public class BaseDatos implements IBaseDatos {
	// -------------------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------------------
	private final String TABLA_DATOS = "datos";
	// -------------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------------
	private Context contexto;
	// -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------
	public BaseDatos(Context contexto) {
		this.contexto = contexto;
	}
	// -------------------------------------------------------------------------
    // Métodos Interfaz
    // -------------------------------------------------------------------------
	@Override
	public void insertarRegistro(Registro registro) throws PersistenciaException {
		
		ContentValues cv = new ContentValues();
		cv.put("fecha_hora",registro.getFechaHora());
		cv.put("dispositivo",registro.getDispositivo());
		cv.put("registro",registro.getDato());
		cv.put("estado",registro.getEstado());
		
		SQLiteDatabase db = (new SQLite(this.contexto)).getWritableDatabase();
		if (db.insert(this.TABLA_DATOS, null, cv) == -1) 
			throw new PersistenciaException("Ocurrio un error al insertar el registro "+registro.getDato()
					+ " del dispositivo "+registro.getDispositivo());
		db.close();
	}
	
	@Override
	public ArrayList<Registro> registrosEnviar() {
		String consulta = "SELECT * FROM "+this.TABLA_DATOS+" WHERE estado = '"+Registro.NUEVO+"'";
		SQLiteDatabase db = (new SQLite(this.contexto)).getWritableDatabase();
		Cursor c = db.rawQuery(consulta, null);
		ArrayList<Registro> respuesta = new ArrayList<Registro>(); 
		if(c.moveToFirst()) {
			do {
				Registro tmpRegistro = new Registro(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3), c.getString(4));
				respuesta.add(tmpRegistro);
			} while (c.moveToNext());
		}
		return respuesta;
	}
	
	@Override
	public int marcarEntregados() {
		ContentValues cv = new ContentValues();
		cv.put("estado",Registro.ENTREGADO);
		
		SQLiteDatabase db = (new SQLite(this.contexto)).getWritableDatabase();
		int respuesta = db.update(this.TABLA_DATOS, cv, "estado='"+Registro.ENVIADO+"'", null);
		db.close();
		return respuesta;
	}
}
