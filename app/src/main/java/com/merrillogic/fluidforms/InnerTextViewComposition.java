package com.merrillogic.fluidforms;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 *
 */
public class InnerTextViewComposition extends InnerViewComposition {

	TextView mReviewText;
	EditText mEditText;

	public InnerTextViewComposition(View rootView) {
		super(rootView);
		mReviewText = (TextView) mReviewView;
		mEditText = (EditText) mEditView;
	}

	@Override
	public void bind(Object dataItem) {
		//I'LL JUST DO MY OWN DAMN TYPING
		if (dataItem instanceof String) {
			String now = (String) dataItem;
			mEditText.setText(now);
			mReviewText.setText(now);
		} else {
			throw new Error("FUCK THIS, WRONG TYPE, FUCK THAT.");
		}

	}
}
