package com.player.music.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.player.music.model.audit.DateAudit;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "albums", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@JsonInclude(Include.NON_NULL)
@Transactional
public class Album extends DateAudit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private Long postedDate;

    @NotBlank
    private String coverImage;

    @OneToMany(mappedBy = "albums", cascade = CascadeType.MERGE)
    private List<Song> songs;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "artist_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Artist artist;

    public Album() {
    }

    public Album(@NotBlank @Size(max = 40) String name, @NotBlank Long postedDate, @NotBlank String coverImage,
            Artist artist) {
        this.name = name;
        this.postedDate = postedDate;
        this.coverImage = coverImage;
        this.artist = artist;
    }

    public Album(Long id, @NotBlank @Size(max = 40) String name, @NotBlank Long postedDate, @NotBlank String coverImage,
            Artist artist) {
        this.id = id;
        this.name = name;
        this.postedDate = postedDate;
        this.coverImage = coverImage;
        this.artist = artist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Long postedDate) {
        this.postedDate = postedDate;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album id(Long id) {
        this.id = id;
        return this;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

}