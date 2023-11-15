package com.example.functionality_two.services;

import com.example.functionality_two.DTOs.FileMetadataDTO;
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
public class MetadataService implements IMetadataService {

    private final MetadataJpaRepository metadataRepository;

    private final FoldersJpaRepository foldersRepository;
    @Autowired
    public MetadataService(MetadataJpaRepository metadataJpaRepository, FoldersJpaRepository foldersJpaRepository){
        metadataRepository = metadataJpaRepository;
        foldersRepository = foldersJpaRepository;
    }
    @Override
    public String createFile(FileMetadataDTO fileMetadataDTO, Model model) {
        if (fileMetadataDTO==null){
            return "add";
        }
        List<Folder> folders = new ArrayList<>();
        for (String folder :
                fileMetadataDTO.getFolders().split("(,)|(, )")) {
            foldersRepository.findByName(folder).ifPresent(folders::add);
        }
        FileMetadata fileMetadata = new FileMetadata(fileMetadataDTO.getFilename(),
                Integer.parseInt(fileMetadataDTO.getSize().split(" ")[0]),
                folders
        );
        if (metadataRepository.findByFilename(fileMetadata.getFilename()).isPresent()) {
            return readFile(fileMetadata.getFilename(), model);
        }
        FileMetadata savedFile = metadataRepository.save(fileMetadata);
        return readFile(savedFile.getFilename(), model);
    }


    public String readFile(String filename, Model model) {
        if(filename==null){
            return "search";
        }
        return metadataRepository.findByFilename(filename)
                .map(fileMetadata -> {
                    String string = fileMetadata.getParentFolder().stream().map(Folder::toString).reduce("", String::concat);
                    FileMetadataDTO file = new FileMetadataDTO(
                            fileMetadata.getFilename(),
                            fileMetadata.getSize(),
                            string
                    );
                    model.addAttribute("file",file);
                    return "get";
                }).orElse("redirect:/files");
    }

    @Override
    public String updateFile(String filename,FileMetadataDTO updatedFile, Model model) {
        return metadataRepository.findByFilename(filename)
                .map(existingFile -> {
                    existingFile.setSize(Integer.parseInt(updatedFile.getSize().split(" ")[0]));
                    List<Folder> folders = new ArrayList<>();

                    for (String folder:
                            updatedFile.getFolders().split("(,)|(, )")) {
                        foldersRepository.findByName(folder).ifPresent(folders::add);
                    }
                    existingFile.setParentFolder(folders);
                    metadataRepository.save(existingFile);
                    model.addAttribute("file", existingFile);
                    return "redirect:/files";
                })
                .orElse("edit");
    }

    @Override
    public String deleteFile(String filename, Model model) {
        if(filename==null){
            return "delete";
        }
        return metadataRepository.findByFilename(filename)
                .map(existingFile -> {
                    metadataRepository.delete(existingFile);
                    System.out.println("successfully deleted");
                    return "redirect:/files";
                })
                .orElse("redirect:/files");
    }




}