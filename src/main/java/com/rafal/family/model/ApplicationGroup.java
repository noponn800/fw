package com.rafal.family.model;

import lombok.Data;
import org.springframework.context.ApplicationListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ApplicationGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    private List<ApplicationUser> users;

    @OneToMany(mappedBy = "group")
    private List<ApplicationTask> tasks;

    private String name;

    @OneToOne
    private ApplicationUser creator;

    public ApplicationGroup() {
    }

    public ApplicationGroup(String name) {
        this.name = name;
    }

    public ApplicationUser getCreator() {
        return creator;
    }

    public void setCreator(ApplicationUser creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ApplicationTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<ApplicationTask> tasks) {
        this.tasks = tasks;
    }

    public List<ApplicationUser> getUsers() {
        return users;
    }

    public void setUsers(List<ApplicationUser> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addUser(ApplicationUser u)
    {
        users.add(u);
    }
}
