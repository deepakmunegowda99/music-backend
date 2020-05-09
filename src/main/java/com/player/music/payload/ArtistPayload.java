package com.player.music.payload;

import java.util.ArrayList;
import java.util.List;

public class ArtistPayload {

    private String name;

    private String artistType;

    private String nameOfArtists;

    private List<Long> genreIds = new ArrayList<Long>();

    private List<Long> removeGenre = new ArrayList<Long>();

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

    public List<Long> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Long> genreIds) {
        this.genreIds = genreIds;
    }

    public List<Long> getRemoveGenre() {
        return removeGenre;
    }

    public void setRemoveGenre(List<Long> removeGenre) {
        this.removeGenre = removeGenre;
    }

}