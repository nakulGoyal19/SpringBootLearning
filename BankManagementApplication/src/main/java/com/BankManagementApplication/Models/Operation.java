package com.BankManagementApplication.Models;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String description;

    private Date date;

    @ManyToOne
    @JoinColumn(name="accountNumber",nullable = false)
    private UserProfile userProfile;

    public Operation(){}

    public Operation(Integer id, String description, Date date, UserProfile userProfile) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.userProfile = userProfile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
