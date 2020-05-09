package com.player.music.model;

import java.util.List;

public class ArtistGenre implements java.io.Serializable {
    private Long id;

    private String name;

    private String artistType; /// band or solo

    private String nameOfArtists;

    public List<GenreObject> genreObject;

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

    public List<GenreObject> getGenreObject() {
        return genreObject;
    }

    public void setGenreObject(List<GenreObject> genreObject) {
        this.genreObject = genreObject;
    }

}