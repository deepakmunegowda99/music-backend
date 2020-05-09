package com.player.music.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.player.music.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "sessions")
public class Session extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @NotEmpty
    private String loggedin;

    @NotNull
    private Long loggedintime;

    @NotNull
    private Long loggedouttime;

    public Session() {
    }

    public Session(User user, String loggedin, Long loggedintime, Long loggedouttime) {
        this.user = user;
        this.loggedin = loggedin;
        this.loggedintime = loggedintime;
        this.loggedouttime = loggedouttime;
    }

    public Session(Long sessionId, User user, String loggedin, Long loggedintime, Long loggedouttime) {
        this.sessionId = sessionId;
        this.user = user;
        this.loggedin = loggedin;
        this.loggedintime = loggedintime;
        this.loggedouttime = loggedouttime;
    }

    public Long getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getloggedin() {
        return this.loggedin;
    }

    public void setloggedin(String loggedin) {
        this.loggedin = loggedin;
    }

    public Long getloggedintime() {
        return this.loggedintime;
    }

    public void setloggedintime(Long loggedintime) {
        this.loggedintime = loggedintime;
    }

    public Long getloggedouttime() {
        return this.loggedouttime;
    }

    public void setloggedouttime(Long loggedouttime) {
        this.loggedouttime = loggedouttime;
    }

    public Session sessionId(Long sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public Session user(User user) {
        this.user = user;
        return this;
    }

    public Session loggedintime(Long loggedintime) {
        this.loggedintime = loggedintime;
        return this;
    }

    public Session loggedouttime(Long loggedouttime) {
        this.loggedouttime = loggedouttime;
        return this;
    }

}