package com.logigear.control.base;

public interface IEditable extends IClickable {

	void setValue(String value);

	void enter(CharSequence... value);

	void clear();
}
