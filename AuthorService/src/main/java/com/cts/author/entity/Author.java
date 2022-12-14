package com.cts.author.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AUTHOR")
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="AUTHOR_ID")
	private long authorId;
	@Column(name="AUTHOR_NAME")
	private String authorName;
	@Column(name="PHONE")
	private String phone;
	@Column(name="EMAIL")
	private String email;
	@Column(name="PWD")
	private String pwd;
	public Author(long authorId, String authorName, String phone, String email, String pwd) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.phone = phone;
		this.email = email;
		this.pwd = pwd;
	}
	public Author() {
		super();
	}
	public long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public int hashCode() {
		return Objects.hash(authorId, authorName, email, phone, pwd);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return authorId == other.authorId && Objects.equals(authorName, other.authorName)
				&& Objects.equals(email, other.email) && Objects.equals(phone, other.phone)
				&& Objects.equals(pwd, other.pwd);
	}
	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", authorName=" + authorName + ", phone=" + phone + ", email=" + email
				+ ", pwd=" + pwd + "]";
	}

	
	
	

}
