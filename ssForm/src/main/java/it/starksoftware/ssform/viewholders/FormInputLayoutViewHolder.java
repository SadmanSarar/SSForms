package it.starksoftware.ssform.viewholders;

import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.listeners.FormCustomEditTextInputLayoutListener;
import it.starksoftware.ssform.model.FormElementInputLayout;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormInputLayoutViewHolder extends FormViewHolder {
	
	
	private Callback mCallback;
	
	public FormInputLayoutViewHolder(View itemView, Callback callback, FormCustomEditTextInputLayoutListener listener) {
		super(itemView, null, listener, FormTypeManager.IS_INPUT_LAYOUT, null);
		mCallback = callback;
	}
	
	public void bind(final FormElementInputLayout formElement) {
		primaryBind();
		editText.setHint(formElement.getmHint());
		//editText.setText(formElement.getValue());
		
		switch (formElement.getType()) {
			case FormElementInputLayout.TYPE_EDITTEXT_TEXT_SINGLELINE:
				editText.setMaxLines(1);
				mCallback.setEditTextInputLayoutFocusEnabled(editText, getAdapterPosition(), layoutRow);
				break;
			case FormElementInputLayout.TYPE_EDITTEXT_TEXT_MULTILINE:
				editText.setSingleLine(false);
				editText.setMaxLines(4);
				mCallback.setEditTextInputLayoutFocusEnabled(editText, getAdapterPosition(), layoutRow);
				break;
			case FormElementInputLayout.TYPE_EDITTEXT_NUMBER:
				editText.setRawInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
				KeyListener keyListenerkeyListenerEDITTEXT_NUMBER = DigitsKeyListener.getInstance("1234567890.");
				editText.setKeyListener(keyListenerkeyListenerEDITTEXT_NUMBER);
				mCallback.setEditTextInputLayoutFocusEnabled(editText, getAdapterPosition(), layoutRow);
				break;
			case FormElementInputLayout.TYPE_EDITTEXT_NUMBER_INTEGER:
				editText.setRawInputType(InputType.TYPE_CLASS_NUMBER);
				KeyListener keyListenerNUMBER_INTEGER = DigitsKeyListener.getInstance("1234567890");
				editText.setKeyListener(keyListenerNUMBER_INTEGER);
				mCallback.setEditTextInputLayoutFocusEnabled(editText, getAdapterPosition(), layoutRow);
				break;
			case FormElementInputLayout.TYPE_EDITTEXT_EMAIL:
				editText.setRawInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
				mCallback.setEditTextInputLayoutFocusEnabled(editText, getAdapterPosition(), layoutRow);
				break;
			case FormElementInputLayout.TYPE_EDITTEXT_PHONE:
				editText.setRawInputType(InputType.TYPE_CLASS_PHONE);
				mCallback.setEditTextInputLayoutFocusEnabled(editText, getAdapterPosition(), layoutRow);
				break;
			case FormElementInputLayout.TYPE_EDITTEXT_PASSWORD:
				editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
				editText.setSelection(editText.getText().length());
				mCallback.setEditTextInputLayoutFocusEnabled(editText, getAdapterPosition(), layoutRow);
				break;
			case FormElementInputLayout.TYPE_PICKER_DATE:
				mCallback.setDatePickerInputLayout(editText, getAdapterPosition(), layoutRow);
				break;
			case FormElementInputLayout.TYPE_PICKER_TIME:
				mCallback.setTimePickerInputLayout(editText, getAdapterPosition(), layoutRow);
				break;
			case FormElementInputLayout.TYPE_SPINNER_DROPDOWN:
				mCallback.setSingleOptionsDialogInputLayout(editText, getAdapterPosition(), layoutRow);
				break;
			case FormElementInputLayout.TYPE_PICKER_MULTI_CHECKBOX:
				mCallback.setMultipleOptionsDialogInputLayout(editText, getAdapterPosition(), layoutRow);
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
		void setEditTextInputLayoutFocusEnabled(final EditText editText, final int position, final LinearLayout layoutRow);
		
		void setDatePickerInputLayout(final EditText editText, final int position, final LinearLayout layoutRow);
		
		void setTimePickerInputLayout(final EditText editText, final int position, final LinearLayout layoutRow);
		
		void setSingleOptionsDialogInputLayout(final EditText editText, final int position, final LinearLayout layoutRow);
		
		void setMultipleOptionsDialogInputLayout(final EditText editText, final int position, final LinearLayout layoutRow);
	}
	
	public static FormInputLayoutViewHolder createViewHolder(ViewGroup parent, Callback callback,FormCustomEditTextInputLayoutListener listener) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_input_layout, parent, false);
		
		return new FormInputLayoutViewHolder(view, callback,listener);
		
	}
	
}
