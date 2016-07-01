package ch.belsoft.hrassistant.dao;

import java.util.Date;
import java.util.List;

import ch.belsoft.hrassistant.model.DataItem;

public interface IDataItem {

	public String getRev();

	public void setRev(String rev);

	public String getId();

	public void setId(String id);

	public DataItem.DataType getDataType();

	public Date getCreated();

	public void setCreated(Date created);

	public Date getModified();

	public void setModified(Date modified);

}
