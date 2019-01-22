package it.starksoftware.ssform.style;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

/**
 * Created by Sadman Sarar on 1/22/19.
 */
public class Color {
	enum Type {RES, HEX, INT}
	
	private Type   mType;
	@Nullable
	private String hexString;
	@ColorInt
	private int    color;
	@ColorRes
	private int    resColor;
	
	private Color() {
	}
	
	public static Color fromHex(String hex) {
		Color color = new Color();
		color.hexString = hex;
		color.mType = Type.HEX;
		return color;
	}
	
	public static Color fromInt(@ColorInt int colorInt) {
		Color color = new Color();
		color.color = colorInt;
		color.mType = Type.INT;
		return color;
	}
	
	public static Color fromRes(@ColorRes int colorRes) {
		Color color = new Color();
		color.resColor = colorRes;
		color.mType = Type.RES;
		return color;
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
