package it.starksoftware.ssform.model;

import it.starksoftware.ssform.style.FormTextStyle;

/**
 * Object for header of the form lists
 * Created by Adib on 18-Apr-17.
 */

public class FormHeader implements FormObject {
	
	private String mTitle;
	private boolean visibility = true;
	private int mTag;
	private boolean required = false;
	private FormTextStyle mFormTextStyle;
	
	
	public FormHeader() {
	}
	
	public FormHeader setTag(int mTag) {
		this.mTag = mTag;
		return this;
	}
	
	@Override
	public boolean isRequired() {
		return required;
	}
	
	public int getTag() {
		return mTag;
	}
	
	/**
	 * static method to create instance
	 *
	 * @return
	 */
	public static FormHeader createInstance() {
		return new FormHeader();
	}
	
	/**
	 * sets the title, returns itself
	 *
	 * @param title
	 *
	 * @return
	 */
	public FormHeader setTitle(String title) {
		this.mTitle = title;
		return this;
	}
	
	public FormHeader setCustomVisibility(boolean visibility) {
		this.visibility = visibility;
		return this;
	}
	
	public boolean getCustomVisibility() {
		return visibility;
	}
	
	public FormTextStyle getFormTextStyle() {
		return mFormTextStyle;
	}
	
	public FormHeader setHeaderTextStyle(FormTextStyle formTextStyle) {
		mFormTextStyle = formTextStyle;
		return this;
	}
	
	/**
	 * returns the title
	 *
	 * @return
	 */
	public String getTitle() {
		
		return this.mTitle;
	}
	
	@Override
	public boolean isHeader() {
		
		return true;
	}
	
	@Override
	public String getElementType() {
		
		return "Header";
	}
	
	@Override
	public String toString() {
		
		return this.mTitle;
	}
	
	
}
