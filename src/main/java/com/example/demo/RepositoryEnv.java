package com.example.demo;

public class RepositoryEnv {
	
	private String gitVendor;
	private String gitUserName;
	private String gitPassword;
	
	public String getGitVendor() {
		return gitVendor;
	}
	public void setGitVendor(String gitVendor) {
		this.gitVendor = gitVendor;
	}
	public String getGitUserName() {
		return gitUserName;
	}
	public void setGitUserName(String gitUserName) {
		this.gitUserName = gitUserName;
	}
	public String getGitPassword() {
		return gitPassword;
	}
	public void setGitPassword(String gitPassword) {
		this.gitPassword = gitPassword;
	}
	
	@Override
	public String toString() {
		return "RepositoryEnv [gitVendor=" + gitVendor + ", gitUserName=" + gitUserName + ", gitPassword=" + gitPassword
				+ "]";
	}

}
