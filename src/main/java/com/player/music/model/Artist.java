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

@Entity
@Table(name = "artists", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@JsonInclude(Include.NON_NULL)
@Transactional
public class Artist extends DateAudit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String name;

    @NotBlank
    private String artistType; /// band or solo

    private String nameOfArtists;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "artist", cascade = CascadeType.MERGE)
    private List<Album> albums;

    public Artist() {
    }

    public Artist(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getArtistId() {
        return this.id;
    }

    public void setArtistId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist artistId(Long id) {
        this.id = id;
        return this;
    }

    public Artist name(String name) {
        this.name = name;
        return this;
    }

    public String getArtistType() {
        return artistType;
    }

    public void setArtistType(String artistType) {
        this.artistType = artistType;
    }

    public String getNameOfArtists() {
        return nameOfArtists;
    }

    public void setNameOfArtists(String nameOfArtists) {
        this.nameOfArtists = nameOfArtists;
    }

    public Artist(@NotBlank @Size(max = 40) String name, @NotBlank String artistType, String nameOfArtists) {
        this.name = name;
        this.artistType = artistType;
        this.nameOfArtists = nameOfArtists;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

}