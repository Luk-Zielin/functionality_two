package com.example.functionality_two.services;

import com.example.functionality_two.DTOs.FolderDTO;
import org.springframework.ui.Model;

public interface IFoldersService {
    public String createFolder(FolderDTO folderDTO, Model model);
    public String deleteFolder(String folderName, Model model);
    public String readFolder(String folderName, Model model);
    public String updateFolder(String folderName, FolderDTO updatedFolder, Model model);
    public String addFileToFolder(String fileName, String folderName);
    public String addFolderToFolder(String childFolder, String parentFolder);
}
