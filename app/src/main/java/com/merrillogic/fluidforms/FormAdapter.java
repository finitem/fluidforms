package com.merrillogic.fluidforms;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.merrillogic.fluidforms.InputItem.InputType;

import java.util.List;

/**
 *
 */
public class FormAdapter extends RecyclerView.Adapter<FormViewHolder> {


	public interface LayoutSource {
		public int getContainerLayoutId(InputType itemType);

		public int getInnardsLayoutId(InputType itemType);

		public InnerViewComposition makeInnerViewComposition(InputType type, View rootView);
	}

	public interface FormAdapterInteractionListener {
		public void rowClicked(int pos);
	}
	//Really want to put all of the state info into some generalized gson-friendly state class.

	private LayoutSource mLayoutSource;
	private List<InputItem> mInputItems;
	private FormAdapterInteractionListener mListener;
	private int mSelectedPosition = 0;

	public FormAdapter(LayoutSource layoutSource, List<InputItem> inputItems, FormAdapterInteractionListener listener) {
		mLayoutSource = layoutSource;
		mInputItems = inputItems;
		//Can I produce this instead?
		mListener = listener;
	}

	@Override
	public FormViewHolder onCreateViewHolder(ViewGroup parent, int i) {

		InputType type = mInputItems.get(i).type;
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		FormViewHolder fullViewHolder = new FormViewHolder(
				inflater.inflate(mLayoutSource.getContainerLayoutId(type), parent, false));
		View innerView = inflater.inflate(mLayoutSource.getInnardsLayoutId(type),
				fullViewHolder.contentView, true);
		fullViewHolder.contentComp = mLayoutSource.makeInnerViewComposition(type, innerView);

		return fullViewHolder;
	}

	@Override
	public void onBindViewHolder(final FormViewHolder formViewHolder, final int i) {
		InputItem item = mInputItems.get(i);
		formViewHolder.titleText.setText(item.title);
		formViewHolder.contentComp.bind(item);
		if (mSelectedPosition == i) {
			formViewHolder.contentComp.jumpToEdit();
		} else {
			formViewHolder.contentComp.jumpToReview();
		}
		formViewHolder.rootView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				mListener.rowClicked(i);
				formViewHolder.contentComp.startEdit();
				//TODO: Y'know, don't just default to going to edit mode.
			}
		});
	}

	@Override
	public void onViewRecycled(FormViewHolder holder) {
		holder.contentComp.unbind();
		super.onViewRecycled(holder);
	}

	@Override
	public int getItemCount() {
		return mInputItems.size();
	}
}
