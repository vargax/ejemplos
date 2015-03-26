package co.edu.uniandes.arquesoft.exp3.fsdb.plataforma.persistencia;

import java.util.ArrayList;

import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.persistencia.Registro;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {
	// -------------------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------------------
	private static final String NOMBRE_BASE_DATOS = "FSDB";
	private static final int VERSION_BASE_DATOS = 1;
	
	private static final String[][] SQL_TABLAS = {{"datos","CREATE TABLE datos(id INTEGER PRIMARY KEY AUTOINCREMENT, "
            										+ "fecha_hora INTEGER not null, "
										            + "dispositivo TEXT not null, "
										            + "registro TEXT not null,"
										            + "estado TEXT)"}};
	// -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------
	public SQLite(Context context) {
        super(context, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS);
    }
	
	// -------------------------------------------------------------------------
    // Métodos Superclase
    // -------------------------------------------------------------------------
	@Override
	public void onCreate(SQLiteDatabase db) {
		for(String[] sql : SQL_TABLAS) {
			db.execSQL(sql[1]);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		for(String[] sql : SQL_TABLAS) {
			db.execSQL("DROP TABLE IF EXISTS "+sql[0]);
		}
	}
}
