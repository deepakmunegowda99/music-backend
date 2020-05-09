package com.player.music.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.player.music.model.Album;
import com.player.music.model.Artist;
import com.player.music.model.IAlbumArtistProjection;
import com.player.music.model.IAlbumSongsProjection;
import com.player.music.model.Song;
import com.player.music.payload.AlbumPayload;
import com.player.music.payload.SongsPlayLoad;
import com.player.music.repository.AlbumRepository;
import com.player.music.repository.SongRepository;
import com.player.music.response.ApiResponse;
import com.player.music.util.ListEntities;
import com.player.music.util.UploadFile;

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
@RequestMapping("/api/album")
@Transactional
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SongRepository songRepository;

    @PostMapping
    public ResponseEntity<?> addAlbum(@Valid @RequestBody AlbumPayload albumPayload) {
        if (albumRepository.existsByName(albumPayload.getAlbumName()) && albumPayload.getAlbumId() == null) {
            return new ResponseEntity(new ApiResponse(false, "Album already in use!"), HttpStatus.BAD_REQUEST);
        }
        String result = "";
        Album album = new Album();
        if (albumPayload.getAlbumId() == null) {
            String url = new UploadFile().UploadImage(albumPayload.getImageCover(),albumPayload.getAlbumName());
            album = new Album(albumPayload.getAlbumName(), album.getPostedDate(), url,
                    new Artist().artistId(albumPayload.getArtistId()));
            result = "Album added sucessfully";
        } else {
            album = albumRepository.getOne(albumPayload.getAlbumId());
            if (albumPayload.getAlbumName() != "" && album.getName() != albumPayload.getAlbumName()) {
                album.setName(albumPayload.getAlbumName());
            }
            if (album.getPostedDate() != 0 && album.getPostedDate() != album.getPostedDate()) {
                album.setPostedDate(album.getPostedDate());
            }
            if (albumPayload.getImageCover() != "" && album.getCoverImage() != albumPayload.getImageCover()) {
                String url = new UploadFile().UploadImage(albumPayload.getImageCover(),albumPayload.getAlbumName());
                album.setCoverImage(url);
            }
            if (albumPayload.getArtistId() != 0 && album.getArtist().getArtistId() != albumPayload.getArtistId()) {
                album.setArtist(new Artist().artistId(albumPayload.getArtistId()));
            }

            result = "Album updated sucessfully";
        }

        album = albumRepository.save(album);

        List<Song> songs = new ArrayList<Song>();

        for (int i = 0; i < albumPayload.getSongs().size(); i++) {
            SongsPlayLoad songsPlayLoad = albumPayload.getSongs().get(i);
            if (songsPlayLoad.getSongId() != null) {
                songs.add(new Song(songsPlayLoad.getSongId(), songsPlayLoad.getSongName(), songsPlayLoad.getUrl(), album,
                        songsPlayLoad.getStatus()));
            } else {
                songs.add(new Song(songsPlayLoad.getSongName(), songsPlayLoad.getUrl(), album));
            }

        }

        songRepository.saveAll(songs);
        List<IAlbumArtistProjection> albums = albumRepository.getAlbumSongArtistsById(album.getId());

        return ResponseEntity.ok().body(new ApiResponse(true, result, albums));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAlbumList(@RequestParam(name = "name", required = false) String name) {
        String result = "";
        Object listEntities = new ListEntities().ListOfEntites(name);
        return ResponseEntity.ok().body(new ApiResponse(true, result, listEntities));
    }

    @GetMapping
    public ResponseEntity<?> fetchAlbum(@RequestParam(name = "id", required = false) Long id) {
        ApiResponse apiResponse = new ApiResponse();
        if (id == null) {
            List<IAlbumArtistProjection> albums = new ArrayList<IAlbumArtistProjection>();
            albums = albumRepository.getAlbumSongArtists();
            apiResponse = new ApiResponse(true, "Successfully fetched all albums", albums);

        } else {
            List<IAlbumSongsProjection> albums = new ArrayList<IAlbumSongsProjection>();
            albums = albumRepository.getAlbumSong(id);
            apiResponse = new ApiResponse(true, "Successfully fetched albums", albums);
        }
        return ResponseEntity.ok().body(apiResponse);

    }


    

}
