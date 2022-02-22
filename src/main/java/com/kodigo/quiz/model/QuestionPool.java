package com.kodigo.quiz.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pool")
public class QuestionPool {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long qpid;
	
	private String name;
	
	//OneToMany relationship child - fetching AdminUser to questionPool
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "adid", nullable = false)
	private AdminUser adminUser;
	
	//OneToMany relationship parent - mapping QuestionPool to questionGroup
	@JsonIgnore
	@OneToMany(mappedBy = "questionPool", fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL)
	private List<QuestionGroup> questionGroup;
	
	public QuestionPool() {}

	public QuestionPool(String name, AdminUser adminUser) {
		super();
		this.name = name;
		this.adminUser = adminUser;
	}

	public QuestionPool(String name) {
		super();
		this.name = name;
	}

	public QuestionPool(Long qpid) {
		super();
		this.qpid = qpid;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public AdminUser getAdminUser() {
		return adminUser;
	}


	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Long getQpid() {
		return qpid;
	}

	public void setQpid(Long qpid) {
		this.qpid = qpid;
	}

	@Override
	public String toString() {
		return "QuestionPool [qpid=" + qpid + ", name=" + name + ", adminUser=" + adminUser + ", questionGroup="
				+ questionGroup + "]";
	}
}

