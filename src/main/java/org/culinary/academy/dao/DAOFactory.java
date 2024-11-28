package org.culinary.academy.dao;

import org.culinary.academy.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        USER,STUDENT,PROGRAM,REGISTRATION,QUERY
    }

    public SuperDAO  getDAO(DAOTypes types) {
        switch (types) {
            case USER:
                return  new UserDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case PROGRAM:
                return new ProgramDAOImpl();
            case REGISTRATION:
                return new RegistrationDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;


        }
    }

}

