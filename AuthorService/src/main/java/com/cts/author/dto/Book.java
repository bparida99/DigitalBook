package com.cts.author.dto;

import java.util.Date;
import java.util.Objects;

public class Book {
		private long bookId;
		private String bookName;
		private String publisher;
		private String logo;
		private Long authorId;
		private String authorName;
		private Date publishedDate;
		private String status;
		private Double price;
		public Book() {
			super();
		}
		public Book(long bookId, String bookName, String publisher, String logo, Long authorId, String authorName,
				Date publishedDate, String status, Double price) {
			super();
			this.bookId = bookId;
			this.bookName = bookName;
			this.publisher = publisher;
			this.logo = logo;
			this.authorId = authorId;
			this.authorName = authorName;
			this.publishedDate = publishedDate;
			this.status = status;
			this.price = price;
		}
		public long getBookId() {
			return bookId;
		}
		public void setBookId(long bookId) {
			this.bookId = bookId;
		}
		public String getBookName() {
			return bookName;
		}
		public void setBookName(String bookName) {
			this.bookName = bookName;
		}
		public String getPublisher() {
			return publisher;
		}
		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}
		public String getLogo() {
			return logo;
		}
		public void setLogo(String logo) {
			this.logo = logo;
		}
		public Long getAuthorId() {
			return authorId;
		}
		public void setAuthorId(Long authorId) {
			this.authorId = authorId;
		}
		public String getAuthorName() {
			return authorName;
		}
		public void setAuthorName(String authorName) {
			this.authorName = authorName;
		}
		public Date getPublishedDate() {
			return publishedDate;
		}
		public void setPublishedDate(Date publishedDate) {
			this.publishedDate = publishedDate;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		@Override
		public int hashCode() {
			return Objects.hash(authorId, authorName, bookId, bookName, logo, price, publishedDate, publisher, status);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Book other = (Book) obj;
			return Objects.equals(authorId, other.authorId) && Objects.equals(authorName, other.authorName)
					&& bookId == other.bookId && Objects.equals(bookName, other.bookName)
					&& Objects.equals(logo, other.logo) && Objects.equals(price, other.price)
					&& Objects.equals(publishedDate, other.publishedDate) && Objects.equals(publisher, other.publisher)
					&& Objects.equals(status, other.status);
		}
		@Override
		public String toString() {
			return "Book [bookId=" + bookId + ", bookName=" + bookName + ", publisher=" + publisher + ", logo=" + logo
					+ ", authorId=" + authorId + ", authorName=" + authorName + ", publishedDate=" + publishedDate
					+ ", status=" + status + ", price=" + price + "]";
		}
		
		
}
