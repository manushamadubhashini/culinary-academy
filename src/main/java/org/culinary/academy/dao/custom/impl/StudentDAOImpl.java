package org.culinary.academy.dao.custom.impl;

import org.culinary.academy.config.SessionFactoryConfig;
import org.culinary.academy.dao.custom.StudentDAO;
import org.culinary.academy.entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Student entity) {
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
    public boolean update(Student entity) {
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
            Student student=session.get(Student.class,id);
            session.delete(student);
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
    public List<Student> getAll() {
        Session session=SessionFactoryConfig.getInstance().getSession();
        try {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
            query.from(Student.class);
            List<Student> resultList = session.createQuery(query).getResultList();
            transaction.commit();
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public Student search(String id) {
        Session session=null;
        Transaction transaction=null;
        try {
            session=SessionFactoryConfig.getInstance().getSession();
            transaction=session.beginTransaction();
            Student student=session.get(Student.class,id);
            transaction.commit();
            return student;
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
    public String getNextId() {
        Session session=SessionFactoryConfig.getInstance().getSession();
        try {
            String newId = "ST000";
            Transaction transaction = session.beginTransaction();
            List list = session.createNativeQuery("select student_id from student order by student_id desc limit 1").list();
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
