package com.example.functionality_one.services;

import com.example.functionality_one.DTOs.FileMetadataDTO;
import com.example.functionality_one.repositories.MetadataJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

public interface IMetadataService {
    public String createFile(FileMetadataDTO fileMetadataDTO, MetadataJpaRepository Repository, Model model);
    public String deleteFile(String filename,MetadataJpaRepository Repository, Model model);
    public String readFile(String filename, MetadataJpaRepository Repository, Model model);
    public String updateFile(String filename, FileMetadataDTO updatedFile,MetadataJpaRepository Repository, Model model);
}
