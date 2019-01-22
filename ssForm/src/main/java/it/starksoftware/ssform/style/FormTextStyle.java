package it.starksoftware.ssform.style;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.StyleRes;
import android.support.v7.widget.AppCompatTextView;

/**
 * Created by Sadman Sarar on 1/22/19.
 */
public class FormTextStyle {
	
	private FormColor mTextFormColor;
	private float mTextSize       = 0;
	@StyleRes
	private int   mTextAppearance = Integer.MIN_VALUE;
	
	private FormTextStyle() {
	}
	
	public static FormTextStyle createInstance() {
		return new FormTextStyle();
	}
	
	
	public FormColor getTextFormColor() {
		return mTextFormColor;
	}
	
	/**
	 * @param color
	 *
	 * @return
	 */
	public FormTextStyle setTextFormColor(@ColorInt int color) {
		mTextFormColor = FormColor.fromInt(color);
		return this;
	}
	
	/**
	 * @param color
	 *
	 * @return
	 */
	public FormTextStyle setTextColorRes(@ColorRes int color) {
		mTextFormColor = FormColor.fromRes(color);
		return this;
	}
	
	/**
	 * @param color hex color eg: #ff0000
	 *
	 * @return
	 */
	public FormTextStyle setTextColorHex(String color) {
		mTextFormColor = FormColor.fromHex(color);
		return this;
	}
	
	/**
	 * @param size interpreted as "scaled pixel" units
	 *
	 * @return
	 */
	public FormTextStyle setTextSize(float size) {
		mTextSize = size;
		return this;
	}
	
	/**
	 * @return
	 */
	public float getTextSize() {
		return mTextSize;
	}
	
	/**
	 * @param appearance
	 *
	 * @return
	 */
	public FormTextStyle setTextAppearance(@StyleRes int appearance) {
		mTextAppearance = appearance;
		return this;
	}
	
	/**
	 * @return
	 */
	public int getTextAppearance() {
		return mTextAppearance;
	}
	
	public void format(AppCompatTextView textView) {
		if (getTextFormColor() != null) {
			textView.setTextColor(getTextFormColor().getColor(textView.getContext()));
		}
		if (getTextSize() > 0) {
			textView.setTextSize(getTextSize());
		}
		if (getTextAppearance() > 0) {
			textView.setTextAppearance(textView.getContext(), getTextAppearance());
		}
	}
}
