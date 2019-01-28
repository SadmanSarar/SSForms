package it.starksoftware.ssform.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.model.FormElementAttach;
import it.starksoftware.ssform.model.FormElementPlaceDialog;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormPlaceDialogViewHolder extends FormViewHolder {
	
	
	private Callback mCallback;
	
	public FormPlaceDialogViewHolder(View itemView, Callback callback) {
		super(itemView, null,null, FormTypeManager.IS_PLACE_DIALOG_VIEW, null);
		mCallback = callback;
	}
	
	public void bind(final FormElementPlaceDialog formElement) {
		mTextViewTitle.setText(formElement.getTitle());
		mTextViewValue.setText(formElement.getValue());
		mCallback.setPlaceDialogPicker(mTextViewValue, getAdapterPosition(), layoutRow, formElement);
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
	
	public interface Callback{
		void setPlaceDialogPicker(final TextView tv, final int position, final LinearLayout layoutRow, final FormElementPlaceDialog formElement);
	}
	
	public static FormPlaceDialogViewHolder createViewHolder(ViewGroup parent, Callback callback) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_place_dialog, parent, false);
		
		return new FormPlaceDialogViewHolder(view, callback);
		
	}
	
}
