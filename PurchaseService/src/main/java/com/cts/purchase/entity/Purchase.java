package com.cts.purchase.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PURCHASE")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="BOOKING_ID")
	private long booking_id;
	@Column(name="BOOK_ID")
	private long bookId;
	@Column(name="BOOK_NAME")
	private String book_name;
	@Column(name="USER_ID")
	private long userId;
	@Column(name="USER_NAME")
	private String userName;
	@Column(name="PURCHASE_DATE")
	private Date purchseDate;
	@Column(name="EXPIRY_DATE")
	private Date expiryDate;
	@Column(name="CANCELED_BY")
	private String canceledBy;
	@Column(name="REFUND")
	private String refund;
	public Purchase() {
		super();
	}
	public Purchase(long booking_id, long bookId, String book_name, long userId, String userName, Date purchseDate,
			Date expiryDate, String canceledBy, String refund) {
		super();
		this.booking_id = booking_id;
		this.bookId = bookId;
		this.book_name = book_name;
		this.userId = userId;
		this.userName = userName;
		this.purchseDate = purchseDate;
		this.expiryDate = expiryDate;
		this.canceledBy = canceledBy;
		this.refund = refund;
	}
	public long getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(long booking_id) {
		this.booking_id = booking_id;
	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getPurchseDate() {
		return purchseDate;
	}
	public void setPurchseDate(Date purchseDate) {
		this.purchseDate = purchseDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getCanceledBy() {
		return canceledBy;
	}
	public void setCanceledBy(String canceledBy) {
		this.canceledBy = canceledBy;
	}
	public String getRefund() {
		return refund;
	}
	public void setRefund(String refund) {
		this.refund = refund;
	}
	@Override
	public int hashCode() {
		return Objects.hash(bookId, book_name, booking_id, canceledBy, expiryDate, purchseDate, refund, userId,
				userName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Purchase other = (Purchase) obj;
		return bookId == other.bookId && Objects.equals(book_name, other.book_name) && booking_id == other.booking_id
				&& Objects.equals(canceledBy, other.canceledBy) && Objects.equals(expiryDate, other.expiryDate)
				&& Objects.equals(purchseDate, other.purchseDate) && Objects.equals(refund, other.refund)
				&& userId == other.userId && Objects.equals(userName, other.userName);
	}
	@Override
	public String toString() {
		return "Purchase [booking_id=" + booking_id + ", bookId=" + bookId + ", book_name=" + book_name + ", userId="
				+ userId + ", userName=" + userName + ", purchseDate=" + purchseDate + ", expiryDate=" + expiryDate
				+ ", canceledBy=" + canceledBy + ", refund=" + refund + "]";
	}
	
	
	
	
	
}
