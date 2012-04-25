package com.luciofm.curso.data;

public class TwitterUser {
	String screen_name;
	String name;

	public TwitterUser() {

	}

	public TwitterUser(String screen_name, String name) {
		this.screen_name = screen_name;
		this.name = name;
	}

	public String getScreen_name() {
		return screen_name;
	}

	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
