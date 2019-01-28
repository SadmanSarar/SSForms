package it.starksoftware.ssform.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.adapter.FormSpinAdapter;
import it.starksoftware.ssform.interfaces.SpinnerCallBack;
import it.starksoftware.ssform.model.FormElementSpinner;
import it.starksoftware.ssform.model.FormSpinnerObject;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormSpinnerViewHolder extends FormViewHolder {
	
	
	public FormSpinnerViewHolder(View itemView) {
		super(itemView, null, null, FormTypeManager.IS_SPINNER_VIEW, null);
	}
	
	public void bind(final FormElementSpinner formElement) {
		mTextViewTitle.setText(formElement.getTitle());
		final FormSpinAdapter adapter         = formElement.getSpinnerAdapter();
		final SpinnerCallBack spinnerCallBack = formElement.getCallback();
		mEditSpinnerValue.setAdapter(adapter);
		if (formElement.getValue() != null) {
			int spinnerPosition = adapter.indexOfSpinner(formElement.getValue());
			mEditSpinnerValue.setSelection(spinnerPosition);
		}
		
		if (formElement.getRefresh()) {
			mEditSpinnerValue.setTag(formElement.getTag());
		}
		
		mEditSpinnerValue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
				FormSpinnerObject user = adapter.getItem(position);
				formElement.setValue(user);
				if (spinnerCallBack != null) {
					spinnerCallBack.callbackSpinnerReturn(user, formElement.getTag(), mEditSpinnerValue);
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> adapter) {
			
			}
		});
		mEditSpinnerValue.setOnFocusChangeListener(new AdapterView.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				String aa = "";
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
	
	
	public static FormSpinnerViewHolder createViewHolder(ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_spinner, parent, false);
		
		return new FormSpinnerViewHolder(view);
		
	}
	
}
