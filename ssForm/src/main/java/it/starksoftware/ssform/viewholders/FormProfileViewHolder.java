package it.starksoftware.ssform.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.abdularis.civ.CircleImageView;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.model.FormElementProfileView;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormProfileViewHolder extends FormViewHolder {
	
	
	private Callback mCallback;
	
	public FormProfileViewHolder(View itemView, Callback callback) {
		super(itemView, null, null, FormTypeManager.IS_PROFILE_VIEW, null);
		mCallback = callback;
	}
	
	public void bind(final FormElementProfileView formElement) {
		formElementProfileName.setText(formElement.getProfileName());
		if (formElement.getProfileImage() != null) {
			circleImageView.setImageBitmap(formElement.getProfileImage());
		}
		mCallback.setImageProfilePicker(circleImageView, getAdapterPosition(), layoutRow);
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
		void setImageProfilePicker(CircleImageView imgView, final int position, final LinearLayout layoutRow);
	}
	
	public static FormProfileViewHolder createViewHolder(ViewGroup parent, Callback callback) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_profileview, parent, false);
		
		return new FormProfileViewHolder(view, callback);
		
	}
	
}
