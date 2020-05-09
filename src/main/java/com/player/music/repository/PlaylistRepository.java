package com.player.music.repository;

import com.player.music.model.PlayList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<PlayList,Long> {
    
}