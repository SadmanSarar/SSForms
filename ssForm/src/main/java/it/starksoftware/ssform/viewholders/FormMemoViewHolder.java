package it.starksoftware.ssform.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.listeners.FormCustomEditMemoTextListener;
import it.starksoftware.ssform.model.FormElementMemo;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormMemoViewHolder extends FormViewHolder {
	
	
	public FormMemoViewHolder(View itemView, FormCustomEditMemoTextListener listener) {
		super(itemView, null, null, FormTypeManager.IS_MEMO_VIEW, listener);
	}
	
	public void bind(FormElementMemo formElement) {
		primaryBind();
		mTextViewTitle.setText(formElement.getTitle());
		mEditMemoTextValue.setText(formElement.getValue());
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
	
	
	public static FormMemoViewHolder createViewHolder(ViewGroup parent, FormCustomEditMemoTextListener listener) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_spinner, parent, false);
		
		return new FormMemoViewHolder(view, listener);
		
	}
	
}
