package it.starksoftware.ssform.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.interfaces.RatingCallBack;
import it.starksoftware.ssform.model.FormElementRating;
import it.starksoftware.ssform.ratings.BaseRatingBar;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormRatingViewHolder extends FormViewHolder {
	
	
	public FormRatingViewHolder(View itemView) {
		super(itemView, null, null, FormTypeManager.IS_RATING_VIEW, null);
	}
	
	public void bind(final FormElementRating formElement) {
		final RatingCallBack ratingCallBack = formElement.getRatingCallBack();
		mTextViewTitle.setText(formElement.getTitle());
		mEditRatingValue.setNumStars(formElement.getStars());
		mEditRatingValue.setRating(formElement.getRatingValue());
		mEditRatingValue.setOnRatingChangeListener(new BaseRatingBar.OnRatingChangeListener() {
			@Override
			public void onRatingChange(BaseRatingBar ratingBar, float rating) {
				formElement.setRatingValue(Math.round(rating));
				ratingCallBack.callbackRatingReturn(rating);
			}
		});
		if (linearLayout.getLayoutParams() != null) {
			if (!formElement.getVisibility()) {
				ViewGroup.LayoutParams params = linearLayout.getLayoutParams();
				params.height = 0;
				linearLayout.setLayoutParams(params);
			} else {
				ViewGroup.LayoutParams params = linearLayout.getLayoutParams();
				params.height = -2;
				linearLayout.setLayoutParams(params);
			}
		}
	}
	
	public static FormRatingViewHolder createViewHolder(ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_rating, parent, false);
		
		return new FormRatingViewHolder(view);
		
	}
	
}
