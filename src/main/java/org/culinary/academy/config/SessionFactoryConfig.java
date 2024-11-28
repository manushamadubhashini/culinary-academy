package org.culinary.academy.config;

import org.culinary.academy.entity.Program;
import org.culinary.academy.entity.Registration;
import org.culinary.academy.entity.Student;
import org.culinary.academy.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfig;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfig(){
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        configuration
//             .configure()
             .addAnnotatedClass(User.class)
                .addAnnotatedClass(Student.class)
               .addAnnotatedClass(Program.class)
                .addAnnotatedClass(Registration.class);



//             .buildSessionFactory();
        sessionFactory=configuration.setProperties(properties).buildSessionFactory();
    }
    public static SessionFactoryConfig getInstance(){
     return (factoryConfig==null) ? factoryConfig=new SessionFactoryConfig():factoryConfig;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }

}
