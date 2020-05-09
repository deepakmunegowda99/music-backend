package com.player.music.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArtistAlbumObject {

    @JsonProperty("artistName")
    private String artistName;
    
    @JsonProperty("albumId")
    private Long albumId;

    @JsonProperty("albumName")
    private String albumName;

    @JsonProperty("coverImage")
    private String coverImage;

}
