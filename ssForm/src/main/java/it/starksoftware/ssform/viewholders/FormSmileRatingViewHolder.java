package it.starksoftware.ssform.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import java.util.Iterator;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.interfaces.RatingSmileCallBack;
import it.starksoftware.ssform.model.FormElementSmileRating;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormSmileRatingViewHolder extends FormViewHolder {
	
	
	public FormSmileRatingViewHolder(View itemView) {
		super(itemView, null, null, FormTypeManager.IS_SMILE_RATING, null);
	}
	
	public void bind(final FormElementSmileRating formElement) {
		mTextViewTitle.setText(formElement.getTitle());
		
		Iterator myVeryOwnIterator = formElement.getSmileTitleByValue().keySet().iterator();
		while (myVeryOwnIterator.hasNext()) {
			Integer key   = (Integer) myVeryOwnIterator.next();
			String  value = formElement.getSmileTitleByValue().get(key);
			mSmileValue.setNameForSmile(key, value);
		}
		
		mSmileValue.setSelectedSmile(formElement.getValue());
		final RatingSmileCallBack callback = formElement.getRatingSmileCallBack();
		mSmileValue.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
			@Override
			public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
				switch (smiley) {
					case SmileRating.NONE:
						formElement.setValue(0);
						if (callback != null) {
							callback.callbackRatingSmileReturn(0);
						}
						break;
					case SmileRating.TERRIBLE:
						formElement.setValue(1);
						if (callback != null) {
							callback.callbackRatingSmileReturn(1);
						}
						break;
					case SmileRating.BAD:
						formElement.setValue(2);
						if (callback != null) {
							callback.callbackRatingSmileReturn(2);
						}
						break;
					case SmileRating.GOOD:
						formElement.setValue(3);
						if (callback != null) {
							callback.callbackRatingSmileReturn(3);
						}
						break;
					case SmileRating.OKAY:
						formElement.setValue(4);
						if (callback != null) {
							callback.callbackRatingSmileReturn(4);
						}
						break;
					case SmileRating.GREAT:
						formElement.setValue(5);
						if (callback != null) {
							callback.callbackRatingSmileReturn(5);
						}
						break;
				}
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
	
	
	public static FormSmileRatingViewHolder createViewHolder(ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_smile_rating, parent, false);
		
		return new FormSmileRatingViewHolder(view);
		
	}
	
}
