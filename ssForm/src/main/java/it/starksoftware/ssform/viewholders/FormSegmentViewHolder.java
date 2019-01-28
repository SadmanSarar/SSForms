package it.starksoftware.ssform.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.interfaces.SegmentCallBack;
import it.starksoftware.ssform.interfaces.SwitchCallBack;
import it.starksoftware.ssform.model.FormElementSegment;
import it.starksoftware.ssform.model.FormElementSwitch;
import it.starksoftware.ssform.segmented.SegmentedGroup;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormSegmentViewHolder extends FormViewHolder {
	
	
	public FormSegmentViewHolder(View itemView) {
		super(itemView, null, null, FormTypeManager.IS_SEGMENT_VIEW, null);
	}
	
	public void bind(final FormElementSegment formElement) {
		mTextViewTitle.setText(formElement.getTitle());
		SegmentedGroup        segmentedGroup  = mEditSegmentGroupValue;
		final SegmentCallBack segmentCallBack = formElement.getSegmentCallBack();
		final RadioButton     rbOne           = segmentedGroup.findViewById(R.id.radioOne);
		final RadioButton     rbTwo           = segmentedGroup.findViewById(R.id.radioTwo);
		rbOne.setText(formElement.getSegmentedButtons().get(0).getText());
		rbOne.setTag(formElement.getSegmentedButtons().get(0).getTag());
		rbOne.setChecked(formElement.getValueCheckA());
		
		rbTwo.setText(formElement.getSegmentedButtons().get(1).getText());
		rbTwo.setTag(formElement.getSegmentedButtons().get(1).getTag());
		rbTwo.setChecked(formElement.getValueCheckB());
		
		segmentedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				if (checkedId == R.id.radioOne) {
					segmentCallBack.callbackSegmentReturn(radioGroup, 0);
					formElement.setValueCheckA(true);
					formElement.setValueCheckB(false);
					rbOne.setChecked(true);
					rbTwo.setChecked(false);
				} else if (checkedId == R.id.radioTwo) {
					segmentCallBack.callbackSegmentReturn(radioGroup, 1);
					formElement.setValueCheckA(false);
					formElement.setValueCheckB(true);
					rbOne.setChecked(false);
					rbTwo.setChecked(true);
				}
			}
		});
		
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
	
	public static FormSegmentViewHolder createViewHolder(ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_segment, parent, false);
		
		return new FormSegmentViewHolder(view);
		
	}
	
}
