package com.railway.management.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    // Constructors

    public User() {
        // Default constructor
    }

    public User(Person person, List<Ticket> tickets) {
        this.person = person;
        this.tickets = tickets;
    }

    // Getters and Setters

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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    // Other methods

    @Override
    public String toString() {
        StringBuilder box = new StringBuilder();
        box.append("+-----------------------------------------------+\n");
        box.append("| User ID: ").append(id).append("\n");
        box.append("| Person: ").append("Name: ").append(person.getName()).append(", Email: ").append(person.getEmail()).append(", Age: ").append(person.getAge()).append(", Address: ").append(person.getAddress()).append(", Phone: ").append(person.getPhone()).append("\n");
        box.append("+-----------------------------------------------+");

        return box.toString();
    }



	public String getName() {
		return person.getName();
	}
}
