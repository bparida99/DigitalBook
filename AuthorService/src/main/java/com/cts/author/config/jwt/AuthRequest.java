package com.cts.author.config.jwt;

import java.util.Objects;

public class AuthRequest {

    private String userName;
    private String password;
	public AuthRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public AuthRequest() {
		super();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		return Objects.hash(password, userName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthRequest other = (AuthRequest) obj;
		return Objects.equals(password, other.password) && Objects.equals(userName, other.userName);
	}
	@Override
	public String toString() {
		return "AuthRequest [userName=" + userName + ", password=" + password + "]";
	}
	
	
    
}