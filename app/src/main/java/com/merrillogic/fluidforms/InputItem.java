package com.merrillogic.fluidforms;

/**
 *
 */
public class InputItem<T> {
	public enum InputType {
		TEXT,
		PICKER,
		CHECKLIST,
		CHECKLIST_WITH_BLANK,
		RADIO,
		RADIO_WITH_BLANK
	}

	InputType type;
	String title;
	String explanation;
	T data;

}
