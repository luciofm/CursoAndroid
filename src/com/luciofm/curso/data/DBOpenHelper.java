package com.luciofm.curso.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder builder = new StringBuilder();
		builder.append("CREATE TABLE tabela (");
		builder.append("_id INTEGER PRIMARY KEY AUTOINCREMENT,");
		builder.append("nome TEXT,");
		builder.append("email TEXT");
		builder.append(")");

		db.execSQL(builder.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE tabela IF EXISTS");
		onCreate(db);
	}
}
