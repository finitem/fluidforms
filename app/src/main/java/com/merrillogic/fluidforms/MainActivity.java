package com.merrillogic.fluidforms;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity
		implements FormAdapter.FormAdapterInteractionListener {

	@InjectView(R.id.confirm_button)
	private Button vSubmitButton;

	@InjectView(R.id.forms)
	private RecyclerView vRecyclerView;

	private List<InputItem> mInputs = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.composition_form);
		ButterKnife.inject(this);
		vRecyclerView.setLayoutManager(
				new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		FormAdapter.LayoutSource source = new FormAdapter.LayoutSource() {
			@Override
			public int getContainerLayoutId(InputItem.InputType itemType) {
				//If these, return these... AGH MAPS AGAIN. Hey I should make this a resource thing.
				//assuming text
				return R.layout.input_container;
			}

			@Override
			public int getInnardsLayoutId(InputItem.InputType itemType) {
				//I'm assuming the type is text, and returning the layout for that here. got it.
				return R.layout.input_item_textbox;
			}

			@Override
			public InnerViewComposition makeInnerViewComposition(InputItem.InputType type,
																 View rootView) {
				//again, assuming text.
				return new InnerTextViewComposition(rootView);
			}
		};

		InputItem<String> stringput = new InputItem<>();
		stringput.title = "hello";
		stringput.data = "yoyoyo";
		stringput.explanation = "put your greeting here please";
		stringput.type = InputItem.InputType.TEXT;
		mInputs.add(stringput);
		vRecyclerView.setAdapter(new FormAdapter(source, mInputs, this));
	}

	@OnClick(R.id.confirm_button)
	void submit() {
		for (int i = 0; i < mInputs.size(); i++) {
			InputItem inputItem = mInputs.get(i);
			if (!inputItem.isValid()) {
				vRecyclerView.scrollToPosition(i);
				Toast.makeText(this, inputItem.title + " is required", Toast.LENGTH_LONG).show();
			}
		}
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

	@Override
	public void rowClicked(int pos) {
		//yup
	}
}
