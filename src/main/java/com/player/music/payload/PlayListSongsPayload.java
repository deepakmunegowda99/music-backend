package com.player.music.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PlayListSongsPayload {

    @NotNull
    private Long songPlayListId;

    @NotNull
    private Long playListId;

    @NotBlank
    private Long songsId;

    public Long getSongPlayListId() {
        return this.songPlayListId;
    }

    public void setSongPlayListId(Long songPlayListId) {
        this.songPlayListId = songPlayListId;
    }

    public Long getPlayListId() {
        return this.playListId;
    }

    public void setPlayListId(Long playListId) {
        this.playListId = playListId;
    }

    public Long getSongsId() {
        return this.songsId;
    }

    public void setSongsId(Long songsId) {
        this.songsId = songsId;
    }

    public PlayListSongsPayload songPlayListId(Long songPlayListId) {
        this.songPlayListId = songPlayListId;
        return this;
    }

    public PlayListSongsPayload playListId(Long playListId) {
        this.playListId = playListId;
        return this;
    }

    public PlayListSongsPayload songsId(Long songsId) {
        this.songsId = songsId;
        return this;
    }

}