package it.starksoftware.ssform.style;

import android.view.View;

/**
 * Created by Sadman Sarar on 1/22/19.
 */
public class FormContainerStyle {
	
	private Background mBackGroundColor;
	
	private FormContainerStyle() {
	}
	
	public static FormContainerStyle createInstance() {
		return new FormContainerStyle();
	}
	
	
	public FormContainerStyle setBackGround(Background backGround) {
		mBackGroundColor = backGround;
		return this;
	}
	
	public void format(View view) {
		if (mBackGroundColor != null) {
			mBackGroundColor.setBackground(view);
		}
	}
}
