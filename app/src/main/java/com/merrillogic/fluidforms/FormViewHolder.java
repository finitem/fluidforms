package com.merrillogic.fluidforms;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 *
 */
public class FormViewHolder extends RecyclerView.ViewHolder {

	View rootView;
	TextView titleText;
	ViewGroup contentView;
	InnerViewComposition contentComp;

	public FormViewHolder(View itemView) {
		super(itemView);
		rootView = itemView;
		titleText = (TextView) itemView.findViewById(R.id.title);
		contentView = (ViewGroup) itemView.findViewById(R.id.content_frame);
	}
}
