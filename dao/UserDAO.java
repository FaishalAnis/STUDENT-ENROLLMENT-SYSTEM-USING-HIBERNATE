package com.anudip.app.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.anudip.app.entities.User;

public class UserDAO {
    private SessionFactory factory;

    public UserDAO() {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        factory = config.buildSessionFactory();
    }

    public List<User> getAllUserDetails() {
        Session session = factory.openSession();
        Query<User> query = session.createQuery("from User", User.class);
        List<User> users = query.list();
        session.close();
        return users;
    }

    public void registerUserDetails(User user) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        System.out.println("User registered successfully.");
    }

    public void updateUser(User user) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
        System.out.println("User updated successfully.");
    }

    public void deleteUser(User user) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
        System.out.println("User deleted successfully.");
    }

    public User authenticateUser(String username, String password) {
        Session session = factory.openSession();
        Query<User> query = session.createQuery("from User where username = :username and password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
		User user = query.uniqueResult();
        session.close();
        return user;
    }
}
