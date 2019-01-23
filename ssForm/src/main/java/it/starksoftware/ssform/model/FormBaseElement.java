package it.starksoftware.ssform.model;

import it.starksoftware.ssform.style.FormContainerStyle;
import it.starksoftware.ssform.style.FormEditTextStyle;
import it.starksoftware.ssform.style.FormTextStyle;

/**
 * Basic interface for all form elements,
 * Created by Sadman Sarar on 18-Apr-17.
 */

public abstract class FormBaseElement<T extends FormBaseElement<T>> implements FormObject {
	
	private FormTextStyle      mTitleTextStyle;
	private FormEditTextStyle  mValueTextStyle;
	private FormContainerStyle mContainerStyle;
	
	public FormTextStyle getTitleTextStyle() {
		return mTitleTextStyle;
	}
	public abstract T getThis();
	public T setTitleTextStyle(FormTextStyle formTextStyle) {
		mTitleTextStyle = formTextStyle;
		return getThis();
	}
	
	public FormEditTextStyle getValueTextStyle() {
		return mValueTextStyle;
	}
	
	public T setValueTextStyle(FormEditTextStyle formTextStyle) {
		mValueTextStyle = formTextStyle;
		return getThis();
	}
	
	public FormContainerStyle getContainerStyle() {
		return mContainerStyle;
	}
	
	public T setContainerStyle(FormContainerStyle containerStyle) {
		mContainerStyle = containerStyle;
		return getThis();
	}
}
