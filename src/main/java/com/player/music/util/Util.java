package com.player.music.util;

import java.util.Date;

import com.player.music.model.Session;
import com.player.music.model.User;
import com.player.music.repository.SessionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Util {

    @Autowired
    private SessionRepository sessionRepository;

    public boolean SignOut(Long userID) {
        Session sess = sessionRepository.findByUserAndLoggedinAndStatus(new User(userID), "TRUE", "1");
        if (sess == null) {
            return false;
        }
        sess.setloggedin("FALSE");
        sess.setStatus("1");
        sess.setloggedouttime(new Date().getTime());
        sessionRepository.save(sess);
        return true;
    }
}