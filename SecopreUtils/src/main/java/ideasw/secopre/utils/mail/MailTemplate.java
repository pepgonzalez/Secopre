package ideasw.secopre.utils.mail;

public enum MailTemplate {
	PLAIN_TEXT("simple_mail", new String[] { "title", "fullName", "message",
			"URL" }), HTML("simple_mail", new String[] { "title", "fullName",
			"message", "URL" }), HTML_IMAGES_IN_LINE("advance_template",
			new String[] { "title", "fullName", "message", "URL" });

	private String nameTemplate;
	private String[] attributes;

	MailTemplate(String nameTemplate, String[] attributes) {
		this.nameTemplate = nameTemplate;
		this.attributes = attributes;
	}

	/**
	 * @return the nameTemplate
	 */
	public String getNameTemplate() {
		return nameTemplate;
	}

	/**
	 * @param nameTemplate
	 *            the nameTemplate to set
	 */
	public void setNameTemplate(String nameTemplate) {
		this.nameTemplate = nameTemplate;
	}

	/**
	 * @return the attributes
	 */
	public String[] getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes
	 *            the attributes to set
	 */
	public void setAttributes(String[] attributes) {
		this.attributes = attributes;
	}
}
