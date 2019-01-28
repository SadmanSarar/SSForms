package it.starksoftware.ssform.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.Map;

import it.starksoftware.ssform.DateSwitcher.DateSwitcher;
import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.interfaces.DateSwitcherCallBack;
import it.starksoftware.ssform.model.FormElementDateSwitcher;

/**
 * Created by Sadman Sarar on 1/27/19.
 */
public class FormDateSwitcherViewHolder extends FormViewHolder {
	
	
	public FormDateSwitcherViewHolder(View itemView) {
		super(itemView, null, null, FormTypeManager.IS_DATE_SWITCHER, null);
	}
	
	public void bind(final FormElementDateSwitcher formElement) {
		mTextViewTitle.setText(formElement.getTitle());
		dateSwitcher.setType(formElement.getDateSwitcherType());
		final DateSwitcherCallBack callback = formElement.getDateSwitcherCallBack();
		dateSwitcher.setOnDateChangeListener(new DateSwitcher.OnDateChangeListener() {
			@Override
			public void onChange(Map<DateSwitcher.DateRange, Date> dateRange) {
				Date topDate    = dateRange.get(DateSwitcher.DateRange.TOP_DATE);
				Date bottomDate = dateRange.get(DateSwitcher.DateRange.BOTTOM_DATE);
				if (callback != null) {
					callback.callbackDateSwitcherReturn(topDate, bottomDate, formElement, formElement.getTag());
				}
			}
		});
	}
	
	public static FormDateSwitcherViewHolder createViewHolder(ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.form_element_date_switcher, parent, false);
		
		return new FormDateSwitcherViewHolder(view);
		
	}
	
}
