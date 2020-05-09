package com.player.music.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenreObject {

    @JsonProperty("genreId")
    private Long genreId;

    @JsonProperty("genreName")
    private String genreName;

    @JsonProperty("genreArtistId")
    private Long genreArtistId;

    

}
