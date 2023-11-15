package com.example.functionality_two.services;

import com.example.functionality_two.DTOs.FileMetadataDTO;
import org.springframework.ui.Model;

public interface IMetadataService {
    public String createFile(FileMetadataDTO fileMetadataDTO, Model model);
    public String deleteFile(String filename, Model model);
    public String readFile(String filename, Model model);
    public String updateFile(String filename, FileMetadataDTO updatedFile, Model model);
}
