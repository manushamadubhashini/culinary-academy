package org.culinary.academy.dao.custom.impl;

import org.culinary.academy.config.SessionFactoryConfig;
import org.culinary.academy.dao.custom.QueryDAO;
import org.culinary.academy.dto.RegistrationDTO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public  class QueryDAOImpl implements QueryDAO {

//    Session session= SessionFactoryConfig.getInstance().getSession();

    @Override
    public List<RegistrationDTO> getAll() {
        Session session = null;
        Transaction transaction = null;
        List<RegistrationDTO> registrationDTOS = new ArrayList<>();

        try {
            // Create a new session for each operation
            session = SessionFactoryConfig.getInstance().getSession();
            transaction = session.beginTransaction();

            List<Object[]> list = session.createNativeQuery("select r.reg_id,r.reg_date,r.student_id,r.program_id,s.student_name,p.program_name from student s INNER JOIN registration r ON r.student_id=s.student_id INNER JOIN program p ON r.program_id=p.program_id;").list();

            if (list.isEmpty()) {
                System.out.println("empty");
            } else {
                for (Object[] objects : list) {
                    registrationDTOS.add(new RegistrationDTO(
                            (String) objects[0],
                            (Date) objects[1],
                            (String) objects[2],
                            (String) objects[3],
                            (String) objects[4],
                            (String) objects[5]
                    ));
                }
            }

            transaction.commit();
            return registrationDTOS;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            // Ensure session is closed in the finally block
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
