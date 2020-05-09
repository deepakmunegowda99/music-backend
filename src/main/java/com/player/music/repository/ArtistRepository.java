package com.player.music.repository;

import java.util.List;

import com.player.music.model.Artist;
import com.player.music.model.IArtistGenreProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Boolean existsByName(String name);

    List<Artist> findByStatus(String status);

    Artist findByIdAndStatus(Long id, String status);

    public final static String genre_artist = "select ar.id,ar.name,ar.artist_type as artistType,ar.name_of_artists as nameOfArtists,json_arrayagg(JSON_OBJECT('genreId',g.id,'genreName',g.name,'genreArtistId',gar.id)) as genreObject from artists ar  left join genre_arist gar on gar.artist_id = ar.id left join genres g on g.id = gar.genre_id";

    public final static String genre_artist_findById = genre_artist + "\tWhere ar.id = ?1";

    public final static String groupByArtistId = "\tgroup by ar.id";

    @Query(value = (genre_artist + groupByArtistId), nativeQuery = true)
    List<IArtistGenreProjection> getArtistGenres();

    @Query(value = (genre_artist_findById + groupByArtistId), nativeQuery = true)
    List<IArtistGenreProjection> getArtistGenre(@Param("id") Long id);

}