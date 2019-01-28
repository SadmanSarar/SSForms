package it.starksoftware.ssform.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.interfaces.ButtonCallBack;
import it.starksoftware.ssform.model.FormElementAttach;
import it.starksoftware.ssform.model.FormElementButton;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormButtonViewHolder extends FormViewHolder {
	
	
	public FormButtonViewHolder(View itemView) {
		super(itemView, null,null, FormTypeManager.IS_BUTTON_VIEW, null);
	}
	
	public void bind(final FormElementButton formElement) {
		final ButtonCallBack buttonCallBack = formElement.getButtonCallBack();
		mButtonTitle.setText(formElement.getTitle());
		mButtonTitle.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				buttonCallBack.callbackButtonReturn(formElement, formElement.getTag());
			}
		});
	}
	
	
	public static FormButtonViewHolder createViewHolder(ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_button, parent, false);
		return new FormButtonViewHolder(view);
	}
	
}
