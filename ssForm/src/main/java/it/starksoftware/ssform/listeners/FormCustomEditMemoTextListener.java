package it.starksoftware.ssform.listeners;

import android.text.Editable;
import android.text.TextWatcher;

import it.starksoftware.ssform.model.FormElementMemo;

/**
 * Created by Sadman Sarar on 1/28/19.
 */
public class FormCustomEditMemoTextListener implements TextWatcher {
	private int position;
	
	private DataSetProvider mDataSetProvider;
	
	public FormCustomEditMemoTextListener(DataSetProvider dataSetProvider) {
		mDataSetProvider = dataSetProvider;
	}
	
	public void updatePosition(int position) {
		this.position = position;
	}
	
	@Override
	public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
		// no op
	}
	
	@Override
	public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
		FormElementMemo formElement = (FormElementMemo) mDataSetProvider.getDataSet().get(position);
		formElement.setValue(charSequence.toString());
	}
	
	@Override
	public void afterTextChanged(Editable editable) {
		// no op
	}
}
