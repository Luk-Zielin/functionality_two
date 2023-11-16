package com.example.functionality_two.entities;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;
import java.util.Optional;

@Entity
@EntityScan("com.example.model")
public class FileMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String filename;
    private long size;

    @ManyToMany
    @Column(nullable = true)
    private List<Folder> parentFolders;


    public FileMetadata() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public List<Folder> getParentFolders() {
        return parentFolders;
    }

    public void setParentFolders(List<Folder> folder) {
        this.parentFolders = folder;
    }

    public FileMetadata(String filename, long size, List<Folder> parentFolders) {
        this.filename = filename;
        this.size = size;
        this.parentFolders = parentFolders;
    }


}
