package com.railway.management.dao.Impl;

import com.railway.management.dao.UserDAO;
import com.railway.management.entity.Ticket;
import com.railway.management.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }
    }

    @Override
    public List<Ticket> getTicketsByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Ticket WHERE user = :user", Ticket.class)
                    .setParameter("user", user)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // handle the exception appropriately
        }
    }


    @Override
    public void changeUserPassword(User user, String newPassword) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Fetch the user from the database
            User existingUser = session.get(User.class, user.getId());

            // Update the user's password
            existingUser.getPerson().setPassword(newPassword);

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
