package com.player.music.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.player.music.model.Genre;
import com.player.music.model.IGenreArtistProjections;
import com.player.music.payload.GenrePayload;
import com.player.music.repository.GenreRepository;
import com.player.music.response.ApiResponse;
import static com.player.music.util.Constants.ACTIVE_STATUS;
import static com.player.music.util.Constants.INACTIVE_STATUS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/genre")
@Transactional
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @PostMapping
    public ResponseEntity<?> addEditGenre(@RequestParam(name = "id", required = false) Long id,
            @Valid @RequestBody GenrePayload genrePayload) {
        if (genreRepository.existsByNameAndStatus(genrePayload.getName(), ACTIVE_STATUS)) {
            return new ResponseEntity(new ApiResponse(false, "Genre already in use!"), HttpStatus.BAD_REQUEST);
        }
        String result = "";
        Genre genre = new Genre();
        if (id == null) {
            genre = new Genre(genrePayload.getName(), genrePayload.getPriority(), genrePayload.getDescription());
            result = "Genre added sucessfully";
        } else {
            genre = genreRepository.findByIdAndStatus(id, ACTIVE_STATUS);
            if (genre == null) {
                return new ResponseEntity(new ApiResponse(false, "Genre Not found!"), HttpStatus.BAD_REQUEST);
            }
            if (genrePayload.getName() != null) {
                genre.setName(genrePayload.getName());
            }
            if (genrePayload.getDescription() != null) {
                genre.setDescription(genrePayload.getDescription());
            }
            if (genrePayload.getPriority() != null) {
                genre.setPriority(genrePayload.getPriority());
            }
            result = "Genre updated sucessfully";
        }
        genre = genreRepository.save(genre);
        return ResponseEntity.ok().body(new ApiResponse(true, result, genre));
    }

    @GetMapping
    public ResponseEntity<?> fetchGenre(@RequestParam(name = "id", required = false) Long id) {
        String result = "";
        List<Genre> genre = new ArrayList<Genre>();
        if (id == null) {
            genre = genreRepository.findByStatus(ACTIVE_STATUS);
            if (genre.size() == 0) {
                return new ResponseEntity(new ApiResponse(false, "Genres Not found!"), HttpStatus.BAD_REQUEST);
            }
            result = "All Genre fetched sucessfully";
        } else {
            Genre gen = genreRepository.findByIdAndStatus(id, ACTIVE_STATUS);
            if (gen == null) {
                return new ResponseEntity(new ApiResponse(false, "Genre Not found!"), HttpStatus.BAD_REQUEST);
            }

            genre = Arrays.asList(gen);
            result = "Genre fetched sucessfully";
        }
        return ResponseEntity.ok().body(new ApiResponse(true, result, genre));

    }

    @DeleteMapping
    public ResponseEntity<?> deleteGenre(@RequestParam(name = "id") Long id) {
        Genre genre = genreRepository.findByIdAndStatus(id, ACTIVE_STATUS);
        if (genre == null) {
            return new ResponseEntity(new ApiResponse(false, "Genre Not found!"), HttpStatus.BAD_REQUEST);
        }
        genre.setStatus(INACTIVE_STATUS);
        genreRepository.save(genre);
        return ResponseEntity.ok().body(new ApiResponse(true, "Succesfully deleted genre"));
    }

    @GetMapping("/album")
    public ResponseEntity<?> genreArtistAlbum(@RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "lower", required = false) Long lower) {
        String result = "Reterived genre with albums";
        List<IGenreArtistProjections> genre = new ArrayList<IGenreArtistProjections>();

        if (id == null) {
            genre = genreRepository.getGenreArtistAlbum((long) 0, (long) 8);
        } else {
            genre = genreRepository.getGenreArtistAlbumById(lower, lower + 10, id);
        }
        return ResponseEntity.ok().body(new ApiResponse(true, result, genre));

    }

}