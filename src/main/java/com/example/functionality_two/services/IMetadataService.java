package com.example.functionality_two.services;

import com.example.functionality_two.DTOs.FileMetadataDTO;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

public interface IMetadataService {
    String createFile(FileMetadataDTO fileMetadataDTO, Model model);
    String deleteFile(String filename, Model model);
    String readFile(String filename, Model model);
    String updateFile(String filename, FileMetadataDTO updatedFile, Model model);
    ResponseEntity<FileMetadataDTO> readFile(String filename);
    ResponseEntity<FileMetadataDTO> createFile(FileMetadataDTO fileMetadataDTO);
    ResponseEntity<FileMetadataDTO> updateFile(String filename, FileMetadataDTO updatedFile);
}
