package com.model;

public class NewArticleCommand {

	private int parentId;
	private String content;
	private String title;

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "NewArticleCommand [parentId=" + parentId + ", content=" + content + ", title=" + title + "]";
	}
}
