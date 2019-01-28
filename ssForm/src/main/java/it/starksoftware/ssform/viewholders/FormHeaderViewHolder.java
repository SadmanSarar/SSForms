package it.starksoftware.ssform.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.model.FormHeader;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormHeaderViewHolder extends FormViewHolder {
	
	
	public FormHeaderViewHolder(View itemView) {
		super(itemView, null, null, FormTypeManager.IS_HEADER_VIEW, null);
	}
	
	public void bind(FormHeader formHeader){
		mTextViewTitle.setText(formHeader.getTitle());
		if (formHeader.getFormTextStyle() != null) {
			formHeader.getFormTextStyle().format(mTextViewTitle);
		}
		if (formHeader.getContainerStyle() != null) {
			formHeader.getContainerStyle().format(itemView);
		}
	}
	
	public static FormHeaderViewHolder createViewHolder(ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_header, parent, false);
		
		return new FormHeaderViewHolder(view);
		
	}
	
}
