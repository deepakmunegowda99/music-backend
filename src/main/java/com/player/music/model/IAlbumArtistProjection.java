package com.player.music.model;

public interface IAlbumArtistProjection {
    Long getId();

    String getStatus();

    String getCoverImage();

    String getAlbumName();

    Long getPostedDate();

    Long getArtistId();

    String getArtistName();

    Integer getNoOfSongs();

}