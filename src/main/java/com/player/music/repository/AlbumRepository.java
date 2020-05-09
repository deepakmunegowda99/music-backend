package com.player.music.repository;

import java.util.List;

import com.player.music.model.Album;
import com.player.music.model.IAlbumArtistProjection;
import com.player.music.model.IAlbumSongsProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlbumRepository extends JpaRepository<Album, Long> {

        Boolean existsByName(String name);

        public final static String album_field = "select al.id,al.created_at as createdDate ,al.status,al.cover_image as coverImage,"
                        + "al.name as albumName ,al.posted_date as postedDate,al.artist_id as artistId,ar.name as artistName,"
                        + "count(s.id) as noofsongs";

        public final static String song_field = ",json_arrayagg(JSON_OBJECT('songId',s.id,'songName',s.name,'url',s.url,'status',s.status)) as songObject";

        public final static String tables = "\tfrom albums al left join artists ar on ar.id = al.artist_id left join songs s on s.albums_id = al.id";

        public final static String album_songs_findById = "\tWhere al.id = ?1";

        public final static String group_albums = "\tgroup by al.id";

        @Query(value = (album_field + tables + group_albums), nativeQuery = true)
        List<IAlbumArtistProjection> getAlbumSongArtists();

        @Query(value = (album_field + tables + album_songs_findById + group_albums), nativeQuery = true)
        List<IAlbumArtistProjection> getAlbumSongArtistsById(@Param("id") Long id);

        @Query(value = (album_field + song_field + tables + album_songs_findById + group_albums), nativeQuery = true)
        List<IAlbumSongsProjection> getAlbumSong(@Param("id") Long id);

        @Query("SELECT DISTINCT a FROM Album a JOIN FETCH a.songs s JOIN FETCH a.artist WHERE a.id = (:id) and s.status = (:status)")
        public List<Album> findByAlbumSongsId(@Param("id") Long id, @Param("status") String status);

}