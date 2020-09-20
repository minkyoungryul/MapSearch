package com.kakaobank.project.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kakaobank.project.common.domain.BaseModel;

@Entity
@Table(name="MEMBER")
public class Member extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="EMAIL", nullable = false)
	private String email;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PASSWORD")
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", resultCode="+ super.getResultCode() +"]";
	}
	
	
	
}
