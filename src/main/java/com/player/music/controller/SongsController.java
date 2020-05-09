package com.player.music.controller;

import java.util.List;

import javax.transaction.Transactional;

import com.player.music.model.Album;

import com.player.music.repository.AlbumRepository;
import com.player.music.response.ApiResponse;
import static com.player.music.util.Constants.ACTIVE_STATUS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/songs")
@Transactional
public class SongsController {

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping
    public ResponseEntity<?> fetchSongs(@RequestParam(name = "albumId") Long albumId) {
        if (albumId == null) {
            return new ResponseEntity(new ApiResponse(false, "Album id cannot be null"), HttpStatus.BAD_REQUEST);
        }
        List<Album> songs = albumRepository.findByAlbumSongsId(albumId, ACTIVE_STATUS);
        return ResponseEntity.ok().body(new ApiResponse(true, "Successfully retrived all songs", songs));

    }

}