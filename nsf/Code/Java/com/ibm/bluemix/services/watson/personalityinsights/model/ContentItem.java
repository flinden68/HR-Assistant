package com.ibm.bluemix.services.watson.personalityinsights.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibm.bluemix.services.watson.personalityinsights.model.PersonalityInsightsResult.ContentLanguage;
import com.ibm.bluemix.services.watson.personalityinsights.model.PersonalityInsightsResult.ContentTypes;

public class ContentItem {

	private String content = "";
	private String id = "";

	@JsonProperty("userid")
	private String userId = "";

	@JsonProperty("sourceid")
	private String sourceId = "";

	private long updated = 0;

	private ContentTypes contentType = ContentTypes.TEXT_PLAIN;

	private ContentLanguage contentLanguage = ContentLanguage.ENGLISH;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public long getUpdated() {
		return updated;
	}

	public void setUpdated(long updated) {
		this.updated = updated;
	}

	public ContentTypes getContentType() {
		return contentType;
	}

	public void setContentType(ContentTypes contentType) {
		this.contentType = contentType;
	}

	public ContentLanguage getContentLanguage() {
		return contentLanguage;
	}

	public void setContentLanguage(ContentLanguage contentLanguage) {
		this.contentLanguage = contentLanguage;
	}

}
