package com.rafal.family.model;

import javax.persistence.*;

@Entity
public class ApplicationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String body;
    private int points;
    private boolean complete;
    @OneToOne
    private ApplicationUser founder;
    @OneToOne
    private ApplicationUser executioner;
    //DateTime deadline
    private Integer priority;
    @ManyToOne
    private ApplicationGroup group;

    public ApplicationTask(String body) {
        this.body = body;
    }
    public ApplicationTask(String body, String title)
    {
        this.body = body;
        this.title = title;
    }
    public ApplicationTask()
    {

    }

    public ApplicationGroup getGroup() {
        return group;
    }

    public void setGroup(ApplicationGroup group) {
        this.group = group;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public ApplicationUser getExecutioner() {
        return executioner;
    }

    public void setExecutioner(ApplicationUser executioner) {
        this.executioner = executioner;
    }

    public ApplicationUser getFounder() {
        return founder;
    }

    public void setFounder(ApplicationUser founder) {
        this.founder = founder;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
