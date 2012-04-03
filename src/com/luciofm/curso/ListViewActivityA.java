package com.luciofm.curso;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivityA extends Activity {

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
}
