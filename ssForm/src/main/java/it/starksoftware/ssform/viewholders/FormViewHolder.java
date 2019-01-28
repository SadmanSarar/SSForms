package it.starksoftware.ssform.viewholders;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.github.abdularis.civ.CircleImageView;
import com.google.android.flexbox.FlexboxLayout;
import com.hsalf.smilerating.SmileRating;

import it.starksoftware.ssform.DateSwitcher.DateSwitcher;
import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.listeners.FormCustomEditMemoTextListener;
import it.starksoftware.ssform.listeners.FormCustomEditTextInputLayoutListener;
import it.starksoftware.ssform.ratings.BaseRatingBar;
import it.starksoftware.ssform.segmented.SegmentedGroup;

/**
 * Created by Sadman Sarar on 1/22/19.
 */
public class FormViewHolder extends RecyclerView.ViewHolder {
	
	public LinearLayout                           layoutRow;
	public LinearLayout                           linearLayout;
	public AppCompatTextView                      mTextViewTitle;
	public Button                                 mButtonTitle;
	public AppCompatTextView                                 mTextViewDetail;
	public AppCompatTextView                                 mTextViewOptions;
	public AppCompatEditText                                 mEditTextValue;
	public AppCompatEditText                                 mEditMemoTextValue;
	public TextView                                          mTextViewValue;
	public Switch                                            mEditSwitchValue;
	public CheckBox                               mEditCheckBoxValue;
	public ImageView                              mEditImageViewValue;
	public TextView                               mTextViewAttachValue;
	public BaseRatingBar                          mEditRatingValue;
	public Spinner                                mEditSpinnerValue;
	public SegmentedGroup                         mEditSegmentGroupValue;
	public FormAdapter.FormCustomEditTextListener mFormCustomEditTextListener;
	public FormCustomEditTextInputLayoutListener  mFormCustomEditTextInputLayoutListener;
	public FormCustomEditMemoTextListener         mFormCustomEditMemoTextListener;
	public RecyclerView                           mEditImageViewMultipleValue;
	public ImageButton                            btnAdd;
	public FlexboxLayout                          tokens;
	public ImageButton                            btnAddTokens;
	public DateSwitcher                           dateSwitcher;
	public EditText                               editText;
	public CircleImageView                                   circleImageView;
	public AppCompatTextView                                 formElementProfileName;
	public SmileRating                                       mSmileValue;
	
	public FormViewHolder(View v, FormAdapter.FormCustomEditTextListener listener, FormCustomEditTextInputLayoutListener listenerInputLayout, int viewType, FormCustomEditMemoTextListener memoTextListener) {
		super(v);
		mTextViewTitle = (AppCompatTextView) v.findViewById(R.id.formElementTitle);
		mTextViewOptions = (AppCompatTextView) v.findViewById(R.id.formElementTitle);
		linearLayout = (LinearLayout) v.findViewById(R.id.itemContainer);
		layoutRow = (LinearLayout) v.findViewById(R.id.layoutRow);
		
		if (viewType == 1) {
			mEditTextValue = (AppCompatEditText) v.findViewById(R.id.formElementValue);
			mFormCustomEditTextListener = listener;
			if (mEditTextValue != null)
				mEditTextValue.addTextChangedListener(mFormCustomEditTextListener);
		} else if (viewType == 2) {
			mEditSwitchValue = (Switch) v.findViewById(R.id.formElementValue);
		} else if (viewType == 3) {
			mEditImageViewValue = (ImageView) v.findViewById(R.id.formElementValue);
		} else if (viewType == 9) {
			mEditImageViewValue = (ImageView) v.findViewById(R.id.formElementValue);
		} else if (viewType == 4) {
			mEditSpinnerValue = (Spinner) v.findViewById(R.id.formElementValue);
		} else if (viewType == 7) {
			mEditSegmentGroupValue = (SegmentedGroup) v.findViewById(R.id.formElementValue);
		} else if (viewType == 8) {
			mTextViewAttachValue = (TextView) v.findViewById(R.id.formElementValue);
		} else if (viewType == 10) {
			mEditRatingValue = (BaseRatingBar) v.findViewById(R.id.formElementValue);
		} else if (viewType == 5) {
			mEditMemoTextValue = (AppCompatEditText) v.findViewById(R.id.formMemoElementValue);
			mFormCustomEditMemoTextListener = memoTextListener;
			if (mEditMemoTextValue != null)
				mEditMemoTextValue.addTextChangedListener(mFormCustomEditMemoTextListener);
		} else if (viewType == 11) {
			mEditImageViewMultipleValue = (RecyclerView) v.findViewById(R.id.formElementValue);
			btnAdd = (ImageButton) v.findViewById(R.id.btnAdd);
		} else if (viewType == 12) {
			mEditTextValue = (AppCompatEditText) v.findViewById(R.id.formElementValue);
		} else if (viewType == 13) {
			mTextViewValue = (TextView) v.findViewById(R.id.formElementTextViewValue);
		} else if (viewType == 14) {
			mTextViewDetail = (AppCompatTextView) v.findViewById(R.id.formElementValue);
		} else if (viewType == 15) {
			mButtonTitle = (Button) v.findViewById(R.id.btnAction);
		} else if (viewType == 16) {
			mEditCheckBoxValue = (CheckBox) v.findViewById(R.id.formElementValue);
		} else if (viewType == 17) {
			mTextViewValue = (TextView) v.findViewById(R.id.formElementTextViewValue);
		} else if (viewType == 18) {
			tokens = (FlexboxLayout) v.findViewById(R.id.tokens);
			btnAddTokens = (ImageButton) v.findViewById(R.id.btnAddTokens);
		} else if (viewType == 19) {
			dateSwitcher = (DateSwitcher) v.findViewById(R.id.mDateSwitcher);
		} else if (viewType == 20) {
			editText = (EditText) v.findViewById(R.id.formElementValue);
			mFormCustomEditTextInputLayoutListener = listenerInputLayout;
			if (editText != null)
				editText.addTextChangedListener(mFormCustomEditTextInputLayoutListener);
		} else if (viewType == 21) {
			circleImageView = (CircleImageView) v.findViewById(R.id.circleImageView);
			formElementProfileName = (AppCompatTextView) v.findViewById(R.id.formElementProfileName);
		} else if (viewType == 22) {
			mSmileValue = (SmileRating) v.findViewById(R.id.ratingView);
			
		}
		
	}
	
	
	
	void primaryBind(){
		if (mFormCustomEditTextListener != null) {
			mFormCustomEditTextListener.updatePosition(getAdapterPosition());
		}
		
		if (mFormCustomEditTextInputLayoutListener != null) {
			mFormCustomEditTextInputLayoutListener.updatePosition(getAdapterPosition());
		}
		
		if (mFormCustomEditMemoTextListener != null) {
			mFormCustomEditMemoTextListener.updatePosition(getAdapterPosition());
		}
	}
	
}

