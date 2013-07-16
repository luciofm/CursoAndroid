package com.luciofm.curso;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivityA extends Activity {

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
}
