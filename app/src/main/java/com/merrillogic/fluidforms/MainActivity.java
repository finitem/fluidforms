package com.merrillogic.fluidforms;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.composition_form);
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.forms);
		recyclerView.setLayoutManager(
				new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		FormAdapter.LayoutSource source = new FormAdapter.LayoutSource() {
			@Override
			public int getContainerLayoutId(InputItem.InputType itemType) {
				//If these, return these... AGH MAPS AGAIN. Hey I should make this a resource thing.
				return R.layout.input_container;
			}

			@Override
			public int getInnardsLayoutId(InputItem.InputType itemType) {
				return R.layout.input_item_textbox;
			}

			@Override
			public InnerViewComposition makeInnerViewComposition(InputItem.InputType type,
																 View rootView) {
				return new InnerTextViewComposition(rootView);
			}
		};

		List<InputItem> inputs = new ArrayList<>();
		InputItem<String> stringput = new InputItem<>();
		stringput.title = "hello";
		stringput.data = "yoyoyo";
		stringput.explanation = "put your greeting here please";
		stringput.type = InputItem.InputType.TEXT;
		inputs.add(stringput);
		recyclerView.setAdapter(new FormAdapter(source, inputs));
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
