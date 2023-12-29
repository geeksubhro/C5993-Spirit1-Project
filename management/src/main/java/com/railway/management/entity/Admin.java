package com.railway.management.entity;

import javax.persistence.*;

@Entity
@Table(name = "Admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

	@OneToOne
	@JoinColumn(name = "person_id") // Use the correct column name for the foreign key
	private Person person;

    @Column(name = "admin_privilege")
    private String adminPrivilege;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getAdminPrivilege() {
		return adminPrivilege;
	}

	public void setAdminPrivilege(String adminPrivilege) {
		this.adminPrivilege = adminPrivilege;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", person=" + person + ", adminPrivilege=" + adminPrivilege + "]";
	}

	public Admin(int id, Person person, String adminPrivilege) {
		super();
		this.id = id;
		this.person = person;
		this.adminPrivilege = adminPrivilege;
	}
	public Admin(Person person, String adminPrivilege) {
		super();
		this.person = person;
		this.adminPrivilege = adminPrivilege;
	}

	public Admin() {
		super();
	}

}
