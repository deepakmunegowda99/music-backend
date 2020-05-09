package com.player.music.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SongsObject {

    @JsonProperty("songId")
    private Long songId;

    @JsonProperty("songName")
    private String songName;

    @JsonProperty("url")
    private String url;

    @JsonProperty("status")
    private String status;

}
