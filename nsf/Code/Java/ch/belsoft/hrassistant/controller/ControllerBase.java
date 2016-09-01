package ch.belsoft.hrassistant.controller;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Comparator;
import java.util.List;

import ch.belsoft.hrassistant.attachment.controller.AttachmentController;
import ch.belsoft.hrassistant.attachment.model.Attachment;
import ch.belsoft.hrassistant.attachment.model.AttachmentHolder;
import ch.belsoft.hrassistant.attachment.model.Upload;
import ch.belsoft.hrassistant.dao.IDataItem;
import ch.belsoft.hrassistant.job.model.JobApplication;
import ch.belsoft.hrassistant.model.DataItem;
import ch.belsoft.tools.Logging;
import ch.belsoft.tools.Util;
import ch.belsoft.tools.XPagesUtil;

public abstract class ControllerBase implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private static final String PARAM_ID = "id";
	private static final String PARAM_READONLY = "readonly";
	protected static String PAGETITLE_NEW = "New Data Item";
	private static String INFO_CREATED = "Created on {CREATED}.";
	private static String INFO_CREATED_MODIFIED = "Created on {CREATED}. Modified on {MODIFIED}";
	private static String INFO_CREATED_REPLACE = "{CREATED}";
	private static String INFO_MODIFIED_REPLACE = "{MODIFIED}";
	protected static String PAGETITLE_EXISTING = "Data Item {NAME}: {DESCRIPTION}";
	protected static final String PAGETITLE_REPLACE_NAME = "{NAME}";
	protected static final String PAGETITLE_REPLACE_DESCRIPTION = "{DESCRIPTION}";

	protected ApplicationController applicationController = null;
	protected AttachmentController attachmentController;
	protected Upload upload = null;
	protected AttachmentHolder attachmentHolder = null;

	protected boolean newDataItem = false;
	protected boolean readOnly = true | false;

	protected String searchQuery = "";

	public ControllerBase() {
		String p = XPagesUtil.getQueryString(PARAM_READONLY);
		this.readOnly = Util.toBoolean(p);
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	protected String getId() {
		return XPagesUtil.getQueryString(PARAM_ID);
	}

	protected void handleException(Exception e) {
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		XPagesUtil.showErrorMessage(errors.toString());
	}

	public String getCreatedModifiedInfo(IDataItem dataItem) {
		String result = "";
		try {
			String created = XPagesUtil.getDateTimeStringLocalized(dataItem
					.getCreated());
			if (dataItem.getId() != null && "".equals(dataItem.getId())) {
				result = INFO_CREATED.replace(INFO_CREATED_REPLACE, created);
			} else {
				String modified = XPagesUtil
						.getDateTimeStringLocalized(dataItem.getModified());
				result = INFO_CREATED_MODIFIED.replace(INFO_CREATED_REPLACE,
						created);
				result = result.replace(INFO_MODIFIED_REPLACE, modified);
			}

		} catch (Exception e) {
			Logging.logError(e);
		}
		return result;
	}

	public ApplicationController getApplicationController() {
		return applicationController;
	}

	public void setApplicationController(
			ApplicationController applicationController) {
		this.applicationController = applicationController;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	/*
	 * public void postSearch(List<?> searchedDataItems) { try { if
	 * (searchedDataItems == null || searchedDataItems.size() == 0) { XPagesUtil
	 * .showErrorMessage("not items found with search query: " +
	 * this.searchQuery, "pnlSearchInfoNoItemsFound"); } } catch (Exception e) {
	 * Logging.logError(e); } }
	 */

	public int getListCount(List<?> list) {
		int result = 0;
		try {
			if (list != null) {
				result = list.size();
			}
		} catch (Exception e) {
			Logging.logError(e);
		}
		return result;

	}

	public void clearSearchQuery() {
		this.searchQuery = "";
	}

	public void loadAttachmnents(IDataItem dataItem) {
		setAttachmentHolder(attachmentController.findAttachment(dataItem
				.getAttachmentId()));
	}

	public void removeAttachments() {
		attachmentController.remove(attachmentHolder);
	}

	public void creatAttachment(IDataItem dataItem) {
		attachmentHolder = attachmentController
				.createNewAttachmentHolderFromUpload(upload, null);
		attachmentController.create(attachmentHolder);
		dataItem.setAttachmentId(attachmentHolder.getId());
	}

	public void updateAttachments(IDataItem dataItem) {
		try {
			if ((this.newDataItem || "".equals(dataItem.getAttachmentId()))
					&& upload.getUploadFile() != null) {
				creatAttachment(dataItem);
			} else {
				if (!"".equals(dataItem.getAttachmentId())
						&& upload.getUploadFile() != null) {
					// update
					Attachment attachment = attachmentController
							.createAttachmentFromUpload(upload);
					attachmentHolder.addAttachment(attachment.getName(),
							attachment);
					attachmentController.update(attachmentHolder);
				}
			}
		} catch (Exception e) {
			handleException(e);
			Logging.logError(e);
		}
	}

	public boolean hasAttachments() {
		return attachmentHolder != null
				&& !attachmentHolder.getAttachments().isEmpty();
	}

	/*
	 * GETTERS AND SETTERS
	 */

	public AttachmentController getAttachmentController() {
		return attachmentController;
	}

	public void setAttachmentController(
			AttachmentController attachmentController) {
		this.attachmentController = attachmentController;
	}

	public Upload getUpload() {
		return upload;
	}

	public void setUpload(Upload upload) {
		this.upload = upload;
	}

	public AttachmentHolder getAttachmentHolder() {
		return attachmentHolder;
	}

	public void setAttachmentHolder(AttachmentHolder attachmentHolder) {
		this.attachmentHolder = attachmentHolder;
	}

	public static final Comparator<IDataItem> CreatedComparator = new Comparator<IDataItem>() {
		public int compare(IDataItem o1, IDataItem o2) {
			return o2.getCreated().compareTo(o1.getCreated());
		}
	};

}
