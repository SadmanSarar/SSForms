package it.starksoftware.ssform.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.interfaces.SwitchCallBack;
import it.starksoftware.ssform.model.FormElementSwitch;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormSwitchViewHolder extends FormViewHolder {
	
	
	public FormSwitchViewHolder(View itemView) {
		super(itemView, null, null, FormTypeManager.IS_SWITCH_VIEW, null);
	}
	
	public void bind(final FormElementSwitch formElement) {
		mTextViewTitle.setText(formElement.getTitle());
		mEditSwitchValue.setChecked(formElement.getValue());
		final SwitchCallBack switchCallBack = formElement.getSwitchCallBack();
		mEditSwitchValue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				switchCallBack.callbackSwitchReturn(formElement, formElement.getTag(), isChecked);
				formElement.setValue(isChecked);
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
	
	public static FormSwitchViewHolder createViewHolder(ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_switch, parent, false);
		
		return new FormSwitchViewHolder(view);
		
	}
	
}
