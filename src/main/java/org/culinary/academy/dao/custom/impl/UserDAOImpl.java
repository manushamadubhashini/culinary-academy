package org.culinary.academy.dao.custom.impl;

import org.culinary.academy.config.SessionFactoryConfig;
import org.culinary.academy.dao.custom.UserDAO;
import org.culinary.academy.entity.Student;
import org.culinary.academy.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean save(User entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionFactoryConfig.getInstance().getSession();
            transaction = session.beginTransaction();

            session.save(entity);

            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;

        } finally {
            session.close();
        }
    }


    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;

    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public User search(String id) {
        Session session=null;
        Transaction transaction=null;
        try {
            session=SessionFactoryConfig.getInstance().getSession();
            transaction=session.beginTransaction();
            User user=session.get(User.class,Integer.parseInt(id));
            transaction.commit();
            return user;
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }

    }

    @Override
    public User searchByUsername(String username) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionFactoryConfig.getInstance().getSession();
            transaction = session.beginTransaction();
            User user = (User ) session.createQuery("FROM User WHERE userName = :username")
                    .setParameter("username", username)
                    .uniqueResult();
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
