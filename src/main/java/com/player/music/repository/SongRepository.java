package com.player.music.repository;

import java.util.List;

import com.player.music.model.Song;
import com.player.music.model.Album;
import com.player.music.model.ArtistAlbumSongs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findByAlbumsAndStatus(Album albums, String status);

    @Query("SELECT al.name as albumName , al.coverImage as coverImage ,p.id as songId,p.name as songName,p.url as url  FROM Song p JOIN p.albums al"
            + "\tWHERE p.albums = (:id) and p.status = (:status)")
    public List<ArtistAlbumSongs> findByAlbumIdAndFetchAlbumsEagerly(@Param("id") Album id, String status);

}
