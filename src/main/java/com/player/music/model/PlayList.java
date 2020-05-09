package com.player.music.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.player.music.model.audit.DateAudit;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "playlists")
public class PlayList extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistsId;

    @NotBlank
    @Size(max = 40)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_Id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;


    public PlayList() {
    }

    public PlayList(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public PlayList(Long playlistsId, String name, User user) {
        this.playlistsId = playlistsId;
        this.name = name;
        this.user = user;
    }

    public Long getPlaylistsId() {
        return this.playlistsId;
    }

    public void setPlaylistsId(Long playlistsId) {
        this.playlistsId = playlistsId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PlayList playlistsId(Long playlistsId) {
        this.playlistsId = playlistsId;
        return this;
    }

    public PlayList name(String name) {
        this.name = name;
        return this;
    }

    public PlayList user(User user) {
        this.user = user;
        return this;
    }

}