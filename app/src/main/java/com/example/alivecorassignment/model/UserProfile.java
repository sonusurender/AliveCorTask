package com.example.alivecorassignment.model;

import java.util.Date;

/**
 * Created by sonu on 13:54, 18/01/21
 * Copyright (c) 2021 . All rights reserved.
 */
public class UserProfile {
    private String firstName;
    private String lastName;
    private Date dob;

    public UserProfile() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
