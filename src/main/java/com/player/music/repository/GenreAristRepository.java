package com.player.music.repository;

import java.util.List;

import com.player.music.model.Artist;
import com.player.music.model.Genre;
import com.player.music.model.GenreArist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreAristRepository extends JpaRepository<GenreArist,Long> {

    List<GenreArist> findByArist(Artist arist);

    List<GenreArist> findByGenre(Genre genre);

        
}