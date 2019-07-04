package com.rafal.family.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationUser that = (ApplicationUser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", groups=" + groups +
                '}';
    }

    private String username;
    private String password;
    @ManyToMany
    private List<ApplicationGroup> groups;

    public ApplicationUser()
    {

    }

    public ApplicationUser(String name, String surname, String username, String password)
    {
        this.groups = new ArrayList<ApplicationGroup>();
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ApplicationGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<ApplicationGroup> groups) {
        this.groups = groups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void addGroup(ApplicationGroup g)
    {
        groups.add(g);
    }

}