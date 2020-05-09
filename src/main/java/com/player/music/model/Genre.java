package com.player.music.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.player.music.model.audit.DateAudit;

@Entity
@Table(name = "genres", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Genre extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String name;

    @NotNull
    private Integer priority;

    @NotEmpty
    private String description;

    @NotEmpty
    private String parentGenre = "true";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Genre id(Long id) {
        this.id = id;
        return this;
    }

    public String getParentGenre() {
        return parentGenre;
    }

    public void setParentGenre(String parentGenre) {
        this.parentGenre = parentGenre;
    }

    public Genre() {
    }

    public Genre(@NotBlank @Size(max = 40) String name, @NotNull Integer priority, @NotEmpty String description) {
        this.name = name;
        this.priority = priority;
        this.description = description;
    }

    public Genre(@NotBlank @Size(max = 40) String name, @NotNull Integer priority, @NotEmpty String description,
            String parentGenre) {
        this.name = name;
        this.priority = priority;
        this.description = description;
        this.parentGenre = parentGenre;
    }

    public Genre(Long id, @NotBlank @Size(max = 40) String name, @NotNull Integer priority,
            @NotEmpty String description, String parentGenre) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.description = description;
        this.parentGenre = parentGenre;
    }

}