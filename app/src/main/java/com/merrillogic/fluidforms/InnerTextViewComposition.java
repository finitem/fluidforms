package com.merrillogic.fluidforms;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 *
 */
public class InnerTextViewComposition extends InnerViewComposition {

	TextView mReviewText;
	EditText mEditText;
	InputItem<String> currentData;

	public InnerTextViewComposition(View rootView) {
		super(rootView);
		mReviewText = (TextView) mReviewView;
		mEditText = (EditText) mEditView;
	}

	@Override
	public void bind(InputItem dataItem) {
		//I'LL JUST DO MY OWN DAMN TYPING
		if (dataItem.data instanceof String) {
			currentData = dataItem;
		} else {
			throw new Error("FUCK THIS, WRONG TYPE, FUCK THAT.");
		}
		mEditText.setText(currentData.data);
		mReviewText.setText(currentData.data);
	}

	@Override
	public void unbind() {
		ensureSaved();
		currentData = null;
	}

	public void ensureSaved() {
		currentData.data = mEditText.getText().toString();
		//might want something like this to move from edit to review, and to save to dataitem
	}

	@Override
	public void startEdit() {
		AnimatorSet set = new AnimatorSet();
		Animator fadeOut = ObjectAnimator.ofFloat(mReviewText, "alpha", 1f, 0f);
		Animator fadeIn = ObjectAnimator.ofFloat(mEditText, "alpha", 0f, 1f);
		set.playTogether(fadeIn, fadeOut);
		set.setDuration(350);
		set.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
				mReviewText.setVisibility(View.VISIBLE);
				mEditText.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				jumpToEdit();
			}

			@Override
			public void onAnimationCancel(Animator animation) {
			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}
		});
		set.start();
	}

	@Override
	public void jumpToEdit() {
		mReviewText.setVisibility(View.GONE);
		mEditText.setVisibility(View.VISIBLE);
	}

	@Override
	public void finishEdit() {
		jumpToReview();
	}

	@Override
	public void jumpToReview() {

		mReviewText.setText(currentData.data);
		mEditText.setVisibility(View.GONE);
		mReviewText.setVisibility(View.VISIBLE);
	}
}
