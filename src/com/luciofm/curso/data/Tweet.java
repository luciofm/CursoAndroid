package com.luciofm.curso.data;

public class Tweet {
	String text;
	TwitterUser user;

	public Tweet() {
	}

	public Tweet(String screenname, String name, String text) {
		this.text = text;
		this.user = new TwitterUser(screenname, name);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public TwitterUser getUser() {
		return user;
	}

	public void setUser(TwitterUser user) {
		this.user = user;
	}
}
