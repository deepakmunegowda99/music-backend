package com.player.music.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.player.music.model.Artist;
import com.player.music.model.Genre;
import com.player.music.model.GenreArist;
import com.player.music.model.IArtistGenreProjection;
import com.player.music.payload.ArtistPayload;
import com.player.music.repository.ArtistRepository;
import com.player.music.repository.GenreAristRepository;
import com.player.music.response.ApiResponse;
import static com.player.music.util.Constants.ACTIVE_STATUS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/artist")
@Transactional
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreAristRepository genreAristRepository;

    @PostMapping
    public ResponseEntity<?> addArist(@RequestParam(name = "id", required = false) Long id,
            @Valid @RequestBody ArtistPayload artistPayload) {
        if (artistPayload.getName() != null && artistRepository.existsByName(artistPayload.getName())) {
            return new ResponseEntity(new ApiResponse(false, "Artist already in use!"), HttpStatus.BAD_REQUEST);
        }
        Artist artist = new Artist();
        String result = "";
        if (id == null) {
            artist = new Artist(artistPayload.getName(), artistPayload.getArtistType(),
                    artistPayload.getNameOfArtists());
            result = "Artist added sucessfully";
        } else {
            artist = artistRepository.findByIdAndStatus(id, ACTIVE_STATUS);
            if (artistPayload.getName() != null) {
                artist.setName(artistPayload.getName());
            }
            if (artistPayload.getArtistType() != null) {
                artist.setArtistType(artistPayload.getArtistType());
            }
            if (artistPayload.getNameOfArtists() != null) {
                artist.setNameOfArtists(artistPayload.getNameOfArtists());
            }
            result = "Artist updated sucessfully";
        }
        if (artistPayload.getArtistType() != null && artistPayload.getNameOfArtists() != null
                && artistPayload.getNameOfArtists() != null) {
            artist = artistRepository.save(artist);
        }

        if (!artistPayload.getRemoveGenre().isEmpty()) {
            List<GenreArist> removeGenreArists = new ArrayList<GenreArist>();
            for (int i = 0; i < artistPayload.getRemoveGenre().size(); i++) {
                GenreArist genreArist = new GenreArist().genrearistId(artistPayload.getRemoveGenre().get(i));
                removeGenreArists.add(genreArist);
            }
            genreAristRepository.deleteAll(removeGenreArists);
        }

        List<GenreArist> genreArists = new ArrayList<GenreArist>();
        if (!artistPayload.getGenreIds().isEmpty()) {
            for (int i = 0; i < artistPayload.getGenreIds().size(); i++) {
                GenreArist genreArist = new GenreArist(artist, new Genre().id(artistPayload.getGenreIds().get(i)));
                genreArists.add(genreArist);
            }
            genreArists = genreAristRepository.saveAll(genreArists);
        }

        List<IArtistGenreProjection> artistGenre = artistRepository.getArtistGenre(artist.getArtistId());

        return ResponseEntity.ok().body(new ApiResponse(true, result, artistGenre));
    }

    @GetMapping
    public ResponseEntity<?> fetchArtist(@RequestParam(name = "id", required = false) Long id) {
        String result = "";
        List<IArtistGenreProjection> artistGenre = new ArrayList<IArtistGenreProjection>();
        if (id == null) {
            artistGenre = artistRepository.getArtistGenres();
            result = "All Artist fetched sucessfully";
        } else {
            artistGenre = artistRepository.getArtistGenre(id);
            result = "Artist fetched sucessfully";
        }
        return ResponseEntity.ok().body(new ApiResponse(true, result, artistGenre));

    }

}