package com.railway.management.entity;

import javax.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private int empId;
    @Column(name = "salary")
    private int salary;

    public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
	    StringBuilder box = new StringBuilder();
	    box.append("+----------------------------------------+\n");
	    box.append("| Employee ID: ").append(empId).append("\n");
	    box.append("| Salary: ").append(salary).append("\n");
	    box.append("| Person Details:").append("\n");
	    box.append("|   - ID: ").append(person.getId()).append("\n");
	    box.append("|   - Name: ").append(person.getName()).append("\n");
	    box.append("|   - Email: ").append(person.getEmail()).append("\n");
	    box.append("|   - Age: ").append(person.getAge()).append("\n");
	    box.append("|   - Address: ").append(person.getAddress()).append("\n");
	    box.append("|   - Phone: ").append(person.getPhone()).append("\n");
	    box.append("|   - User ID: ").append(person.getUserId()).append("\n");
	    box.append("+----------------------------------------+");

	    return box.toString();
	}


	public Employee( int salary, Person person) {
		super();
		this.salary = salary;
		this.person = person;
	}

	public Employee(int salary) {
		super();
		this.salary = salary;
	}

	public Employee() {
		super();
	}

	@OneToOne
	@JoinColumn(name = "person_id") // Use the correct column name for the foreign key
	private Person person;
}
