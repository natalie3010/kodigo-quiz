package com.kodigo.quiz.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "admin_user")
public class AdminUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long adid;
	
	private String email;
	private String name;
	private String pin;
	
	//OneToMany relationship - mapping AdminUser to questionPool
	@OneToMany(mappedBy = "adminUser", fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL)
	@JsonIgnore
	private List<QuestionPool> questionPool;
	
	public AdminUser() {}

	

	public AdminUser(Long adid) {
		super();
		this.adid = adid;
	}



	public AdminUser(String email, String name, String pin) {
		this.email = email;
		this.name = name;
		this.pin = pin;
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

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}
	
	public AdminUser getAdminUser() {
		return this;
	}

	

	public Long getAdid() {
		return adid;
	}

	public void setAdid(Long adid) {
		this.adid = adid;
	}

	@Override
	public String toString() {
		return "AdminUser [email=" + email + ", name=" + name + ", pin=" + pin + "]";
	}
	
	

}
