package com.player.music.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.player.music.model.audit.DateAudit;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "playlist_song")
public class PlayListSong extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistsSongId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "song_Id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Song song;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "playlists_Id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PlayList playList;

    public PlayListSong() {
    }

    public PlayListSong(Song song, PlayList playList) {
        this.song = song;
        this.playList = playList;
    }

    public PlayListSong(Long playlistsSongId, Song song, PlayList playList) {
        this.playlistsSongId = playlistsSongId;
        this.song = song;
        this.playList = playList;
    }

    public Long getPlaylistsSongId() {
        return this.playlistsSongId;
    }

    public void setPlaylistsSongId(Long playlistsSongId) {
        this.playlistsSongId = playlistsSongId;
    }

    public Song getSong() {
        return this.song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public PlayList getPlayList() {
        return this.playList;
    }

    public void setPlayList(PlayList playList) {
        this.playList = playList;
    }

    public PlayListSong playlistsSongId(Long playlistsSongId) {
        this.playlistsSongId = playlistsSongId;
        return this;
    }

    public PlayListSong song(Song song) {
        this.song = song;
        return this;
    }

    public PlayListSong playList(PlayList playList) {
        this.playList = playList;
        return this;
    }

}