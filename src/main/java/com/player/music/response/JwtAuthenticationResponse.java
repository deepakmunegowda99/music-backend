package com.player.music.response;

import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Map<String, Object> userDetails = new HashMap<>();

    public JwtAuthenticationResponse(String accessToken, Map<String, Object> userDetails) {
        this.accessToken = accessToken;
        this.userDetails = userDetails;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Map<String, Object> getMap() {
        return userDetails;
    }

    public void setMap(Map<String, Object> userDetails) {
        this.userDetails = userDetails;
    }

}
