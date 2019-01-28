package it.starksoftware.ssform.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.interfaces.SegmentCallBack;
import it.starksoftware.ssform.model.FormElementAttach;
import it.starksoftware.ssform.model.FormElementSegment;
import it.starksoftware.ssform.segmented.SegmentedGroup;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormAttachViewHolder extends FormViewHolder {
	
	
	private Callback mCallback;
	
	public FormAttachViewHolder(View itemView, Callback callback) {
		super(itemView, null,null, FormTypeManager.IS_ATTACH_VIEW, null);
		mCallback = callback;
	}
	
	public void bind(final FormElementAttach formElement) {
		mTextViewTitle.setText(formElement.getTitle());
		mTextViewAttachValue.setText(formElement.getValue());
		mCallback.setAttachPicker(mTextViewAttachValue, getAdapterPosition(), layoutRow);
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
		void setAttachPicker(TextView tv, final int position, final LinearLayout layoutRow);
	}
	
	public static FormAttachViewHolder createViewHolder(ViewGroup parent, Callback callback) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_attach, parent, false);
		
		return new FormAttachViewHolder(view,callback);
		
	}
	
}
