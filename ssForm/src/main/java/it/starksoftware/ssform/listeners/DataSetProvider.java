package it.starksoftware.ssform.listeners;

import java.util.List;

import it.starksoftware.ssform.model.FormObject;

/**
 * Created by Sadman Sarar on 1/28/19.
 */
public interface DataSetProvider {
	List<FormObject> getDataSet();
}
