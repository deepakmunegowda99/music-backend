package com.player.music.model;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface IAlbumSongsProjection {
    Long getId();

    String getStatus();

    String getCoverImage();

    String getAlbumName();

    Long getPostedDate();

    Long getArtistId();

    String getArtistName();

    Integer getNoOfSongs();

    @JsonIgnore
    String getSongObject();

    @JsonProperty("songsObj")
    public default SongsObject[] GenreObjects() {
        ObjectMapper mapper = new ObjectMapper();
        SongsObject[] objects = null;
        try {
            objects = mapper.readValue(getSongObject(), SongsObject[].class);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return objects;

    }
}