package com.luciofm.curso.data;

public class Gist {
	private String url;
	private String description;

	private GitUser user;

	public Gist() {
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GitUser getUser() {
		return user;
	}

	public void setUser(GitUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Gist [url=" + url + ", description=" + description + ", user="
				+ user + "]";
	}
}
