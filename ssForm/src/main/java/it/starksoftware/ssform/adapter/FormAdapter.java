package it.starksoftware.ssform.adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.codemybrainsout.placesearch.PlaceSearchDialog;
import com.github.abdularis.civ.CircleImageView;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import it.starksoftware.ssform.R;
import it.starksoftware.ssform.activities.RxAttachPicker;
import it.starksoftware.ssform.activities.RxImagePicker;
import it.starksoftware.ssform.activities.RxSignaturePicker;
import it.starksoftware.ssform.activities.RxTokenPicker;
import it.starksoftware.ssform.attach.AttachPicker;
import it.starksoftware.ssform.features.ImagePicker;
import it.starksoftware.ssform.helper.AppTools;
import it.starksoftware.ssform.interfaces.DateTimeCallBack;
import it.starksoftware.ssform.interfaces.SearchableSpinnerCallBack;
import it.starksoftware.ssform.listeners.DataSetProvider;
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
import it.starksoftware.ssform.model.FormSpinnerObject;
import it.starksoftware.ssform.model.FormTokenObject;
import it.starksoftware.ssform.model.Image;
import it.starksoftware.ssform.model.TokesTags;
import it.starksoftware.ssform.signaturepad.SignaturePicker;
import it.starksoftware.ssform.tokens.TokensPicker;
import it.starksoftware.ssform.viewholders.FormAttachViewHolder;
import it.starksoftware.ssform.viewholders.FormButtonViewHolder;
import it.starksoftware.ssform.viewholders.FormCheckBoxViewHolder;
import it.starksoftware.ssform.viewholders.FormCustomKeyboardViewHolder;
import it.starksoftware.ssform.viewholders.FormDateSwitcherViewHolder;
import it.starksoftware.ssform.viewholders.FormDateTimeViewHolder;
import it.starksoftware.ssform.viewholders.FormDefaultViewHolder;
import it.starksoftware.ssform.viewholders.FormDividerViewHolder;
import it.starksoftware.ssform.viewholders.FormHeaderViewHolder;
import it.starksoftware.ssform.viewholders.FormImageViewHolder;
import it.starksoftware.ssform.viewholders.FormImageViewMultipleViewHolder;
import it.starksoftware.ssform.viewholders.FormInputLayoutViewHolder;
import it.starksoftware.ssform.viewholders.FormMemoViewHolder;
import it.starksoftware.ssform.viewholders.FormPlaceDialogViewHolder;
import it.starksoftware.ssform.viewholders.FormProfileViewHolder;
import it.starksoftware.ssform.viewholders.FormRatingViewHolder;
import it.starksoftware.ssform.viewholders.FormSearchableSpinnerViewHolder;
import it.starksoftware.ssform.viewholders.FormSegmentViewHolder;
import it.starksoftware.ssform.viewholders.FormSignatureViewHolder;
import it.starksoftware.ssform.viewholders.FormSmileRatingViewHolder;
import it.starksoftware.ssform.viewholders.FormSpinnerViewHolder;
import it.starksoftware.ssform.viewholders.FormSwitchViewHolder;
import it.starksoftware.ssform.viewholders.FormTokenViewHolder;
import it.starksoftware.ssform.viewholders.FormTypeManager;
import it.starksoftware.ssform.viewholders.FormViewHolder;
import rx.Observable;
import rx.functions.Action1;

public class FormAdapter extends RecyclerView.Adapter<FormViewHolder>
		implements FormImageViewHolder.Callback,
		DataSetProvider,
		FormAttachViewHolder.Callback,
		FormSignatureViewHolder.Callback,
		FormImageViewMultipleViewHolder.Callback,
		FormDateTimeViewHolder.Callback,
		FormPlaceDialogViewHolder.Callback,
		FormTokenViewHolder.Callback,
		FormInputLayoutViewHolder.Callback,
		FormProfileViewHolder.Callback,
		FormDefaultViewHolder.Callback,
		FormSearchableSpinnerViewHolder.Callback {
	
	// defining marker for header view
	
	
	private FormTypeManager mFormTypeManager = new FormTypeManager();
	
	private ArrayList<Image>  images  = new ArrayList<>();
	private ArrayList<String> attachs = new ArrayList<>();
	private List<FormObject> mDataset;
	private Context          mContext;
	private Activity         mActivity;
	private Calendar         mCalendarCurrentDate;
	private Calendar         mCalendarCurrentTime;
	private SpinnerDialog    spinnerDialog;
	private FormSpinAdapter  spinAdapter;
	private int              clickedPosition;
	private FragmentManager  fManager;
	
	public FormAdapter(Context context, Activity activity, FragmentManager fragmentManager) {
		mContext = context;
		mActivity = activity;
		mDataset = new ArrayList<>();
		mCalendarCurrentDate = Calendar.getInstance();
		mCalendarCurrentTime = Calendar.getInstance();
		clickedPosition = -1;
		fManager = fragmentManager;
	}
	
	public void addElements(List<FormObject> formObjects) {
		this.mDataset = formObjects;
	}
	
	public void addElement(FormObject formObject) {
		
		this.mDataset.add(formObject);
	}
	
	public List<FormObject> getFormItems() {
		return mDataset;
	}
	
	public void removeElement(FormObject formObject) {
		
		this.mDataset.remove(formObject);
	}
	
	public void hideElement(FormObject formObject) {
		
		this.mDataset.remove(formObject);
	}
	
	public void setValueAtIndex(int position, String value) {
		FormElement formElement = (FormElement) mDataset.get(position);
		formElement.setValue(value);
	}
	
	public void setCustomKeyboardValueAtIndex(int position, String value) {
		FormElementCustomKeyboard formElement = (FormElementCustomKeyboard) mDataset.get(position);
		formElement.setValue(value);
	}
	
	public void setValueAtTag(int tag, String value) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElement) {
				FormElement formElement = (FormElement) f;
				if (formElement.getTag() == tag) {
					formElement.setValue(value);
					return;
				}
			}
		}
	}
	
	public void setImageValueAtTag(int tag, Bitmap value) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElementImageView) {
				FormElementImageView formElement = (FormElementImageView) f;
				if (formElement.getTag() == tag) {
					formElement.setValue(value);
					return;
				}
			}
		}
	}
	
	public void setImageMultipleValueAtTag(int tag, List<Bitmap> value) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElementImageMultipleView) {
				FormElementImageMultipleView formElement = (FormElementImageMultipleView) f;
				if (formElement.getTag() == tag) {
					formElement.setValue(value);
					return;
				}
			}
		}
	}
	
	public void setSwitchValueAtTag(int tag, Boolean value) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElementSwitch) {
				FormElementSwitch formElement = (FormElementSwitch) f;
				if (formElement.getTag() == tag) {
					formElement.setValue(value);
					return;
				}
			}
		}
	}
	
	public void setSpinnerValueAtTag(int tag, FormSpinnerObject value) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElementSpinner) {
				FormElementSpinner formElement = (FormElementSpinner) f;
				if (formElement.getTag() == tag) {
					formElement.setValue(value);
					return;
				}
			}
		}
	}
	
	public void setSpinnerPositionatTag(int tag, FormSpinnerObject value) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElementSpinner) {
				FormElementSpinner formElement = (FormElementSpinner) f;
				if (formElement.getTag() == tag) {
					formElement.setValue(value);
					return;
				}
			}
		}
	}
	
	public void setMemoValueAtTag(int tag, String value) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElementMemo) {
				FormElementMemo formElement = (FormElementMemo) f;
				if (formElement.getTag() == tag) {
					formElement.setValue(value);
					return;
				}
			}
		}
	}
	
	public FormElementCustomKeyboard getCustomKeyboardValueAtIndex(int index) {
		return ((FormElementCustomKeyboard) mDataset.get(index));
	}
	
	public FormElement getValueAtIndex(int index) {
		return ((FormElement) mDataset.get(index));
	}
	
	public FormElementMemo getMemoValueAtIndex(int index) {
		return ((FormElementMemo) mDataset.get(index));
	}
	
	//MODIFICHE PER FIX SPINNER
	public FormElementSpinner getSpinnerValueAtIndex(int index) {
		return ((FormElementSpinner) mDataset.get(index));
	}
	
	//
	
	public FormElementCustomKeyboard getCustomKeyboardValueAtTag(int tag) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElementCustomKeyboard) {
				FormElementCustomKeyboard formElement = (FormElementCustomKeyboard) f;
				if (formElement.getTag() == tag) {
					Log.d("FM", "ELEMENT NAME -->" + formElement.getTitle());
					return formElement;
				}
			}
		}
		return null;
	}
	
	public FormElement getValueAtTag(int tag) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElement) {
				FormElement formElement = (FormElement) f;
				if (formElement.getTag() == tag) {
					Log.d("FM", "ELEMENT NAME -->" + formElement.getTitle());
					return formElement;
				}
			}
		}
		return null;
	}
	
	public FormDivider getDividerValueAtTag(int tag) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormDivider) {
				FormDivider formElement = (FormDivider) f;
				if (formElement.getTag() == tag) {
					return formElement;
				}
			}
		}
		return null;
	}
	
	public FormElementImageView getImageValueAtTag(int tag) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElementImageView) {
				FormElementImageView formElement = (FormElementImageView) f;
				if (formElement.getTag() == tag) {
					return formElement;
				}
			}
		}
		return null;
	}
	
	public FormElementImageMultipleView getImageMultipleValueAtTag(int tag) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElementImageMultipleView) {
				FormElementImageMultipleView formElement = (FormElementImageMultipleView) f;
				if (formElement.getTag() == tag) {
					return formElement;
				}
			}
		}
		return null;
	}
	
	public FormElementSwitch getSwitchValueAtTag(int tag) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElementSwitch) {
				FormElementSwitch formElement = (FormElementSwitch) f;
				if (formElement.getTag() == tag) {
					return formElement;
				}
			}
		}
		return null;
	}
	
	public FormElementRating getRatingValueAtTag(int tag) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElementRating) {
				FormElementRating formElement = (FormElementRating) f;
				if (formElement.getTag() == tag) {
					return formElement;
				}
			}
		}
		return null;
	}
	
	
	public FormElementButton getButtonValueAtTag(int tag) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElementButton) {
				FormElementButton formElement = (FormElementButton) f;
				if (formElement.getTag() == tag) {
					return formElement;
				}
			}
		}
		return null;
	}
	
	public FormElementSpinner getSpinnerValueAtTag(int tag) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElementSpinner) {
				FormElementSpinner formElement = (FormElementSpinner) f;
				if (formElement.getTag() == tag) {
					return formElement;
				}
			}
		}
		return null;
	}
	
	public FormElementSearchableSpinner getSearchableSpinnerValueAtTag(int tag) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElementSearchableSpinner) {
				FormElementSearchableSpinner formElement = (FormElementSearchableSpinner) f;
				if (formElement.getTag() == tag) {
					return formElement;
				}
			}
		}
		return null;
	}
	
	public int getPositionByTag(int tag) {
		return mFormTypeManager.getPositionByTag(mDataset, tag);
	}
	
	public FormElementAttach getAttachValueAtTag(int tag) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElementAttach) {
				FormElementAttach formElement = (FormElementAttach) f;
				if (formElement.getTag() == tag) {
					return formElement;
				}
			}
		}
		return null;
	}
	
	
	public FormElementCustomKeyboard getCustomKeyboardElementByTag(int tag) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElementCustomKeyboard) {
				FormElementCustomKeyboard formElement = (FormElementCustomKeyboard) f;
				if (formElement.getTag() == tag) {
					return formElement;
				}
			}
		}
		return null;
	}
	
	public FormElementMemo getMemoValueAtTag(int tag) {
		for (FormObject f : this.mDataset) {
			if (f instanceof FormElementMemo) {
				FormElementMemo formElement = (FormElementMemo) f;
				if (formElement.getTag() == tag) {
					return formElement;
				}
			}
		}
		return null;
	}
	
	
	@Override
	public int getItemCount() {
		return mDataset.size();
	}
	
	
	@Override
	public int getItemViewType(int position) {
		return mFormTypeManager.getViewType(mDataset.get(position).getElementType());
	}

//
	
	@Override
	public FormViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return mFormTypeManager.creteViewHoolderForType(parent, viewType, this);
	}
	
	
	@Override
	public void onBindViewHolder(final FormViewHolder holder, final int position) {
		
		// updates edit text listener index
		if (holder.mFormCustomEditTextListener != null) {
			holder.mFormCustomEditTextListener.updatePosition(holder.getAdapterPosition());
		}
		
		if (holder.mFormCustomEditTextInputLayoutListener != null) {
			holder.mFormCustomEditTextInputLayoutListener.updatePosition(holder.getAdapterPosition());
		}
		
		if (holder.mFormCustomEditMemoTextListener != null) {
			holder.mFormCustomEditMemoTextListener.updatePosition(holder.getAdapterPosition());
		}
		
		FormObject currentObject = mDataset.get(position);
		
		if (getItemViewType(position) == FormTypeManager.IS_HEADER_VIEW && holder instanceof FormHeaderViewHolder) {
			FormHeader formHeader = (FormHeader) currentObject;
			((FormHeaderViewHolder) holder).bind(formHeader);
		} else if (getItemViewType(position) == FormTypeManager.IS_DIVIDER_VIEW) {
			FormDivider formHeader = (FormDivider) currentObject;
			((FormDividerViewHolder) holder).bind(formHeader);
		} else if (getItemViewType(position) == FormTypeManager.IS_DEFAULT_VIEW) {
			FormElement formElement = (FormElement) currentObject;
			((FormDefaultViewHolder) holder).bind(formElement);
			
		} else if (getItemViewType(position) == FormTypeManager.IS_INPUT_LAYOUT) {
			FormElementInputLayout formElement = (FormElementInputLayout) currentObject;
			((FormInputLayoutViewHolder) holder).bind(formElement);
			
		} else if (getItemViewType(position) == FormTypeManager.IS_SWITCH_VIEW) {
			final FormElementSwitch formElement = (FormElementSwitch) currentObject;
			if (holder instanceof FormSwitchViewHolder) {
				((FormSwitchViewHolder) holder).bind(formElement);
			}
		} else if (getItemViewType(position) == FormTypeManager.IS_SEGMENT_VIEW) {
			final FormElementSegment formElement = (FormElementSegment) currentObject;
			((FormSegmentViewHolder) holder).bind(formElement);
		} else if (getItemViewType(position) == FormTypeManager.IS_IMAGE_VIEW) {
			FormElementImageView formElement = (FormElementImageView) currentObject;
			if (holder instanceof FormImageViewHolder) {
				((FormImageViewHolder) holder).bind(formElement);
			}
		} else if (getItemViewType(position) == FormTypeManager.IS_PROFILE_VIEW) {
			FormElementProfileView formElement = (FormElementProfileView) currentObject;
			((FormProfileViewHolder) holder).bind(formElement);
		} else if (getItemViewType(position) == FormTypeManager.IS_TOKEN) {
			final FormElementToken formElement = (FormElementToken) currentObject;
			((FormTokenViewHolder) holder).bind(formElement);
			
		} else if (getItemViewType(position) == FormTypeManager.IS_SIGNATURE_VIEW) {
			FormElementSignature formElement = (FormElementSignature) currentObject;
			((FormSignatureViewHolder) holder).bind(formElement);
		} else if (getItemViewType(position) == FormTypeManager.IS_PLACE_DIALOG_VIEW) {
			FormElementPlaceDialog formElement = (FormElementPlaceDialog) currentObject;
			
		} else if (getItemViewType(position) == FormTypeManager.IS_MEMO_VIEW) {
			FormElementMemo formElement = (FormElementMemo) currentObject;
			((FormMemoViewHolder) holder).bind(formElement);
		} else if (getItemViewType(position) == FormTypeManager.IS_SMILE_RATING) {
			final FormElementSmileRating formElement = (FormElementSmileRating) currentObject;
			((FormSmileRatingViewHolder) holder).bind(formElement);
			
		} else if (getItemViewType(position) == FormTypeManager.IS_DATE_SWITCHER) {
			final FormElementDateSwitcher formElement = (FormElementDateSwitcher) currentObject;
			((FormDateSwitcherViewHolder) holder).bind(formElement);
			
		} else if (getItemViewType(position) == FormTypeManager.IS_BUTTON_VIEW) {
			final FormElementButton formElement = (FormElementButton) currentObject;
			((FormButtonViewHolder) holder).bind(formElement);
			
		} else if (getItemViewType(position) == FormTypeManager.IS_ATTACH_VIEW) {
			FormElementAttach formElement = (FormElementAttach) currentObject;
			((FormAttachViewHolder) holder).bind(formElement);
		} else if (getItemViewType(position) == FormTypeManager.IS_RATING_VIEW) {
			final FormElementRating formElement = (FormElementRating) currentObject;
			((FormRatingViewHolder) holder).bind(formElement);
		} else if (getItemViewType(position) == FormTypeManager.IS_SEARCHABLE_SPINNER_VIEW) {
			final FormElementSearchableSpinner formElement = (FormElementSearchableSpinner) currentObject;
			((FormSearchableSpinnerViewHolder) holder).bind(formElement);
		} else if (getItemViewType(position) == FormTypeManager.IS_SPINNER_VIEW) {
			FormElementSpinner formElement = (FormElementSpinner) currentObject;
			if (holder instanceof FormSpinnerViewHolder) {
				((FormSpinnerViewHolder) holder).bind(formElement);
			}
		} else if (getItemViewType(position) == FormTypeManager.IS_MULTIPLEIMAGE_VIEW) {
			FormElementImageMultipleView formElement = (FormElementImageMultipleView) currentObject;
			((FormImageViewMultipleViewHolder) holder).bind(formElement);
		} else if (getItemViewType(position) == FormTypeManager.IS_CHECKBOX_VIEW) {
			final FormElementCheckBox formElement = (FormElementCheckBox) currentObject;
			((FormCheckBoxViewHolder) holder).bind(formElement);
		} else if (getItemViewType(position) == FormTypeManager.IS_CUSTOM_KEYBOARD) {
			final FormElementCustomKeyboard formElement = (FormElementCustomKeyboard) currentObject;
			((FormCustomKeyboardViewHolder) holder).bind(formElement);
			
		} else if (getItemViewType(position) == FormTypeManager.IS_DATE_TIME) {
			final FormElementDateTime formElement = (FormElementDateTime) currentObject;
			((FormDateTimeViewHolder) holder).bind(formElement);
		}
	}
	
	@Override
	public void setTokenPicker(ImageButton imgButton, final int position, final FormElementToken formElement) {
		imgButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clickedPosition = position;
				getTokensPickerObservable(formElement.getTokensObject()).forEach(actionTokens);
			}
		});
	}
	
	@Override
	public void setSignaturePicker(ImageView imgView, final int position, final LinearLayout layoutRow) {
		imgView.setFocusableInTouchMode(false);
		imgView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clickedPosition = position;
				getSignaturePickerObservable().forEach(actionSignature);
			}
		});
	}
	
	@Override
	public void setPlaceDialogPicker(final TextView tv, final int position, final LinearLayout layoutRow, final FormElementPlaceDialog formElement) {
		
		View.OnClickListener click = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clickedPosition = position;
				PlaceSearchDialog placeSearchDialog = new PlaceSearchDialog.Builder(formElement.getmCtx())
						.setHintText(formElement.getDialogTitle())
						.setNegativeText("CANCEL")
						.setNegativeTextColor(R.color.gray)
						.setPositiveText("SUBMIT")
						.setPositiveTextColor(R.color.red)
						.setLocationNameListener(new PlaceSearchDialog.LocationNameListener() {
							@Override
							public void locationName(String locationName) {
								((FormElementPlaceDialog) mDataset.get(clickedPosition)).setValue(locationName);
								tv.setText(locationName);
							}
						})
						.build();
				placeSearchDialog.show();
				
			}
		};
		tv.setOnClickListener(click);
		layoutRow.setOnClickListener(click);
		
	}
	
	
	@Override
	public void setImagePickerMultiple(ImageButton imgView, final int position, final int maxImages, final LinearLayout layoutRow) {
		imgView.setFocusableInTouchMode(false);
		imgView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clickedPosition = position;
				getImageMultiplePickerObservable(maxImages).forEach(actionMultipleImages);
			}
		});
	}
	
	@Override
	public void setImagePicker(ImageView imgView, final int position, final LinearLayout layoutRow) {
		imgView.setFocusableInTouchMode(false);
		layoutRow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clickedPosition = position;
				getImagePickerObservable().forEach(action);
			}
		});
	}
	
	@Override
	public void setImageProfilePicker(CircleImageView imgView, final int position, final LinearLayout layoutRow) {
		imgView.setFocusableInTouchMode(false);
		imgView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clickedPosition = position;
				getImageProfilePickerObservable().forEach(actionProfile);
			}
		});
	}
	
	@Override
	public void setAttachPicker(TextView tv, final int position, final LinearLayout layoutRow) {
		tv.setFocusableInTouchMode(false);
		layoutRow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clickedPosition = position;
				getAttachPickerObservable(attachs).forEach(actionAttach);
			}
		});
	}
	
	
	Action1<ArrayList<String>> actionAttach = new Action1<ArrayList<String>>() {
		@Override
		public void call(ArrayList<String> attach) {
			if (attach != null) {
				
				attachs = new ArrayList<String>();
				for (int p = 0; p < attach.size(); p++)
					attachs.add(attach.get(p));
				((FormElementAttach) mDataset.get(clickedPosition)).setValue(String.valueOf(attach.size()));
				((FormElementAttach) mDataset.get(clickedPosition)).setOptions(attachs);
				notifyItemChanged(clickedPosition);
			}
		}
	};
	
	Action1<Bitmap> actionSignature = new Action1<Bitmap>() {
		@Override
		public void call(Bitmap images) {
			if (images != null) {
				AppTools appTools = new AppTools();
				((FormElementSignature) mDataset.get(clickedPosition)).setValue(images);
				notifyItemChanged(clickedPosition);
			}
		}
	};
	
	public View createCustomToken(Context context, final int pos, final ArrayList<FormTokenObject> selectedTokens, final int posFormItem) {
		View     view   = View.inflate(context, R.layout.token_item, null);
		TextView tvChip = view.findViewById(R.id.tvChip);
		tvChip.setText(selectedTokens.get(pos).getValue());
		ImageView ivClose = view.findViewById(R.id.ivClose);
		ivClose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				selectedTokens.remove(selectedTokens.get(pos));
				ArrayList<TokesTags> tokensView = new ArrayList<>();
				for (int i = 0; i < selectedTokens.size(); i++) {
					View      v    = createCustomToken(mContext, i, selectedTokens, clickedPosition);
					TokesTags item = new TokesTags();
					item.setTokenItem(v);
					tokensView.add(item);
				}
				((FormElementToken) mDataset.get(clickedPosition)).setValue(tokensView);
				notifyItemChanged(clickedPosition);
			}
		});
		return view;
	}
	
	Action1<ArrayList<FormTokenObject>> actionTokens = new Action1<ArrayList<FormTokenObject>>() {
		@Override
		public void call(ArrayList<FormTokenObject> tokens) {
			if (tokens != null) {
				ArrayList<TokesTags> tokensView = new ArrayList<>();
				for (int i = 0; i < tokens.size(); i++) {
					View      v    = createCustomToken(mContext, i, tokens, clickedPosition);
					TokesTags item = new TokesTags();
					item.setTokenItem(v);
					tokensView.add(item);
				}
				AppTools appTools = new AppTools();
				((FormElementToken) mDataset.get(clickedPosition)).setValue(tokensView);
				notifyItemChanged(clickedPosition);
			}
		}
	};
	
	Action1<List<Image>> actionProfile = new Action1<List<Image>>() {
		@Override
		public void call(List<Image> images) {
			if (images != null) {
				AppTools appTools = new AppTools();
				Uri      imageUri = Uri.fromFile(new File(images.get(0).getPath()));
				
				try {
					int    rotateImage = appTools.getCameraPhotoOrientation((Activity) mContext, imageUri);
					Bitmap bmp         = appTools.getThumbnail(imageUri, rotateImage, mContext);
					
					Glide.with((Activity) mContext)
							.load(appTools.bitmapToByte(bmp))
							.asBitmap()
							.into(new SimpleTarget<Bitmap>() {
								@Override
								public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
									((FormElementProfileView) mDataset.get(clickedPosition)).setProfileImage(resource);
									notifyItemChanged(clickedPosition);
								}
							});
				} catch (Exception ex) {
					String sX = ex.toString();
					String gg = "";
				}
			}
		}
	};
	
	Action1<List<Image>> action = new Action1<List<Image>>() {
		@Override
		public void call(List<Image> images) {
			if (images != null) {
				AppTools appTools = new AppTools();
				Uri      imageUri = Uri.fromFile(new File(images.get(0).getPath()));
				
				try {
					int    rotateImage = appTools.getCameraPhotoOrientation((Activity) mContext, imageUri);
					Bitmap bmp         = appTools.getThumbnail(imageUri, rotateImage, mContext);
					
					Glide.with((Activity) mContext)
							.load(appTools.bitmapToByte(bmp))
							.asBitmap()
							.into(new SimpleTarget<Bitmap>() {
								@Override
								public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
									((FormElementImageView) mDataset.get(clickedPosition)).setValue(resource);
									notifyItemChanged(clickedPosition);
								}
							});
				} catch (Exception ex) {
					String sX = ex.toString();
					String gg = "";
				}
			}
		}
	};
	
	Action1<List<Image>> actionMultipleImages = new Action1<List<Image>>() {
		@Override
		public void call(List<Image> images) {
			if (images != null) {
				AppTools     appTools = new AppTools();
				Uri          imageUri;
				List<Bitmap> listRes  = new ArrayList<Bitmap>();
				
				for (int i = 0; i < images.size(); i++) {
					try {
						imageUri = Uri.fromFile(new File(images.get(i).getPath()));
						int    rotateImage = appTools.getCameraPhotoOrientation((Activity) mContext, imageUri);
						Bitmap bmp         = appTools.getThumbnail(imageUri, rotateImage, mContext);
						listRes.add(bmp);
					} catch (Exception ex) {
						String sX = ex.toString();
						String gg = "";
					}
				}
				
				ImageCardAdapter adapter = new ImageCardAdapter(mActivity, listRes);
				((FormElementImageMultipleView) mDataset.get(clickedPosition)).setImgAdapter(adapter);
				((FormElementImageMultipleView) mDataset.get(clickedPosition)).setValue(listRes);
				notifyItemChanged(clickedPosition);
			}
		}
	};
	
	private Observable<ArrayList<String>> getAttachPickerObservable(ArrayList<String> currentItems) {
		return RxAttachPicker.getInstance().start(mContext, AttachPicker.create((Activity) mContext), currentItems);
	}
	
	private Observable<List<Image>> getImagePickerObservable() {
		return RxImagePicker.getInstance().start(mContext, ImagePicker.create((Activity) mContext), 0);
	}
	
	private Observable<List<Image>> getImageProfilePickerObservable() {
		return RxImagePicker.getInstance().start(mContext, ImagePicker.create((Activity) mContext), 0);
	}
	
	private Observable<List<Image>> getImageMultiplePickerObservable(int maxImages) {
		return RxImagePicker.getInstance().start(mContext, ImagePicker.create((Activity) mContext), maxImages);
	}
	
	private Observable<Bitmap> getSignaturePickerObservable() {
		return RxSignaturePicker.getInstance().start(mContext, SignaturePicker.create((Activity) mContext));
	}
	
	private Observable<ArrayList<FormTokenObject>> getTokensPickerObservable(ArrayList<FormTokenObject> objectTokens) {
		return RxTokenPicker.getInstance().start(mContext, TokensPicker.create((Activity) mContext), objectTokens);
	}
	
	@Override
	public void setEditTextFocusEnabled(final AppCompatEditText editText, final int position, final LinearLayout layoutRow) {
		editText.setFocusableInTouchMode(true);
		editText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				editText.requestFocus();
				InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
			}
		});
		
		layoutRow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				editText.requestFocus();
				InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
			}
		});
	}
	
	@Override
	public void setEditTextInputLayoutFocusEnabled(final EditText editText, final int position, final LinearLayout layoutRow) {
		editText.setFocusableInTouchMode(true);
		editText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				editText.requestFocus();
				InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
			}
		});
		
		layoutRow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				editText.requestFocus();
				InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
			}
		});
	}
	
	@Override
	public void setDatePicker(final AppCompatEditText editText, final int position, final LinearLayout layoutRow) {
		editText.setFocusableInTouchMode(false);
		
		editText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				editText.requestFocus();
				InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
			}
		});
		
		layoutRow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clickedPosition = position;
				DatePickerDialog datePickerDialog = new DatePickerDialog(
						mContext,
						date,
						mCalendarCurrentDate.get(Calendar.YEAR),
						mCalendarCurrentDate.get(Calendar.MONTH),
						mCalendarCurrentDate.get(Calendar.DAY_OF_MONTH)
				);
				
				// this could be used to set a minimum date
				// datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
				
				// display the picker
				datePickerDialog.show();
			}
		});
		
	}
	
	@Override
	public void setDatePickerInputLayout(final EditText editText, final int position, final LinearLayout layoutRow) {
		editText.setFocusableInTouchMode(false);
		
		editText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				editText.requestFocus();
				InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
			}
		});
		
		layoutRow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clickedPosition = position;
				DatePickerDialog datePickerDialog = new DatePickerDialog(
						mContext,
						date,
						mCalendarCurrentDate.get(Calendar.YEAR),
						mCalendarCurrentDate.get(Calendar.MONTH),
						mCalendarCurrentDate.get(Calendar.DAY_OF_MONTH)
				);
				
				// this could be used to set a minimum date
				// datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
				
				// display the picker
				datePickerDialog.show();
			}
		});
		
	}
	
	@Override
	public void setDatePickerTextView(final TextView textView, final int position, final LinearLayout layoutRow, final Date minDate, final Date maxDate) {
		
		textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clickedPosition = position;
				DatePickerDialog datePickerDialog = new DatePickerDialog(
						mContext,
						dateTextView,
						mCalendarCurrentDate.get(Calendar.YEAR),
						mCalendarCurrentDate.get(Calendar.MONTH),
						mCalendarCurrentDate.get(Calendar.DAY_OF_MONTH)
				);
				
				if (maxDate != null) {
					datePickerDialog.getDatePicker().setMaxDate(maxDate.getTime());
				}
				
				if (minDate != null) {
					datePickerDialog.getDatePicker().setMinDate(minDate.getTime());
				}
				
				datePickerDialog.show();
			}
		});
		
		layoutRow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clickedPosition = position;
				DatePickerDialog datePickerDialog = new DatePickerDialog(
						mContext,
						dateTextView,
						mCalendarCurrentDate.get(Calendar.YEAR),
						mCalendarCurrentDate.get(Calendar.MONTH),
						mCalendarCurrentDate.get(Calendar.DAY_OF_MONTH)
				);
				
				if (maxDate != null) {
					datePickerDialog.getDatePicker().setMaxDate(maxDate.getTime());
				}
				
				if (minDate != null) {
					datePickerDialog.getDatePicker().setMinDate(minDate.getTime());
				}
				datePickerDialog.show();
			}
		});
		
	}
	
	@Override
	public void setTimePicker(final AppCompatEditText editText, final int position, final LinearLayout layoutRow) {
		
		editText.setFocusableInTouchMode(false);
		
		editText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				editText.requestFocus();
				InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
			}
		});
		
		layoutRow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				// saves clicked position for further reference
				clickedPosition = position;
				
				// prepares time picker dialog
				TimePickerDialog timePickerDialog = new TimePickerDialog(
						mContext,
						time,
						mCalendarCurrentDate.get(Calendar.HOUR_OF_DAY),
						mCalendarCurrentDate.get(Calendar.MINUTE),
						true
				);
				
				// display the picker
				timePickerDialog.show();
			}
		});
		
	}
	
	@Override
	public void setTimePickerInputLayout(final EditText editText, final int position, final LinearLayout layoutRow) {
		
		editText.setFocusableInTouchMode(false);
		
		editText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				editText.requestFocus();
				InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
			}
		});
		
		layoutRow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				// saves clicked position for further reference
				clickedPosition = position;
				
				// prepares time picker dialog
				TimePickerDialog timePickerDialog = new TimePickerDialog(
						mContext,
						time,
						mCalendarCurrentDate.get(Calendar.HOUR_OF_DAY),
						mCalendarCurrentDate.get(Calendar.MINUTE),
						true
				);
				
				// display the picker
				timePickerDialog.show();
			}
		});
		
	}
	
	@Override
	public void setSearchableSpinnerView(final AppCompatTextView textView, final int position, final LinearLayout layoutRow, final FormElementSearchableSpinner formElementSearchableSpinner) {
		
		final SearchableSpinnerCallBack ratingCallBack = formElementSearchableSpinner.getCallback();
		
		ArrayList<String> items = formElementSearchableSpinner.getItems();
		spinnerDialog = new SpinnerDialog(mActivity, items, formElementSearchableSpinner.getDialogTitle(), "Annulla");
		spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
			@Override
			public void onClick(String item, int position) {
				FormSpinnerObject spinnerItem = formElementSearchableSpinner.getSpinnerObject().get(position);
				textView.setText(spinnerItem.getValue());
				ratingCallBack.callbackSearchableSpinnerReturn(formElementSearchableSpinner, formElementSearchableSpinner.getTag(), spinnerItem);
			}
		});
		
		
		textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clickedPosition = position;
				spinnerDialog.showSpinerDialog();
			}
		});
		
		layoutRow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clickedPosition = position;
				spinnerDialog.showSpinerDialog();
			}
		});
	}
	
	
	@Override
	public void setDateTimePickerTextView(final TextView textView, final int position, final LinearLayout layoutRow, final FormElementDateTime formElementDateTime) {
		final SwitchDateTimeDialogFragment dateTimeFragment;
		dateTimeFragment = SwitchDateTimeDialogFragment.newInstance(
				"Impostazione Data e Ora",
				"Conferma",
				"Annulla"
		);
		
		dateTimeFragment.set24HoursMode(true);
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -1);  // number of days to add
		
		final DateTimeCallBack dateTimeCallBack = formElementDateTime.getCallback();
		
		dateTimeFragment.setMinimumDateTime(formElementDateTime.getMinDate());
		dateTimeFragment.setMaximumDateTime(new GregorianCalendar(2050, Calendar.DECEMBER, 31).getTime());
		try {
			dateTimeFragment.setSimpleDateMonthAndDayFormat(new SimpleDateFormat("MMMM dd", Locale.getDefault()));
		} catch (SwitchDateTimeDialogFragment.SimpleDateMonthAndDayFormatException e) {
		
		
		}
		
		dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonWithNeutralClickListener() {
			@Override
			public void onPositiveButtonClick(Date date) {
				
				
				if (clickedPosition >= 0) {
					((FormElementDateTime) mDataset.get(clickedPosition)).setValue(date);
					notifyItemChanged(clickedPosition);
					clickedPosition = -1;
					dateTimeCallBack.callbackDateTimeReturn(date, formElementDateTime, formElementDateTime.getTag());
				}
				
			}
			
			@Override
			public void onNegativeButtonClick(Date date) {
			
			
			}
			
			@Override
			public void onNeutralButtonClick(Date date) {
			
			
			}
		});
		
		dateTimeFragment.startAtCalendarView();
		dateTimeFragment.setDefaultDateTime(new Date());
		String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";
		//dateTimeFragment.show(mc.getFragmentManager(), TAG_DATETIME_FRAGMENT);
		
		textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clickedPosition = position;
				dateTimeFragment.setDefaultDateTime(((FormElementDateTime) mDataset.get(clickedPosition)).getDefaultDate());
				dateTimeFragment.show(fManager, "TAG_DATETIME_FRAGMENT");
			}
		});
		
		layoutRow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clickedPosition = position;
				dateTimeFragment.setDefaultDateTime(((FormElementDateTime) mDataset.get(clickedPosition)).getDefaultDate());
				dateTimeFragment.show(fManager, "TAG_DATETIME_FRAGMENT");
			}
		});
	}
	
	@Override
	public void setTimePickerTextView(final TextView textView, final int position, final LinearLayout layoutRow) {
		
		textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				// saves clicked position for further reference
				clickedPosition = position;
				
				// prepares time picker dialog
				TimePickerDialog timePickerDialog = new TimePickerDialog(
						mContext,
						timeTextView,
						mCalendarCurrentDate.get(Calendar.HOUR_OF_DAY),
						mCalendarCurrentDate.get(Calendar.MINUTE),
						true
				);
				
				// display the picker
				timePickerDialog.show();
			}
		});
		
		layoutRow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				// saves clicked position for further reference
				clickedPosition = position;
				
				// prepares time picker dialog
				TimePickerDialog timePickerDialog = new TimePickerDialog(
						mContext,
						timeTextView,
						mCalendarCurrentDate.get(Calendar.HOUR_OF_DAY),
						mCalendarCurrentDate.get(Calendar.MINUTE),
						true
				);
				
				// display the picker
				timePickerDialog.show();
			}
		});
		
	}
	
	@Override
	public void setSingleOptionsDialog(final AppCompatEditText editText, final int position, final LinearLayout layoutRow) {
		
		// get the element
		final FormElement currentObj = (FormElement) mDataset.get(position);
		
		editText.setFocusableInTouchMode(false);
		
		// reformat the options in format needed
		final CharSequence[] options = new CharSequence[currentObj.getOptions().size()];
		for (int i = 0; i < currentObj.getOptions().size(); i++) {
			options[i] = currentObj.getOptions().get(i);
		}
		
		// prepare the dialog
		final AlertDialog dialog = new AlertDialog.Builder(mContext)
				.setTitle("Pick one")
				.setItems(options, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						editText.setText(options[which]);
						currentObj.setValue(options[which].toString());
						notifyItemChanged(clickedPosition);
					}
				})
				.create();
		
		// display the dialog on click
		layoutRow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dialog.show();
			}
		});
		
	}
	
	@Override
	public void setSingleOptionsDialogInputLayout(final EditText editText, final int position, final LinearLayout layoutRow) {
		
		// get the element
		final FormElementInputLayout currentObj = (FormElementInputLayout) mDataset.get(position);
		
		editText.setFocusableInTouchMode(false);
		
		// reformat the options in format needed
		final CharSequence[] options = new CharSequence[currentObj.getOptions().size()];
		for (int i = 0; i < currentObj.getOptions().size(); i++) {
			options[i] = currentObj.getOptions().get(i);
		}
		
		// prepare the dialog
		final AlertDialog dialog = new AlertDialog.Builder(mContext)
				.setTitle("Pick one")
				.setItems(options, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						editText.setText(options[which]);
						currentObj.setValue(options[which].toString());
						notifyItemChanged(clickedPosition);
					}
				})
				.create();
		
		// display the dialog on click
		layoutRow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dialog.show();
			}
		});
		
	}
	
	@Override
	public void setMultipleOptionsDialog(final AppCompatEditText editText, final int position, final LinearLayout layoutRow) {
		
		// get the element
		final FormElement currentObj = (FormElement) mDataset.get(position);
		editText.setFocusableInTouchMode(false);
		
		// reformat the options in format needed
		final CharSequence[]     options         = new CharSequence[currentObj.getOptions().size()];
		final boolean[]          optionsSelected = new boolean[currentObj.getOptions().size()];
		final ArrayList<Integer> mSelectedItems  = new ArrayList();
		
		for (int i = 0; i < currentObj.getOptions().size(); i++) {
			options[i] = currentObj.getOptions().get(i);
			optionsSelected[i] = false;
			
			if (currentObj.getOptionsSelected().contains(options[i])) {
				optionsSelected[i] = true;
				mSelectedItems.add(i);
			}
		}
		
		// prepare the dialog
		final AlertDialog dialog = new AlertDialog.Builder(mContext)
				.setTitle("Pick one or more")
				.setMultiChoiceItems(options, optionsSelected,
									 new DialogInterface.OnMultiChoiceClickListener() {
										 @Override
										 public void onClick(DialogInterface dialog, int which,
															 boolean isChecked) {
											 if (isChecked) {
												 // If the user checked the item, add it to the selected items
												 mSelectedItems.add(which);
											 } else if (mSelectedItems.contains(which)) {
												 // Else, if the item is already in the array, remove it
												 mSelectedItems.remove(Integer.valueOf(which));
											 }
										 }
									 }
				)
				// Set the action buttons
				.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						String s = "";
						for (int i = 0; i < mSelectedItems.size(); i++) {
							s += options[mSelectedItems.get(i)];
							
							if (i < mSelectedItems.size() - 1) {
								s += ", ";
							}
						}
						editText.setText(s);
						((FormElement) mDataset.get(position)).setValue(s);
						notifyItemChanged(clickedPosition);
					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
					
					}
				})
				.create();
		
		// display the dialog on click
		layoutRow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dialog.show();
			}
		});
		
		editText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dialog.show();
			}
		});
	}
	
	@Override
	public void setMultipleOptionsDialogInputLayout(final EditText editText, final int position, final LinearLayout layoutRow) {
		
		// get the element
		final FormElementInputLayout currentObj = (FormElementInputLayout) mDataset.get(position);
		editText.setFocusableInTouchMode(false);
		
		// reformat the options in format needed
		final CharSequence[]     options         = new CharSequence[currentObj.getOptions().size()];
		final boolean[]          optionsSelected = new boolean[currentObj.getOptions().size()];
		final ArrayList<Integer> mSelectedItems  = new ArrayList();
		
		for (int i = 0; i < currentObj.getOptions().size(); i++) {
			options[i] = currentObj.getOptions().get(i);
			optionsSelected[i] = false;
			
			if (currentObj.getOptionsSelected().contains(options[i])) {
				optionsSelected[i] = true;
				mSelectedItems.add(i);
			}
		}
		
		// prepare the dialog
		final AlertDialog dialog = new AlertDialog.Builder(mContext)
				.setTitle("Pick one or more")
				.setMultiChoiceItems(options, optionsSelected,
									 new DialogInterface.OnMultiChoiceClickListener() {
										 @Override
										 public void onClick(DialogInterface dialog, int which,
															 boolean isChecked) {
											 if (isChecked) {
												 // If the user checked the item, add it to the selected items
												 mSelectedItems.add(which);
											 } else if (mSelectedItems.contains(which)) {
												 // Else, if the item is already in the array, remove it
												 mSelectedItems.remove(Integer.valueOf(which));
											 }
										 }
									 }
				)
				// Set the action buttons
				.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						String s = "";
						for (int i = 0; i < mSelectedItems.size(); i++) {
							s += options[mSelectedItems.get(i)];
							
							if (i < mSelectedItems.size() - 1) {
								s += ", ";
							}
						}
						editText.setText(s);
						((FormElement) mDataset.get(position)).setValue(s);
						notifyItemChanged(clickedPosition);
					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
					
					}
				})
				.create();
		
		// display the dialog on click
		layoutRow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dialog.show();
			}
		});
		
		editText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dialog.show();
			}
		});
	}
	
	@Override
	public List<FormObject> getDataSet() {
		return mDataset;
	}
	
	public class FormCustomEditTextListener implements TextWatcher {
		private int position;
		
		public void updatePosition(int position) {
			this.position = position;
		}
		
		@Override
		public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
			// no op
		}
		
		@Override
		public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
			FormElement formElement = (FormElement) mDataset.get(position);
			formElement.setValue(charSequence.toString());
		}
		
		@Override
		public void afterTextChanged(Editable editable) {
			// no op
		}
	}
	
	
	DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			mCalendarCurrentDate.set(Calendar.YEAR, year);
			mCalendarCurrentDate.set(Calendar.MONTH, monthOfYear);
			mCalendarCurrentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			
			String           myFormatDate = "dd/MM/yy"; // custom format
			SimpleDateFormat sdfDate      = new SimpleDateFormat(myFormatDate, Locale.US);
			
			// act only if clicked position is a valid index
			if (clickedPosition >= 0) {
				((FormElement) mDataset.get(clickedPosition)).setValue(sdfDate.format(mCalendarCurrentDate.getTime()));
				notifyItemChanged(clickedPosition);
				clickedPosition = -1;
			}
		}
		
	};
	
	
	TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mCalendarCurrentTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
			mCalendarCurrentTime.set(Calendar.MINUTE, minute);
			
			String           myFormatTime = "HH:mm"; // custom format
			SimpleDateFormat sdfTime      = new SimpleDateFormat(myFormatTime, Locale.getDefault());
			
			// act only if clicked position is a valid index
			if (clickedPosition >= 0) {
				((FormElement) mDataset.get(clickedPosition)).setValue(sdfTime.format(mCalendarCurrentTime.getTime()));
				notifyItemChanged(clickedPosition);
				clickedPosition = -1;
			}
		}
	};
	
	DatePickerDialog.OnDateSetListener dateTextView = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			mCalendarCurrentDate.set(Calendar.YEAR, year);
			mCalendarCurrentDate.set(Calendar.MONTH, monthOfYear);
			mCalendarCurrentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			
			String           myFormatDate = "dd/MM/yy"; // custom format
			SimpleDateFormat sdfDate      = new SimpleDateFormat(myFormatDate, Locale.US);
			
			// act only if clicked position is a valid index
			if (clickedPosition >= 0) {
				((FormElementDateTime) mDataset.get(clickedPosition)).setValue(mCalendarCurrentDate.getTime());
				notifyItemChanged(clickedPosition);
				clickedPosition = -1;
			}
		}
		
	};
	
	
	TimePickerDialog.OnTimeSetListener timeTextView = new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mCalendarCurrentTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
			mCalendarCurrentTime.set(Calendar.MINUTE, minute);
			
			String           myFormatTime = "HH:mm"; // custom format
			SimpleDateFormat sdfTime      = new SimpleDateFormat(myFormatTime, Locale.getDefault());
			
			// act only if clicked position is a valid index
			if (clickedPosition >= 0) {
				((FormElementDateTime) mDataset.get(clickedPosition)).setValue(mCalendarCurrentTime.getTime());
				notifyItemChanged(clickedPosition);
				clickedPosition = -1;
			}
		}
	};
	
}