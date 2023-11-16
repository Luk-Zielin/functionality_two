package com.example.functionality_two.DTOs;

import com.example.functionality_two.entities.Folder;

import java.util.List;

public class FolderDTO {
    private String name;
    private List<String> parentFolders;
    private List<String> childFolders;
    private List<String> Files;

    public FolderDTO(String name, List<String> parentFolders, List<String> childFolders) {
        this.name = name;
        this.parentFolders = parentFolders;
        this.childFolders = childFolders;
    }
    public FolderDTO(Folder folder){
        this.name= folder.getName();
        this.childFolders = folder.getChildFolders().stream().map(Folder::toString).toList();
        this.parentFolders = folder.getParentFolders().stream().map(Folder::toString).toList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getParentFolders() {
        return parentFolders;
    }

    public void setParentFolders(List<String> parentFolders) {
        this.parentFolders = parentFolders;
    }

    public List<String> getChildFolders() {
        return childFolders;
    }

    public void setChildFolders(List<String> childFolders) {
        this.childFolders = childFolders;
    }
}
