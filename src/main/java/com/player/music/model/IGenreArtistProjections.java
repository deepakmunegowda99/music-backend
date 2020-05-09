package com.player.music.model;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface IGenreArtistProjections {
    Long getId();

    String getName();

    @JsonIgnore
    String getArtistAlbumObject();

    @JsonProperty("artistAlbumObj")
    public default ArtistAlbumObject[] ArtistAlbum() {
        ObjectMapper mapper = new ObjectMapper();
        ArtistAlbumObject[] obj = null;
        try {
            obj = mapper.readValue(getArtistAlbumObject(), ArtistAlbumObject[].class);
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
        return obj;

    }

}
