package org.culinary.academy.dao.custom.impl;

import org.culinary.academy.config.SessionFactoryConfig;
import org.culinary.academy.dao.custom.RegistrationDAO;
import org.culinary.academy.entity.Registration;
import org.culinary.academy.entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean save(Registration entity) {
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
    public boolean update(Registration entity) {
        Session session=null;
        Transaction transaction=null;
        try {
            session=SessionFactoryConfig.getInstance().getSession();
            transaction=session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return true;
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) {
        Session session=null;
        Transaction transaction=null;
        try {
            session=SessionFactoryConfig.getInstance().getSession();
            transaction=session.beginTransaction();
            Registration registration=session.get(Registration.class,id);
            session.delete(registration);
            transaction.commit();
            return true;
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }

    }

    @Override
    public List<Registration> getAll() {
        return List.of();
    }

    @Override
    public Registration search(String id) {
        return null;
    }

    @Override
    public String getNextId() {
        Session session=SessionFactoryConfig.getInstance().getSession();
        try {
            String newId = "RE000";
            Transaction transaction = session.beginTransaction();
            List list = session.createNativeQuery("select reg_id from registration order by reg_id desc limit 1").list();
            if (!list.isEmpty()) newId = (String) list.get(0);
            transaction.commit();
            session.close();
            return newId;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
}
