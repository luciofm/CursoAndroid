package com.luciofm.curso;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivityC extends Activity {

	private ListView list;

	private String[] capitais = { "Rio Branco", "Macei�", "Macap�", "Manaus",
			"Salvador", "Fortaleza", "Bras�lia", "Vit�ria", "Goi�nia",
			"S�o Luis", "Cuiab�", "Campo Grande", "Belo Horizonte", "Bel�m",
			"Jo�o Pessoa", "Curitiba", "Recife", "Teresina", "Rio de Janeiro",
			"Natal", "Porto Alegre", "Porto Velho", "Boa Vista",
			"Florian�polis", "S�o Paulo", "Aracaj�", "Palmas" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_activity);

		list = (ListView) findViewById(R.id.list);
		list.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				capitais));
	}

	public class Adapter extends BaseAdapter {

		LayoutInflater inflater;
		String[] objects;

		public Adapter(Context context, String[] objects) {
			this.objects = objects;
			inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return objects != null ? objects.length : 0;
		}

		@Override
		public String getItem(int position) {
			return objects[position];
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;

			if (v == null)
				v = inflater.inflate(R.layout.simple_list_item, parent, false);

			TextView text = (TextView) v.findViewById(R.id.text);
			text.setText(getItem(position));

			return v;
		}

	}
}
