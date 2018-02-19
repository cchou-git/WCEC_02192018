package org.wcec.retreat.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the user_has_category database table.
 * 
 */
@Entity
@Table(name="category")
@NamedQuery(name="Category.findAll", query="SELECT u FROM Category u")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id 
	@Column(name = "ID", unique = true, nullable = false)
	private Integer categoryId;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
	private List<User> users;
	
	public Category() {
	}
	public Integer getCategoryId() {
		return this.categoryId;
	}
	
	
	public List<User> getUsers() {
		return this.users;
	}

	public void ListUsers(List<User> Users) {
		this.users = Users;
	}
	  
}