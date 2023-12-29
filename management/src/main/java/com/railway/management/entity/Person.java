package com.railway.management.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.*;

import com.railway.management.exceptions.InvalidEmailFormatException;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "age")
    private int age;

    @Column(name = "address")
    private String address;

    @Column(name = "phone", unique = true)
    private Long phone;

    @Column(name = "user_id", unique = true)
    private String userId;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Admin admin;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Employee employee;

    // Constructors

    public Person() {
        // Default constructor
    }

    public Person(String name, String email, int age, String address, Long phone, String userId, String password) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.userId = userId;
        this.password = password;
    }

    // Getters and Setters

    // Other methods

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

    public void setEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            throw new InvalidEmailFormatException();
        }
    }
    private boolean isValidEmail(String email) {
        // Define a simple email regex pattern
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(emailRegex);

        // Create matcher object
        Matcher matcher = pattern.matcher(email);

        // Return whether the email matches the pattern
        return matcher.matches();
    }

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
	    StringBuilder box = new StringBuilder();
	    box.append("+-------------------------------------------------------+\n");
	    box.append("| Person ID: ").append(id).append("\n");
	    box.append("| Name: ").append(name).append("\n");
	    box.append("| Email: ").append(email).append("\n");
	    box.append("| Age: ").append(age).append("\n");
	    box.append("| Address: ").append(address).append("\n");
	    box.append("| Phone: ").append(phone).append("\n");
	    box.append("| User ID: ").append(userId).append("\n");
	    box.append("+-------------------------------------------------------+");

	    return box.toString();
	}
}
