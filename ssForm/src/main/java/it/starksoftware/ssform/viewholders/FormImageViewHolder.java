package it.starksoftware.ssform.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.model.FormElementImageView;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormImageViewHolder extends FormViewHolder {
	
	
	private final Callback mCallback;
	
	public FormImageViewHolder(View itemView, Callback callback) {
		super(itemView, null, null, FormTypeManager.IS_IMAGE_VIEW, null);
		mCallback = callback;
	}
	
	public void bind(final FormElementImageView formElement) {
		mTextViewTitle.setText(formElement.getTitle());
		mEditImageViewValue.setImageBitmap(formElement.getValue());
		mCallback.setImagePicker(mEditImageViewValue, getAdapterPosition(), layoutRow);
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
	
	public interface Callback {
		void setImagePicker(ImageView imgView, final int position, final LinearLayout layoutRow);
	}
	
	
	public static FormImageViewHolder createViewHolder(ViewGroup parent, Callback callback) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_imageview, parent, false);
		
		return new FormImageViewHolder(view,callback);
		
	}
	
}
