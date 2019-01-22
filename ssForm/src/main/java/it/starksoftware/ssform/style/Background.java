package it.starksoftware.ssform.style;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Sadman Sarar on 1/22/19.
 */
public class Background {
	enum Type {RES, DRAWABLE, COLOR}
	
	private Type      mType;
	@Nullable
	private FormColor mFormColor;
	private Drawable  mDrawable;
	@DrawableRes
	private int       mResBackground;
	
	private Background() {
	}
	
	public static Background fromRes(@DrawableRes int resBackground) {
		Background background = new Background();
		background.mResBackground = resBackground;
		background.mType = Type.RES;
		return background;
	}
	
	public static Background fromDrawable(Drawable drawable) {
		Background background = new Background();
		background.mDrawable = drawable;
		background.mType = Type.DRAWABLE;
		return background;
	}
	
	public static Background fromColor(FormColor formColor) {
		Background background = new Background();
		background.mFormColor = formColor;
		background.mType = Type.COLOR;
		return background;
	}
	
	public void setBackground(View view) {
		switch (mType) {
			case DRAWABLE:
				view.setBackground(this.mDrawable);
				break;
			case RES:
				view.setBackgroundResource(mResBackground);
				break;
			case COLOR:
				assert mFormColor != null;
				view.setBackgroundColor(mFormColor.getColor(view.getContext()));
				break;
			default:
			
		}
		
	}
}
