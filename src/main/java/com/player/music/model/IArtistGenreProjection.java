package com.player.music.model;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface IArtistGenreProjection {
    Long getId();

    String getName();

    String getArtistType();

    String getNameOfArtists();

    @JsonIgnore
    String getGenreObject();

    @JsonProperty("genreObj")
    public default GenreObject[] GenreObjects() {
        ObjectMapper mapper = new ObjectMapper();
        GenreObject[] address = null ;
        try {
            address = mapper.readValue(getGenreObject(), GenreObject[].class);
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
        return address;
        
    }

}