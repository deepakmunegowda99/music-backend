package com.player.music.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.player.music.model.audit.DateAudit;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "genre_arist")
public class GenreArist extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artist_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Artist arist;    

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "genre_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Genre genre;

    public GenreArist() {
    }

    public GenreArist(Artist arist, Genre genre) {
        this.arist = arist;
        this.genre = genre;
    }

    public GenreArist(Long id, Artist arist, Genre genre) {
        this.id = id;
        this.arist = arist;
        this.genre = genre;
    }

    public Long getGenrearistId() {
        return this.id;
    }

    public void setGenrearistId(Long id) {
        this.id = id;
    }

    public Artist getArist() {
        return this.arist;
    }

    public void setArist(Artist arist) {
        this.arist = arist;
    }

    public Genre getGenre() {
        return this.genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public GenreArist genrearistId(Long id) {
        this.id = id;
        return this;
    }

    public GenreArist arist(Artist arist) {
        this.arist = arist;
        return this;
    }

    public GenreArist genre(Genre genre) {
        this.genre = genre;
        return this;
    }

}