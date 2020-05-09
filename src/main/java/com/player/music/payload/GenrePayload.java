package com.player.music.payload;

public class GenrePayload {

    private String name;

    private Integer priority;

    private String description;

    public GenrePayload genreName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}