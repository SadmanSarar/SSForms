package it.starksoftware.ssform.viewholders;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.helper.Tools;
import it.starksoftware.ssform.model.FormElementAttach;
import it.starksoftware.ssform.model.FormElementImageMultipleView;
import it.starksoftware.ssform.view.GridSpacingItemDecoration;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormImageViewMultipleViewHolder extends FormViewHolder {
	
	
	private Callback mCallback;
	
	public FormImageViewMultipleViewHolder(View itemView, Callback callback) {
		super(itemView, null, null, FormTypeManager.IS_MULTIPLEIMAGE_VIEW, null);
		mCallback = callback;
	}
	
	public void bind(final FormElementImageMultipleView formElement) {
		mTextViewTitle.setText(formElement.getTitle());
		RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(itemView.getContext(), 10);
		mEditImageViewMultipleValue.setLayoutManager(mLayoutManager);
		mEditImageViewMultipleValue.addItemDecoration(new GridSpacingItemDecoration(2, Tools.dpToPx(itemView.getContext(),10), true));
		mEditImageViewMultipleValue.setItemAnimator(new DefaultItemAnimator());
		mEditImageViewMultipleValue.setAdapter(formElement.getImgAdapter());
		mCallback.setImagePickerMultiple(btnAdd, getAdapterPosition(), formElement.getMaxImages(), layoutRow);
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
		void setImagePickerMultiple(ImageButton imgView, final int position, final int maxImages, final LinearLayout layoutRow);
	}
	
	public static FormImageViewMultipleViewHolder createViewHolder(ViewGroup parent, Callback callback) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_imageview_multiple, parent, false);
		
		return new FormImageViewMultipleViewHolder(view, callback);
		
	}
	
}
