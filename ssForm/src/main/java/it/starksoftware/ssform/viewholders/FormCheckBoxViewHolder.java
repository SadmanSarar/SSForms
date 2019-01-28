package it.starksoftware.ssform.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.interfaces.CheckBoxCallBack;
import it.starksoftware.ssform.model.FormElementCheckBox;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormCheckBoxViewHolder extends FormViewHolder {
	
	
	public FormCheckBoxViewHolder(View itemView) {
		super(itemView, null, null, FormTypeManager.IS_CHECKBOX_VIEW, null);
	}
	
	public void bind(final FormElementCheckBox formElement) {
		mTextViewTitle.setText(formElement.getTitle());
		
		if (formElement.getCallback() != null) {
			final CheckBoxCallBack checkBoxCallBack = formElement.getCallback();
			mEditCheckBoxValue.setChecked(formElement.isChecked());
			mEditCheckBoxValue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					checkBoxCallBack.callbackCheckBoxReturn(formElement.getTag(), mEditCheckBoxValue, isChecked);
				}
			});
		}
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
	
	public static FormCheckBoxViewHolder createViewHolder(ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_checkbox, parent, false);
		
		return new FormCheckBoxViewHolder(view);
		
	}
	
}
