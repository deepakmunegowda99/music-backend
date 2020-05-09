package com.player.music.repository;

import com.player.music.model.PlayListSong;
import com.player.music.model.Song;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistSongsRepository extends JpaRepository<PlayListSong, Long> {
    Boolean existsByPlaylistsSongId(Song playlistsSongId);

}