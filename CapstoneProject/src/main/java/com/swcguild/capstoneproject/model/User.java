/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    @Column(name="user_id")
    private int userId;
    
    @Column(name="username")
    @Size(min=3, max=20, message="The user name must be between 3 and 20 characters")
    @NotEmpty
    private String userName;
    
    @Column(name="password")
    @Size(min=8, max=20, message="The password must be between 8 and 20 characters")
    @NotEmpty
    private String password;
    
    @Column(name="enabled")
    private int enabled;
    
    @Column(name="good_standing")
    private boolean goodStanding;
    
    @Column(name="name")
    @Size(min=5, max=20, message="The name must be between 5 and 20 characters")
    private String name; 
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy="user")
    private Set<Event> events;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public boolean isGoodStanding() {
        return goodStanding;
    }

    public void setGoodStanding(boolean goodStanding) {
        this.goodStanding = goodStanding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.userId;
        hash = 59 * hash + Objects.hashCode(this.userName);
        hash = 59 * hash + Objects.hashCode(this.password);
        hash = 59 * hash + this.enabled;
        hash = 59 * hash + (this.goodStanding ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (this.enabled != other.enabled) {
            return false;
        }
        if (this.goodStanding != other.goodStanding) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

   
 
    
}
