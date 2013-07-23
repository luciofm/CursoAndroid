package com.luciofm.curso;

import com.luciofm.curso.data.DBOpenHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ActivityDB extends Activity implements OnItemClickListener, OnItemLongClickListener {

	EditText edit1;
	EditText edit2;
	Button button;

	private boolean update = false;
	private int currentId;

	ListView list;

	SQLiteDatabase db;
	DBOpenHelper helper;
	SimpleCursorAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_db);

		edit1 = (EditText) findViewById(R.id.editText1);
		edit2 = (EditText) findViewById(R.id.editText2);
		button = (Button) findViewById(R.id.button1);
		list = (ListView) findViewById(R.id.listView1);
		list.setOnItemClickListener(this);
		list.setOnItemLongClickListener(this);

		button.setOnClickListener(clickListener);

		helper = new DBOpenHelper(this, "database", null, 1);
		db = helper.getWritableDatabase();
		atualizaLista();
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			String nome = edit1.getText().toString();
			String email = edit2.getText().toString();

			if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(email)) {
				return;
			}
			ContentValues val = new ContentValues();
			val.put("nome", nome);
			val.put("email", email);

			if (!update)
				db.insert("tabela", null, val);
			else {
				String where = BaseColumns._ID + " = ?";
				String[] whereArgs = new String[] { String.valueOf(currentId) };
				db.update("tabela", val, where, whereArgs);
			}

			button.setText("Adicionar");
			update = false;
			edit1.setText("");
			edit2.setText("");

			atualizaLista();
		}
	};

	public void atualizaLista() {
		Cursor cursor = db.query("tabela", null, null, null, null, null, null);

		String[] colunas = new String[] { "nome", "email" };
		int[] ids = new int[] { android.R.id.text1, android.R.id.text2 };
		mAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, cursor, colunas, ids);
		list.setAdapter(mAdapter);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> adapter, View v, int pos,
			long viewId) {
		Cursor c = mAdapter.getCursor();
		c.moveToPosition(pos);
		int id = c.getInt(c.getColumnIndex(BaseColumns._ID));

		String where = BaseColumns._ID + " = ?";
		String[] whereArgs = new String[] { String.valueOf(id) };
		db.delete("tabela", where, whereArgs);

		atualizaLista();
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View v, int pos, long id) {
		Cursor c = mAdapter.getCursor();
		c.moveToPosition(pos);
		currentId = c.getInt(c.getColumnIndex(BaseColumns._ID));
		update = true;

		edit1.setText(c.getString(c.getColumnIndex("nome")));
		edit2.setText(c.getString(c.getColumnIndex("email")));

		button.setText("Update");
	}
}
