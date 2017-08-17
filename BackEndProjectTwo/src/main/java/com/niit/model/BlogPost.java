package com.niit.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class BlogPost {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
	private String title;
	@Lob
	private String description;
	@ManyToOne
	@JoinColumn(name="username")
    private Users createdBy;
	private Date postedOn;
	private boolean approval;
	
	@OneToMany(mappedBy="blogPost",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonManagedReference
	private List<BlogComment> comments;
	public int getId() {
		return id;
	}
	public List<BlogComment> getComments() {
		return comments;
	}
	public void setComments(List<BlogComment> comments) {
		this.comments = comments;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Users getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Users createdBy) {
		this.createdBy = createdBy;
	}
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}
	public boolean isApproval() {
		return approval;
	}
	public void setApproval(boolean approval) {
		this.approval = approval;
	}
}
