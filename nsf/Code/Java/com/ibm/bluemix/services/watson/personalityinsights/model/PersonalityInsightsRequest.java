package com.ibm.bluemix.services.watson.personalityinsights.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Used to send to a IBM Watson PersonaltyInsight request.
 * 
 * @author FDN
 * 
 */
public class PersonalityInsightsRequest {

	private static final String MSG_CONTENTITEMS_EMPTY = "The list of content items is empty..";

	/*
	 * header: text/plain text/html application/json
	 */
	private ContentTypes contentType = ContentTypes.APPLICATION_JSON;

	private ContentLanguage contentLanguage = ContentLanguage.ENGLISH;

	private String source = "HR Assistant";

	/**
	 * Class constructor with given {@link #contentType} and
	 * {@link #contentLanguage}
	 */
	public PersonalityInsightsRequest(ContentTypes contentType,
			ContentLanguage contentLanguage) {
		this.contentType = contentType;
		this.contentLanguage = contentLanguage;
	}

	/**
	 * Class constructor.
	 */
	public PersonalityInsightsRequest() {

	}

	public enum ContentTypes {
		TEXT_PLAIN("text/plain"), TEXT_HTML("text/html"), APPLICATION_JSON(
				"application/json");

		private final String contentTypeValue;

		private ContentTypes(final String s) {
			contentTypeValue = s;
		}

		@JsonValue
		public String toString() {
			return contentTypeValue;
		}
		// further methods, attributes, etc.
	}

	public enum ContentLanguage {
		ARABIC("ar"), ENGLISH("en"), SPANISH("es"), JAPANESE("ja");

		private final String contentLanguageValue;

		private ContentLanguage(final String s) {
			contentLanguageValue = s;
		}

		@JsonValue
		public String toString() {
			return contentLanguageValue;
		}
		// further methods, attributes, etc.
	}

	@JsonProperty("contentItems")
	private List<ContentItem> contentItems = new ArrayList<ContentItem>();

	/**
	 * @return first Element of the setted content items
	 * @throws NoSuchElementException
	 *             if the contentitems are not initialized (0 size)
	 */
	public ContentItem getFirstContentItem() throws NoSuchElementException {
		if (this.contentItems.size() > 0) {
			return contentItems.get(0);
		} else {
			throw new NoSuchElementException(MSG_CONTENTITEMS_EMPTY);
		}
	}

	public List<ContentItem> getContentItems() {
		return contentItems;
	}

	public void setContentItems(List<ContentItem> contentItems) {
		this.contentItems = contentItems;
	}

	public void addContentItem(ContentItem contentItem) {
		this.contentItems.add(contentItem);
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
