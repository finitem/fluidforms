package com.merrillogic.fluidforms;

import android.view.View;

/**
 *
 */
public abstract class InnerViewComposition {

	private View mRootView;
	protected View mEditView;
	protected View mReviewView;

	public InnerViewComposition(View rootView) {
		mRootView = rootView;
		mEditView = mRootView.findViewById(R.id.edit_container);
		mReviewView = mRootView.findViewById(R.id.review_container);
	}

	public abstract void bind(Object dataItem);

	//animateToEdit;

	//animateToStatic;
}
