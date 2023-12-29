package com.railway.management.dao;

import com.railway.management.entity.Ticket;
import com.railway.management.entity.User;

import java.util.List;

public interface UserDAO {

    void saveUser(User user);

    User getUserById(int id);

    List<User> getAllUsers();

	void updateUser(User user);

	List<Ticket> getTicketsByUser(User user);

	void changeUserPassword(User user, String newPassword);
}
