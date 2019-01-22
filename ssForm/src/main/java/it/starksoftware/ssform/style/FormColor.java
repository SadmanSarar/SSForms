package it.starksoftware.ssform.style;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

/**
 * Created by Sadman Sarar on 1/22/19.
 */
public class FormColor {
	enum Type {RES, HEX, INT}
	
	private Type   mType;
	@Nullable
	private String hexString;
	@ColorInt
	private int    color;
	@ColorRes
	private int    resColor;
	
	private FormColor() {
	}
	
	public static FormColor fromHex(String hex) {
		FormColor formColor = new FormColor();
		formColor.hexString = hex;
		formColor.mType = Type.HEX;
		return formColor;
	}
	
	public static FormColor fromInt(@ColorInt int colorInt) {
		FormColor formColor = new FormColor();
		formColor.color = colorInt;
		formColor.mType = Type.INT;
		return formColor;
	}
	
	public static FormColor fromRes(@ColorRes int colorRes) {
		FormColor formColor = new FormColor();
		formColor.resColor = colorRes;
		formColor.mType = Type.RES;
		return formColor;
	}
	
	public int getColor(Context context) {
		switch (mType) {
			case HEX:
				return android.graphics.Color.parseColor(hexString);
			case RES:
				return ContextCompat.getColor(context, resColor);
			case INT:
				return color;
			default:
				return android.graphics.Color.BLACK;
		}
		
	}
}
