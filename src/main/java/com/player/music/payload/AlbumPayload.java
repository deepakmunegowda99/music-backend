package com.player.music.payload;

import java.util.ArrayList;
import java.util.List;

public class AlbumPayload {

    private Long albumId;

    private String albumName;

    private Long postedDate;

    private Long artistId;

    private String imageCover;

    private List<SongsPlayLoad> songs = new ArrayList<SongsPlayLoad>();

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Long getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Long postedDate) {
        this.postedDate = postedDate;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public List<SongsPlayLoad> getSongs() {
        return songs;
    }

    public void setSongs(List<SongsPlayLoad> songs) {
        this.songs = songs;
    }

    public String getImageCover() {
        return imageCover;
    }

    public void setImageCover(String imageCover) {
        this.imageCover = imageCover;
    }

    

}