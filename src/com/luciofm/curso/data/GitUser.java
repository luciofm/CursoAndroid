package com.luciofm.curso.data;

public class GitUser {
	private String login;
	private String html_url;

	public GitUser() {
		
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getHtml_url() {
		return html_url;
	}
	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

	@Override
	public String toString() {
		return "GitUser [login=" + login + ", html_url=" + html_url + "]";
	}
}
