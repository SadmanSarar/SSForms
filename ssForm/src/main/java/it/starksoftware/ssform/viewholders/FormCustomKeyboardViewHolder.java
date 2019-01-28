package it.starksoftware.ssform.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.model.FormElementAttach;
import it.starksoftware.ssform.model.FormElementCustomKeyboard;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormCustomKeyboardViewHolder extends FormViewHolder {
	
	
	public FormCustomKeyboardViewHolder(View itemView) {
		super(itemView, null,null, FormTypeManager.IS_CUSTOM_KEYBOARD, null);
	}
	
	public void bind(final FormElementCustomKeyboard formElement) {
		mTextViewTitle.setText(formElement.getTitle());
		mEditTextValue.setText(formElement.getValue());
		mEditTextValue.setFocusableInTouchMode(true);
		layoutRow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				formElement.getmKeyboard().registerEditText(mEditTextValue, formElement);
			}
		});
		formElement.getmKeyboard().registerEditText(mEditTextValue, formElement);
		
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
	
	
	public static FormCustomKeyboardViewHolder createViewHolder(ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_custom_keyboard, parent, false);
		
		return new FormCustomKeyboardViewHolder(view);
		
	}
	
}
