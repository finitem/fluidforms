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

	LayoutSource mLayoutSource;
	List<InputItem> mInputItems;

	public FormAdapter(LayoutSource layoutSource, List<InputItem> inputItems) {
		mLayoutSource = layoutSource;
		mInputItems = inputItems;
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
	public void onBindViewHolder(FormViewHolder formViewHolder, int i) {
		InputItem item = mInputItems.get(i);
		formViewHolder.titleText.setText(item.title);
		formViewHolder.contentComp.bind(item.data);
	}

	@Override
	public int getItemCount() {
		return mInputItems.size();
	}
}
