package it.starksoftware.ssform.viewholders;

import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.model.FormElementAttach;
import it.starksoftware.ssform.model.FormElementDateTime;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormDateTimeViewHolder extends FormViewHolder {
	
	
	private Callback mCallback;
	
	public FormDateTimeViewHolder(View itemView, Callback callback) {
		super(itemView, null,null, FormTypeManager.IS_DATE_TIME, null);
		mCallback = callback;
	}
	
	public void bind(final FormElementDateTime formElement) {
		mTextViewTitle.setText(formElement.getTitle());
		switch (formElement.getType()) {
			case FormElementDateTime.TYPE_PICKER_DATE:
				if (formElement.getValue() != null) {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
					mTextViewValue.setText(dateFormat.format(formElement.getValue()));
				}
				mCallback.setDatePickerTextView(mTextViewValue, getAdapterPosition(), layoutRow, formElement.getMinDate(), formElement.getMaxDate());
				break;
			case FormElementDateTime.TYPE_PICKER_TIME:
				if (formElement.getValue() != null) {
					DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
					mTextViewValue.setText(dateFormat.format(formElement.getValue()));
				}
				mCallback.setTimePickerTextView(mTextViewValue, getAdapterPosition(), layoutRow);
				break;
			case FormElementDateTime.TYPE_PICKER_DATE_TIME:
				if (formElement.getValue() != null) {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
					mTextViewValue.setText(dateFormat.format(formElement.getValue()));
				}
				mCallback.setDateTimePickerTextView(mTextViewValue, getAdapterPosition(), layoutRow, formElement);
				break;
			default:
				break;
		}
		
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
		void setDatePickerTextView(final TextView textView, final int position, final LinearLayout layoutRow, final Date minDate, final Date maxDate);
		void setDateTimePickerTextView(final TextView textView, final int position, final LinearLayout layoutRow, final FormElementDateTime formElementDateTime);
		void setTimePickerTextView(final TextView textView, final int position, final LinearLayout layoutRow);
	}
	
	public static FormDateTimeViewHolder createViewHolder(ViewGroup parent, Callback callback) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_datetime, parent, false);
		
		return new FormDateTimeViewHolder(view, callback);
		
	}
	
}
