package com.example.functionality_two.entities;

import com.example.functionality_two.DTOs.FolderDTO;
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
    private List<Folder> parentFolders;

    @ManyToMany
    @Column(nullable = true)
    private List<Folder> childFolders;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentFolders(List<Folder> parentFolders) {
        this.parentFolders = parentFolders;
    }

    public List<Folder> getChildFolders() {
        return childFolders;
    }

    public void setChildFolders(List<Folder> childFolders) {
        this.childFolders = childFolders;
    }

    public Folder(){

    }

    public Folder(String name, List<Folder> parentFolders, List<Folder> childFolders) {
        this.name = name;
        this.parentFolders = parentFolders;
        this.childFolders = childFolders;
    }

    public List<Folder> getParentFolders() {
        return parentFolders;
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
