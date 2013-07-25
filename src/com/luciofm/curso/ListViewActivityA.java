package com.luciofm.curso;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
		list.setAdapter(new CidadesAdapter(this,
				R.layout.new_list_item, R.id.text,
				capitais));
	}

	public class CidadesAdapter extends ArrayAdapter<String> {

		private int layout;
		private int textId;
		private LayoutInflater inflater;
		
		public CidadesAdapter(Context context, int resource,
				int textViewResourceId, String[] objects) {
			super(context, resource, textViewResourceId, objects);
			layout = resource;
			textId = textViewResourceId;
			inflater = LayoutInflater.from(context);
		}

		/* Essa fun��o retorna o n�mero de Views (XML/Layouts)
		 * diferentes que esse adapter utiliza...
		 */
		@Override
		public int getViewTypeCount() {
			return 2;
		}

		/* Essa fun��o retorna qual o tipo da View baseado em sua 
		 * posi��o...
		 * Nesse caso, itens de indice par 0, itens impares 1
		 */
		@Override
		public int getItemViewType(int position) {
			return position % 2 == 0 ? 0 : 1;
		}

		/* Essa fun��o retorna uma View, uma linha da nossa lista
		 * Recebe a posi��o do item, uma view de cache (convertView),
		 * e seu pai, a propria listview, ou gridview...
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;

			int viewType = getItemViewType(position) == 0 ? R.layout.new_list_item
					: R.layout.new_list_item2;

			if (v == null)
				v = inflater.inflate(viewType, parent, false);

			TextView textView = (TextView) v.findViewById(textId);
			textView.setText(getItem(position));

			/* Modificando em c�digo.... */
			/*if (position % 2 == 0) {
				textView.setTextColor(Color.argb(255, 0, 0, 0));
				v.setBackgroundColor(Color.WHITE);
			} else {
				textView.setTextColor(Color.WHITE);
				v.setBackgroundColor(Color.BLACK);
			}*/
			
			return v;
		}
	}
}
