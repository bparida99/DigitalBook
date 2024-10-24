package com.cts.reader.entity;

import jakarta.persistence.*;

@Entity
@Table(name="READER")
public class Reader {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="READER_ID")
	private long readerId;
	@Column(name="READER_NAME")
	private String readerName;
	@Column(name="READER_EMAIL")
	private String readerEmail;
	@Column(name="READER_PHONE")
	private String readerPhone;
	@Column(name="READER_PWD")
	private String pwd;
	public Reader() {
		super();
	}
	public Reader(long readerId, String readerName, String readerEmail, String readerPhone, String pwd) {
		super();
		this.readerId = readerId;
		this.readerName = readerName;
		this.readerEmail = readerEmail;
		this.readerPhone = readerPhone;
		this.pwd = pwd;
	}
	public long getReaderId() {
		return readerId;
	}
	public void setReaderId(long readerId) {
		this.readerId = readerId;
	}
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	public String getReaderEmail() {
		return readerEmail;
	}
	public void setReaderEmail(String readerEmail) {
		this.readerEmail = readerEmail;
	}
	public String getReaderPhone() {
		return readerPhone;
	}
	public void setReaderPhone(String readerPhone) {
		this.readerPhone = readerPhone;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "Reader [readerId=" + readerId + ", readerName=" + readerName + ", readerEmail=" + readerEmail
				+ ", readerPhone=" + readerPhone + ", pwd=" + pwd +  "]";
	}
	
	
}
