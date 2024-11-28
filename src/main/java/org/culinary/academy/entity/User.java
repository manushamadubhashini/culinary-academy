package org.culinary.academy.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_type")
    private String userType;

    public User(String userName, int id, String password, String userType) {
        this.userName = userName;
        this.id = id;
        this.password = password;
        this.userType = userType;
    }

    @Column(name = "password")
    private String password;

    public User(String userName, String password, String userType) {
        this.userName=userName;
        this.password=password;
        this.userType=userType;

    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }



}
