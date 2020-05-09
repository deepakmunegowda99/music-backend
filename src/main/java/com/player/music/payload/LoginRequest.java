package com.player.music.payload;

import javax.validation.constraints.NotEmpty;

public class LoginRequest {

    @NotEmpty
    private String usernameOrEmail;

    @NotEmpty
    private String password;

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsernameOrEmail() {
        return this.usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

}