package com.example.ede67167.userDB;

/**
 * Userdata entity. @author MyEclipse Persistence Tools
 */

public class Userdata implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private String name;
	private String password;

	// Constructors

	/** default constructor */
	public Userdata() {
	}

	/** full constructor */
	public Userdata(String userId, String name, String password) {
		this.userId = userId;
		this.name = name;
		this.password = password;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}