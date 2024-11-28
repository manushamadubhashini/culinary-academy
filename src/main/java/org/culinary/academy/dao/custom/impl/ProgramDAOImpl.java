package org.culinary.academy.dao.custom.impl;

import org.culinary.academy.config.SessionFactoryConfig;
import org.culinary.academy.dao.custom.ProgramDAO;
import org.culinary.academy.entity.Program;
import org.culinary.academy.entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {

    @Override
    public boolean save(Program entity) {
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
    public String getNextId() {
        Session session=SessionFactoryConfig.getInstance().getSession();
        try {
            String newId = "PR000";
            Transaction transaction = session.beginTransaction();
            List list = session.createNativeQuery("select program_id from program order by program_id desc limit 1").list();
            if (!list.isEmpty()) newId = (String) list.get(0);
            transaction.commit();
            session.close();
            return newId;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean update(Program entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionFactoryConfig.getInstance().getSession();
            transaction = session.beginTransaction();
            session.update(entity);

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
    public boolean delete(String id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionFactoryConfig.getInstance().getSession();
            transaction = session.beginTransaction();
            Program program=session.get(Program.class,id);
            session.delete(program);

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
    public List<Program> getAll() {
        Session session=SessionFactoryConfig.getInstance().getSession();
        try {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Program> query = criteriaBuilder.createQuery(Program.class);
            query.from(Program.class);
            List<Program> resultList = session.createQuery(query).getResultList();
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
    public Program search(String id) {
        Session session=null;
        Transaction transaction=null;
        try {
            session=SessionFactoryConfig.getInstance().getSession();
            transaction=session.beginTransaction();
            Program program=session.get(Program.class,id);
            transaction.commit();
            return program;
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
}
