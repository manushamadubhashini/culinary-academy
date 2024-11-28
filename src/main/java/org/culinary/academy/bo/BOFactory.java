package org.culinary.academy.bo;

import org.culinary.academy.bo.custom.impl.ProgramBOImpl;
import org.culinary.academy.bo.custom.impl.RegistrationBOImpl;
import org.culinary.academy.bo.custom.impl.StudentBOImpl;
import org.culinary.academy.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory bOFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){
        return (bOFactory==null)?bOFactory=new BOFactory():bOFactory;
    }

    public enum BOTypes{
        USER,STUDENT,PROGRAM,REGISTRATION
    }

    public SuperBO getBO(BOTypes boTypes){
        switch(boTypes){
            case USER:
                return new UserBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case PROGRAM:
                return new ProgramBOImpl();
            case REGISTRATION:
                return new RegistrationBOImpl();
            default:
                return null;
        }
    }




}


