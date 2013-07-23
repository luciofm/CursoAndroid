package com.luciofm.curso;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.luciofm.curso.data.provider.CursoContent.CursoPessoas;
import com.luciofm.curso.data.provider.CursoContent.CursoPessoas.Columns;

public class ActivityProvider extends FragmentActivity implements OnItemClickListener, OnItemLongClickListener, LoaderCallbacks<Cursor> {

	EditText edit1;
	EditText edit2;
	Button button;

	private boolean update = false;
	private int currentId;

	ListView list;

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

		String[] colunas = new String[] { Columns.NOME.getName(),
				Columns.EMAIL.getName() };
		int[] ids = new int[] { android.R.id.text1, android.R.id.text2 };
		mAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, null, colunas, ids, 0);
		list.setAdapter(mAdapter);

		getSupportLoaderManager().initLoader(0, null, this);
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
			ContentResolver cr = getContentResolver();

			if (!update) {
				cr.insert(CursoPessoas.CONTENT_URI, val);
			}else {
				Uri uri = Uri.parse(CursoPessoas.CONTENT_URI.toString() + "/" + currentId);
				cr.update(uri, val, null, null);
			}

			button.setText("Adicionar");
			update = false;
			edit1.setText("");
			edit2.setText("");
		}
	};

	@Override
	public boolean onItemLongClick(AdapterView<?> adapter, View v, int pos,
			long viewId) {
		ContentResolver cr = getContentResolver();

		Cursor c = mAdapter.getCursor();
		c.moveToPosition(pos);
		int id = c.getInt(c.getColumnIndex(BaseColumns._ID));

		Uri uri = Uri.parse(CursoPessoas.CONTENT_URI.toString() + "/" + id);
		cr.delete(uri, null, null);

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

	@Override
	public Loader<Cursor> onCreateLoader(int loader, Bundle args) {
		return new CursorLoader(this, CursoPessoas.CONTENT_URI, CursoPessoas.PROJECTION, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		mAdapter.changeCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		mAdapter.changeCursor(null);
	}
}
