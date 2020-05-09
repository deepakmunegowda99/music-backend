package com.player.music.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.player.music.model.PlayList;
import com.player.music.model.PlayListSong;
import com.player.music.model.Song;
import com.player.music.model.User;
import com.player.music.payload.PlayListPayload;
import com.player.music.payload.PlayListSongsPayload;
import com.player.music.repository.PlaylistRepository;
import com.player.music.repository.PlaylistSongsRepository;
import com.player.music.response.ApiResponse;
import com.player.music.security.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/playlist")
@Transactional
public class PlayListController {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistSongsRepository playlistSongsRepository;

    @PostMapping
    public ResponseEntity<?> addPlaysList(@Valid @RequestBody PlayListPayload playListPayload) {

        PlayList playList = new PlayList();
        UserPrincipal userPrin = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (playListPayload.getPlayListId() != 0) {
            playList = new PlayList(playListPayload.getName(), new User().userId(playListPayload.getPlayListId()));
        } else {
            playList = new PlayList(playListPayload.getPlayListId(), playListPayload.getName(),
                    new User().userId((userPrin.getId())));
        }
        playlistRepository.save(playList);

        return ResponseEntity.ok().body(new ApiResponse(true, "PlayList Addeded sucessfully"));
    }

    @PostMapping("/song")
    public ResponseEntity<?> addSongToPlayList(@Valid @RequestBody PlayListSongsPayload playListSongs) {
        // Song song = new Song().songId(playListSongs.getSongsId());
        // if (playlistSongsRepository.existsByPlaylistsSongId(song)) {
        //     return new ResponseEntity(new ApiResponse(false, "Songs already in your playList"), HttpStatus.BAD_REQUEST);
        // }
        // PlayListSong playListSong = new PlayListSong(song, new PlayList().playlistsId(playListSongs.getPlayListId()));
        // playlistSongsRepository.save(playListSong);
        return ResponseEntity.ok().body(new ApiResponse(true, "Song Addeded to your playlist"));
    }

}
