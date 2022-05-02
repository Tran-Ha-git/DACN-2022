package com.web.dacn.entity.book;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.dacn.entity.quote.CommentQuote;
import com.web.dacn.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="reviewBook")
public class ReviewBook implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "nvarchar(MAX)")
	private String content;
	private int star;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="mod_time")
	private Date mod_time;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user; 
	
	@ManyToOne
	@JoinColumn(name="book_id")
	@JsonIgnore
	private Book Book; 
	
	@Override
    public int hashCode() {
		 return Objects.hash(getId());
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReviewBook)) {
            return false;
        }
        ReviewBook that = (ReviewBook) obj;
        return  Objects.equals(getId(),that.getId());
    }
}