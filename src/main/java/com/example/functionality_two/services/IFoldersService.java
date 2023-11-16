package com.example.functionality_two.services;

import com.example.functionality_two.DTOs.FolderDTO;
import org.springframework.ui.Model;

public interface IFoldersService {
    String createFolder(FolderDTO folderDTO, Model model);
    String deleteFolder(String folderName, Model model);
    String readFolder(String folderName, Model model);
    String updateFolder(String folderName, FolderDTO updatedFolder, Model model);
}
