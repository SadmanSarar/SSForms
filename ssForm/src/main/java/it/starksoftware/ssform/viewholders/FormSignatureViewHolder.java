package it.starksoftware.ssform.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.model.FormElementAttach;
import it.starksoftware.ssform.model.FormElementSignature;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormSignatureViewHolder extends FormViewHolder {
	
	
	private Callback mCallback;
	
	public FormSignatureViewHolder(View itemView, Callback callback) {
		super(itemView, null,null, FormTypeManager.IS_SIGNATURE_VIEW, null);
		mCallback = callback;
	}
	
	public void bind(final FormElementSignature formElement) {
		mTextViewTitle.setText(formElement.getTitle());
		mEditImageViewValue.setImageBitmap(formElement.getValue());
		mCallback.setSignaturePicker(mEditImageViewValue, getAdapterPosition(), layoutRow);
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
		void setSignaturePicker(ImageView imgView, final int position, final LinearLayout layoutRow);
	}
	
	public static FormSignatureViewHolder createViewHolder(ViewGroup parent, Callback callback) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_signature, parent, false);
		
		return new FormSignatureViewHolder(view, callback);
		
	}
	
}
