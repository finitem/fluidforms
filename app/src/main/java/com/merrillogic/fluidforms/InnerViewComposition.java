package com.merrillogic.fluidforms;

import android.view.View;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 *
 */
public abstract class InnerViewComposition {

	private View mRootView;
	@InjectView(R.id.edit_container)
	protected View mEditView;
	@InjectView(R.id.review_container)
	protected View mReviewView;

	public InnerViewComposition(View rootView) {
		mRootView = rootView;
		ButterKnife.inject(this, rootView);
	}

	public abstract void bind(InputItem dataItem);

	public abstract void unbind();

	public abstract void startEdit();

	public abstract void jumpToEdit();

	public abstract void finishEdit();

	public abstract void jumpToReview();

}
