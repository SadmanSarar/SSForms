package it.starksoftware.ssform.viewholders;

import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.listeners.FormCustomEditTextListener;
import it.starksoftware.ssform.model.FormElement;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormDefaultViewHolder extends FormViewHolder {
	
	
	private Callback mCallback;
	
	public FormDefaultViewHolder(View itemView, Callback callback, FormCustomEditTextListener listener) {
		super(itemView, listener, null, FormTypeManager.IS_DEFAULT_VIEW, null);
		mCallback = callback;
	}
	
	public void bind(final FormElement formElement) {
		primaryBind();
		mTextViewTitle.setText(formElement.getTitle());
		if (mEditTextValue != null) {
			mEditTextValue.setText(formElement.getValue() != null ? formElement.getValue() : "");
			if (formElement.getValueTextStyle() != null) {
				formElement.getValueTextStyle().format(mEditTextValue);
			}
		}
		
		if (formElement.getTitleTextStyle() != null) {
			formElement.getTitleTextStyle().format(mTextViewTitle);
		}
		
		
		if (formElement.getContainerStyle() != null) {
			formElement.getContainerStyle().format(itemView);
		}
		
		switch (formElement.getType()) {
			case FormElement.TYPE_EDITTEXT_TEXT_SINGLELINE:
				mEditTextValue.setMaxLines(1);
				mCallback.setEditTextFocusEnabled(mEditTextValue, getAdapterPosition(), layoutRow);
				break;
			case FormElement.TYPE_EDITTEXT_TEXT_MULTILINE:
				mEditTextValue.setSingleLine(false);
				mEditTextValue.setMaxLines(4);
				mCallback.setEditTextFocusEnabled(mEditTextValue, getAdapterPosition(), layoutRow);
				break;
			case FormElement.TYPE_EDITTEXT_NUMBER:
				mEditTextValue.setRawInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
				KeyListener keyListenerkeyListenerEDITTEXT_NUMBER = DigitsKeyListener.getInstance("1234567890.");
				mEditTextValue.setKeyListener(keyListenerkeyListenerEDITTEXT_NUMBER);
				mCallback.setEditTextFocusEnabled(mEditTextValue, getAdapterPosition(), layoutRow);
				break;
			case FormElement.TYPE_EDITTEXT_NUMBER_INTEGER:
				mEditTextValue.setRawInputType(InputType.TYPE_CLASS_NUMBER);
				KeyListener keyListenerNUMBER_INTEGER = DigitsKeyListener.getInstance("1234567890");
				mEditTextValue.setKeyListener(keyListenerNUMBER_INTEGER);
				mCallback.setEditTextFocusEnabled(mEditTextValue, getAdapterPosition(), layoutRow);
				break;
			case FormElement.TYPE_EDITTEXT_EMAIL:
				mEditTextValue.setRawInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
				mCallback.setEditTextFocusEnabled(mEditTextValue, getAdapterPosition(), layoutRow);
				break;
			case FormElement.TYPE_EDITTEXT_PHONE:
				mEditTextValue.setRawInputType(InputType.TYPE_CLASS_PHONE);
				mCallback.setEditTextFocusEnabled(mEditTextValue, getAdapterPosition(), layoutRow);
				break;
			case FormElement.TYPE_EDITTEXT_PASSWORD:
				mEditTextValue.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
				mEditTextValue.setSelection(mEditTextValue.getText().length());
				mCallback.setEditTextFocusEnabled(mEditTextValue, getAdapterPosition(), layoutRow);
				break;
			case FormElement.TYPE_PICKER_DATE:
				mCallback.setDatePicker(mEditTextValue, getAdapterPosition(), layoutRow);
				break;
			case FormElement.TYPE_PICKER_TIME:
				mCallback.setTimePicker(mEditTextValue, getAdapterPosition(), layoutRow);
				break;
			case FormElement.TYPE_SPINNER_DROPDOWN:
				mCallback.setSingleOptionsDialog(mEditTextValue, getAdapterPosition(), layoutRow);
				break;
			case FormElement.TYPE_PICKER_MULTI_CHECKBOX:
				mCallback.setMultipleOptionsDialog(mEditTextValue, getAdapterPosition(), layoutRow);
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
	
	public interface Callback {
		void setEditTextFocusEnabled(final AppCompatEditText editText, final int position, final LinearLayout layoutRow);
		
		void setDatePicker(final AppCompatEditText editText, final int position, final LinearLayout layoutRow);
		
		void setTimePicker(final AppCompatEditText editText, final int position, final LinearLayout layoutRow);
		
		void setSingleOptionsDialog(final AppCompatEditText editText, final int position, final LinearLayout layoutRow);
		
		void setMultipleOptionsDialog(final AppCompatEditText editText, final int position, final LinearLayout layoutRow);
	}
	
	public static FormDefaultViewHolder createViewHolder(ViewGroup parent, Callback callback, FormCustomEditTextListener listener) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element, parent, false);
		
		return new FormDefaultViewHolder(view, callback, listener);
		
	}
	
}
