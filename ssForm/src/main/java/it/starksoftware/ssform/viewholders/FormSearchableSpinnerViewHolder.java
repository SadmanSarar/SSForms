package it.starksoftware.ssform.viewholders;

import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.model.FormElementSearchableSpinner;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormSearchableSpinnerViewHolder extends FormViewHolder {
	
	
	private Callback mCallback;
	
	public FormSearchableSpinnerViewHolder(View itemView, Callback callback) {
		super(itemView, null, null, FormTypeManager.IS_SEARCHABLE_SPINNER_VIEW, null);
		mCallback = callback;
	}
	
	public void bind(final FormElementSearchableSpinner formElement) {
		mTextViewTitle.setText(formElement.getTitle());
		mTextViewDetail.setText(formElement.getValue());
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
		mCallback.setSearchableSpinnerView(mTextViewDetail, getAdapterPosition(), layoutRow, formElement);
		
	}
	
	public interface Callback {
		void setSearchableSpinnerView(final AppCompatTextView textView, final int position, final LinearLayout layoutRow, final FormElementSearchableSpinner formElementSearchableSpinner);
	}
	
	public static FormSearchableSpinnerViewHolder createViewHolder(ViewGroup parent, Callback callback) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_searchable_spinner, parent, false);
		
		return new FormSearchableSpinnerViewHolder(view, callback);
		
	}
	
}
