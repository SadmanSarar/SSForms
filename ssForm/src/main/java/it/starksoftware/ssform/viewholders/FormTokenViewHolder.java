package it.starksoftware.ssform.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.model.FormElementAttach;
import it.starksoftware.ssform.model.FormElementToken;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormTokenViewHolder extends FormViewHolder {
	
	
	private Callback mCallback;
	
	public FormTokenViewHolder(View itemView, Callback callback) {
		super(itemView, null,null, FormTypeManager.IS_TOKEN, null);
		mCallback = callback;
	}
	
	public void bind(final FormElementToken formElement) {
		mTextViewTitle.setText(formElement.getTitle());
		if (formElement.getValue() != null) {
			if (((FlexboxLayout) tokens).getChildCount() > 0) {
				((FlexboxLayout) tokens).removeAllViews();
			}
			for (int i = 0; i < formElement.getValue().size(); i++) {
				tokens.addView((View) formElement.getValue().get(i).getTokenItem(), 0);
			}
		}
		mCallback.setTokenPicker(btnAddTokens, getAdapterPosition(), formElement);
		
		if (linearLayout.getLayoutParams() != null) {
			if (!formElement.getVisibility()) {
				ViewGroup.LayoutParams params = linearLayout.getLayoutParams();
				params.height = 0;
				linearLayout.setLayoutParams(params);
			}
		}
	}
	
	public interface Callback{
		void setTokenPicker(ImageButton imgButton, final int position, final FormElementToken formElement);
	}
	
	public static FormTokenViewHolder createViewHolder(ViewGroup parent, Callback callback) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_token, parent, false);
		
		return new FormTokenViewHolder(view, callback);
		
	}
	
}
