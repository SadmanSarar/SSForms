package it.starksoftware.ssform.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.model.FormDivider;
import it.starksoftware.ssform.model.FormHeader;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormDividerViewHolder extends FormViewHolder {
	
	
	public FormDividerViewHolder(View itemView) {
		super(itemView, null, null, FormTypeManager.IS_DIVIDER_VIEW, null);
	}
	
	public void bind(FormDivider formHeader){
	
	}
	
	public static FormDividerViewHolder createViewHolder(ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_divider, parent, false);
		
		return new FormDividerViewHolder(view);
		
	}
	
}
