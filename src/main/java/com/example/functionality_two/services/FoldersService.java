package com.example.functionality_two.services;

import com.example.functionality_two.DTOs.FileMetadataDTO;
import com.example.functionality_two.DTOs.FolderDTO;
import com.example.functionality_two.entities.FileMetadata;
import com.example.functionality_two.entities.Folder;
import com.example.functionality_two.repositories.FoldersJpaRepository;
import com.example.functionality_two.repositories.MetadataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoldersService implements IFoldersService{
    private final MetadataJpaRepository metadataRepository;

    private final FoldersJpaRepository foldersRepository;

    @Autowired
    public FoldersService(MetadataJpaRepository metadataRepository, FoldersJpaRepository foldersRepository) {
        this.metadataRepository = metadataRepository;
        this.foldersRepository = foldersRepository;
    }


    @Override
    public String createFolder(FolderDTO folderDTO, Model model) {
        if (folderDTO==null){
            return "add";
        }
        List<Folder> parentFolders = new ArrayList<>();
        for (String folder :
                folderDTO.getParentFolders()){
            foldersRepository.findByName(folder).ifPresent(parentFolders::add);
        }
        List<Folder> childFolders = new ArrayList<>();
        for (String folder :
                folderDTO.getChildFolders()){
            foldersRepository.findByName(folder).ifPresent(childFolders::add);
        }
        Folder folder = new Folder(folderDTO.getName(),
                parentFolders,
                childFolders
        );
        if (metadataRepository.findByFilename(folder.getName()).isPresent()) {
            return readFolder(folder.getName(), model);
        }
        Folder savedFolder = foldersRepository.save(folder);
        return readFolder(savedFolder.getName(), model);
    }

    @Override
    public String deleteFolder(String folderName, Model model) {
        if(folderName==null){
            return "delete";
        }
        return foldersRepository.findByName(folderName)
                .map(existingFolder -> {
                    foldersRepository.delete(existingFolder);
                    System.out.println("successfully deleted");
                    return "redirect:/files";
                })
                .orElse("redirect:/files"); //Todo change the delete redirects
    }

    @Override
    public String readFolder(String folderName, Model model) {
        if(folderName==null){
            return "search";
        }
        return foldersRepository.findByName(folderName)
                .map(folder -> {
                    List<String> parentsFolders = folder.getParentFolders().stream().map(Folder::toString).toList();
                    List<String> childFolders = folder.getChildFolders().stream().map(Folder::toString).toList();
                    FolderDTO file = new FolderDTO(
                            folder.getName(),
                            parentsFolders,
                            childFolders
                    );
                    model.addAttribute("file",file); //todo change the read redirects
                    return "get";
                }).orElse("redirect:/files");
    }

    @Override
    public String updateFolder(String folderName, FolderDTO updatedFolder, Model model) {
        return foldersRepository.findByName(folderName)
                .map(existingFolder -> {
                    List<Folder> childFolders = new ArrayList<>();
                    List<Folder> parentFolders = new ArrayList<>();

                    for (String folder:
                            updatedFolder.getParentFolders()) {
                        foldersRepository.findByName(folder).ifPresent(parentFolders::add);
                    }
                    for (String folder:
                            updatedFolder.getChildFolders()) {
                        foldersRepository.findByName(folder).ifPresent(childFolders::add);
                    }
                    existingFolder.setParentFolders(parentFolders);
                    existingFolder.setChildFolders(childFolders);
                    foldersRepository.save(existingFolder);
                    model.addAttribute("file", existingFolder);
                    return "redirect:/files";
                })
                .orElse("edit"); //Todo change the edit redirects
    }

    @Override
    public String addFileToFolder(String fileName, String folderName) {
        return null;
    }

    @Override
    public String addFolderToFolder(String childFolder, String parentFolder) {
        return null;
    }
}
