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

	private String[] capitais = { "Rio Branco", "Maceió", "Macapá", "Manaus",
			"Salvador", "Fortaleza", "Brasília", "Vitória", "Goiânia",
			"São Luis", "Cuiabá", "Campo Grande", "Belo Horizonte", "Belém",
			"João Pessoa", "Curitiba", "Recife", "Teresina", "Rio de Janeiro",
			"Natal", "Porto Alegre", "Porto Velho", "Boa Vista",
			"Florianópolis", "São Paulo", "Aracajú", "Palmas" };

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
