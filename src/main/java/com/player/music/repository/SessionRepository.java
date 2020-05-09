package com.player.music.repository;

import com.player.music.model.Session;
import com.player.music.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {

    Boolean existsByUserAndLoggedinAndStatus(User user, String loggedin, String status);

    Boolean existsByUserAndLoggedinAndLoggedintime(User user, String loggedin, Long loggedintime);

    Session findByUserAndLoggedinAndStatus(User user, String loggedin, String status);

}