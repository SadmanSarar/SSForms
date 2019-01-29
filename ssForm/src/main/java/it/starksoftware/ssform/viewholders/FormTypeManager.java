package it.starksoftware.ssform.viewholders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.listeners.FormCustomEditMemoTextListener;
import it.starksoftware.ssform.listeners.FormCustomEditTextInputLayoutListener;
import it.starksoftware.ssform.model.FormDivider;
import it.starksoftware.ssform.model.FormElement;
import it.starksoftware.ssform.model.FormElementAttach;
import it.starksoftware.ssform.model.FormElementButton;
import it.starksoftware.ssform.model.FormElementCheckBox;
import it.starksoftware.ssform.model.FormElementCustomKeyboard;
import it.starksoftware.ssform.model.FormElementDateSwitcher;
import it.starksoftware.ssform.model.FormElementDateTime;
import it.starksoftware.ssform.model.FormElementImageMultipleView;
import it.starksoftware.ssform.model.FormElementImageView;
import it.starksoftware.ssform.model.FormElementInputLayout;
import it.starksoftware.ssform.model.FormElementMemo;
import it.starksoftware.ssform.model.FormElementPlaceDialog;
import it.starksoftware.ssform.model.FormElementProfileView;
import it.starksoftware.ssform.model.FormElementRating;
import it.starksoftware.ssform.model.FormElementSearchableSpinner;
import it.starksoftware.ssform.model.FormElementSegment;
import it.starksoftware.ssform.model.FormElementSignature;
import it.starksoftware.ssform.model.FormElementSmileRating;
import it.starksoftware.ssform.model.FormElementSpinner;
import it.starksoftware.ssform.model.FormElementSwitch;
import it.starksoftware.ssform.model.FormElementToken;
import it.starksoftware.ssform.model.FormHeader;
import it.starksoftware.ssform.model.FormObject;

/**
 * Created by Sadman Sarar on 1/28/19.
 */
public class FormTypeManager {
	
	public static final int IS_HEADER_VIEW             = 0;
	public static final int IS_DEFAULT_VIEW            = 1;
	public static final int IS_SWITCH_VIEW             = 2;
	public static final int IS_IMAGE_VIEW              = 3;
	public static final int IS_SPINNER_VIEW            = 4;
	public static final int IS_MEMO_VIEW               = 5;
	public static final int IS_DIVIDER_VIEW            = 6;
	public static final int IS_SEGMENT_VIEW            = 7;
	public static final int IS_ATTACH_VIEW             = 8;
	public static final int IS_SIGNATURE_VIEW          = 9;
	public static final int IS_RATING_VIEW             = 10;
	public static final int IS_MULTIPLEIMAGE_VIEW      = 11;
	public static final int IS_CUSTOM_KEYBOARD         = 12;
	public static final int IS_DATE_TIME               = 13;
	public static final int IS_SEARCHABLE_SPINNER_VIEW = 14;
	public static final int IS_BUTTON_VIEW             = 15;
	public static final int IS_CHECKBOX_VIEW           = 16;
	public static final int IS_PLACE_DIALOG_VIEW       = 17;
	public static final int IS_TOKEN                   = 18;
	public static final int IS_DATE_SWITCHER           = 19;
	public static final int IS_INPUT_LAYOUT            = 20;
	public static final int IS_PROFILE_VIEW            = 21;
	public static final int IS_SMILE_RATING            = 22;
	
	public int getViewType(String elementType) {
		if (elementType.contentEquals("Header")) {
			return IS_HEADER_VIEW;
		} else if (elementType.contentEquals("Switch")) {
			return IS_SWITCH_VIEW;
		} else if (elementType.contentEquals("ImageView")) {
			return IS_IMAGE_VIEW;
		} else if (elementType.contentEquals("Spinner")) {
			return IS_SPINNER_VIEW;
		} else if (elementType.contentEquals("Memo")) {
			return IS_MEMO_VIEW;
		} else if (elementType.contentEquals("Divider")) {
			return IS_DIVIDER_VIEW;
		} else if (elementType.contentEquals("Segment")) {
			return IS_SEGMENT_VIEW;
		} else if (elementType.contentEquals("Attach")) {
			return IS_ATTACH_VIEW;
		} else if (elementType.contentEquals("Signature")) {
			return IS_SIGNATURE_VIEW;
		} else if (elementType.contentEquals("Rating")) {
			return IS_RATING_VIEW;
		} else if (elementType.contentEquals("ImageViewMultiple")) {
			return IS_MULTIPLEIMAGE_VIEW;
		} else if (elementType.contentEquals("CustomKeyboard")) {
			return IS_CUSTOM_KEYBOARD;
		} else if (elementType.contentEquals("DateTime")) {
			return IS_DATE_TIME;
		} else if (elementType.contentEquals("SearchableSpinner")) {
			return IS_SEARCHABLE_SPINNER_VIEW;
		} else if (elementType.contentEquals("Button")) {
			return IS_BUTTON_VIEW;
		} else if (elementType.contentEquals("CheckBox")) {
			return IS_CHECKBOX_VIEW;
		} else if (elementType.contentEquals("PlaceDialog")) {
			return IS_PLACE_DIALOG_VIEW;
		} else if (elementType.contentEquals("Token")) {
			return IS_TOKEN;
		} else if (elementType.contentEquals("DateSwitcher")) {
			return IS_DATE_SWITCHER;
		} else if (elementType.contentEquals("InputLayout")) {
			return IS_INPUT_LAYOUT;
		} else if (elementType.contentEquals("ProfileView")) {
			return IS_PROFILE_VIEW;
		} else if (elementType.contentEquals("SmileRating")) {
			return IS_SMILE_RATING;
		} else {
			return IS_DEFAULT_VIEW;
		}
	}
	public FormViewHolder creteViewHoolderForType(ViewGroup parent,int viewType, FormAdapter adapter) {
		switch (viewType) {
			case IS_HEADER_VIEW:
				return FormHeaderViewHolder.createViewHolder(parent);
			case IS_SWITCH_VIEW:
				return FormSwitchViewHolder.createViewHolder(parent);
			case IS_IMAGE_VIEW:
				return FormImageViewHolder.createViewHolder(parent, adapter);
			case IS_SPINNER_VIEW:
				return FormSpinnerViewHolder.createViewHolder(parent);
			case IS_MEMO_VIEW:
				return FormMemoViewHolder.createViewHolder(parent, new FormCustomEditMemoTextListener(adapter));
			case IS_DIVIDER_VIEW:
				return FormDividerViewHolder.createViewHolder(parent);
			case IS_SEGMENT_VIEW:
				return FormSegmentViewHolder.createViewHolder(parent);
			case IS_ATTACH_VIEW:
				return FormAttachViewHolder.createViewHolder(parent, adapter);
			case IS_SIGNATURE_VIEW:
				return FormSignatureViewHolder.createViewHolder(parent, adapter);
			case IS_RATING_VIEW:
				return FormRatingViewHolder.createViewHolder(parent);
			case IS_MULTIPLEIMAGE_VIEW:
				return FormImageViewMultipleViewHolder.createViewHolder(parent, adapter);
			case IS_CUSTOM_KEYBOARD:
				return FormCustomKeyboardViewHolder.createViewHolder(parent);
			case IS_DATE_TIME:
				return FormDateTimeViewHolder.createViewHolder(parent, adapter);
			case IS_SEARCHABLE_SPINNER_VIEW:
				return FormSearchableSpinnerViewHolder.createViewHolder(parent, adapter);
			case IS_BUTTON_VIEW:
				return FormButtonViewHolder.createViewHolder(parent);
			case IS_CHECKBOX_VIEW:
				return FormCheckBoxViewHolder.createViewHolder(parent);
			case IS_PLACE_DIALOG_VIEW:
				return FormPlaceDialogViewHolder.createViewHolder(parent, adapter);
			case IS_TOKEN:
				return FormTokenViewHolder.createViewHolder(parent, adapter);
			case IS_DATE_SWITCHER:
				return FormDateSwitcherViewHolder.createViewHolder(parent);
			case IS_INPUT_LAYOUT:
				return FormInputLayoutViewHolder.createViewHolder(parent, adapter, new FormCustomEditTextInputLayoutListener(adapter));
			case IS_PROFILE_VIEW:
				return FormProfileViewHolder.createViewHolder(parent, adapter);
			case IS_SMILE_RATING:
				return FormSmileRatingViewHolder.createViewHolder(parent);
			case IS_DEFAULT_VIEW:
				return FormDefaultViewHolder.createViewHolder(parent, adapter);
			default:
				LayoutInflater inflater = LayoutInflater.from(parent.getContext());
				View v;
				FormViewHolder vh;
				v = inflater.inflate(R.layout.form_element_header, parent, false);
				vh = new FormViewHolder(v, null, null, IS_HEADER_VIEW, null);
				return vh;
			
		}
	}
	
	
	public int getPositionByTag(List<FormObject> dataset,int tag){
		int itemPosition = -1;
		
		for (int index = 0, count = dataset.size(); index < count; ++index) {
			if (dataset.get(index).getElementType().contentEquals("Header")) {
				FormHeader element = (FormHeader) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("Switch")) {
				FormElementSwitch element = (FormElementSwitch) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("ImageView")) {
				FormElementImageView element = (FormElementImageView) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("Spinner")) {
				FormElementSpinner element = (FormElementSpinner) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("Memo")) {
				FormElementMemo element = (FormElementMemo) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("Divider")) {
				FormDivider element = (FormDivider) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("Segment")) {
				FormElementSegment element = (FormElementSegment) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("Attach")) {
				FormElementAttach element = (FormElementAttach) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("Signature")) {
				FormElementSignature element = (FormElementSignature) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("Rating")) {
				FormElementRating element = (FormElementRating) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("Basic")) {
				FormElement element = (FormElement) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("ImageViewMultiple")) {
				FormElementImageMultipleView element = (FormElementImageMultipleView) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("CustomKeyboard")) {
				FormElementCustomKeyboard element = (FormElementCustomKeyboard) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("DateTime")) {
				FormElementDateTime element = (FormElementDateTime) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("SearchableSpinner")) {
				FormElementSearchableSpinner element = (FormElementSearchableSpinner) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("Button")) {
				FormElementButton element = (FormElementButton) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("CheckBox")) {
				FormElementCheckBox element = (FormElementCheckBox) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("PlaceDialog")) {
				FormElementPlaceDialog element = (FormElementPlaceDialog) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("Token")) {
				FormElementToken element = (FormElementToken) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("DateSwitcher")) {
				FormElementDateSwitcher element = (FormElementDateSwitcher) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("InputLayout")) {
				FormElementInputLayout element = (FormElementInputLayout) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("ProfileView")) {
				FormElementProfileView element = (FormElementProfileView) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			} else if (dataset.get(index).getElementType().contentEquals("SmileRating")) {
				FormElementSmileRating element = (FormElementSmileRating) dataset.get(index);
				if (element.getTag() == tag) {
					itemPosition = index;
				}
			}
		}//
		return itemPosition;
	}
	
}
