package com.example.functionality_two.entities;

import jakarta.persistence.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@Entity
@EntityScan("com.example.model")
public class Folder implements FileType<Folder> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany
    @Column(nullable = true)
    private List<Folder> parentFolder;

    public Folder() {

    }

    @Override
    public List<Folder> getParentFolder() {
        return null;
    }

    public Folder(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
