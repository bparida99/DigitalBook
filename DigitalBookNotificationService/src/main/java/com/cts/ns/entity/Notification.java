package com.cts.ns.entity;

import jakarta.persistence.*;

import java.util.Objects;



@Entity
@Table(name="NOTIFICATION")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Notification_ID")
	private Long id;
	@Column(name="User_ID")
	private Long userId;
	@Column(name="Message")
	private String msg;
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Notification(Long id, Long userId, String msg) {
		super();
		this.id = id;
		this.userId = userId;
		this.msg = msg;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, msg, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notification other = (Notification) obj;
		return Objects.equals(id, other.id) && Objects.equals(msg, other.msg) && Objects.equals(userId, other.userId);
	}
	@Override
	public String toString() {
		return "Notification [id=" + id + ", userId=" + userId + ", msg=" + msg + "]";
	}
	
	
}
