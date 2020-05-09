package com.player.music.payload;

import javax.validation.constraints.NotBlank;

public class PlayListPayload {
    private Long playListId;

    @NotBlank
    private String name;

    public PlayListPayload() {
    }

    public PlayListPayload(String name) {
        this.name = name;
    }

    public PlayListPayload(Long playListId, String name) {
        this.playListId = playListId;
        this.name = name;
    }

    public Long getPlayListId() {
        return this.playListId;
    }

    public void setPlayListId(Long playListId) {
        this.playListId = playListId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayListPayload playListId(Long playListId) {
        this.playListId = playListId;
        return this;
    }

    public PlayListPayload name(String name) {
        this.name = name;
        return this;
    }

}