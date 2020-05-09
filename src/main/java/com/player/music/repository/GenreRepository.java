package com.player.music.repository;

import java.util.List;

import com.player.music.model.Genre;
import com.player.music.model.IGenreArtistProjections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Boolean existsByNameAndStatus(String name, String status);

    List<Genre> findByStatus(String status);

    Genre findByIdAndStatus(Long id, String status);

    public static final String selectField = "select g.id,g.name,json_arrayagg(JSON_OBJECT('artistName',ar.name,'albumId',al.id,'albumName',al.name,"
            + "\t'coverImage',al.cover_image)) as artistAlbumObject from genres g inner join genre_arist ga on ga.genre_id = g.id left join artists ar"
            + "\ton ar.id = ga.artist_id inner join (select * from albums order by created_at desc limit ?1,?2) al on al.artist_id = ga.artist_id";

    public static final String condition = "\twhere g.parent_genre = 'true'";

    public static final String getSinglefGenre = "\tand g.id =?3";

    public static String getUniquqGenres = "\tgroup by g.id";

    @Query(value = (selectField + condition + getUniquqGenres), nativeQuery = true)
    List<IGenreArtistProjections> getGenreArtistAlbum(@Param("lower") Long lower, @Param("upper") Long upper);

    @Query(value = (selectField + condition + getSinglefGenre + getUniquqGenres), nativeQuery = true)
    List<IGenreArtistProjections> getGenreArtistAlbumById(@Param("lower") Long lower, @Param("upper") Long upper,
            @Param("id") Long id);

}