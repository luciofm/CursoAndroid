package com.luciofm.curso;

public class TwitterPost {
	String user;
	String text;

	public TwitterPost(String user, String text) {
		this.user = user;
		this.text = text;
	}

	public String getUser() {
		return user;
	}

	public String getText() {
		return text;
	}
}
