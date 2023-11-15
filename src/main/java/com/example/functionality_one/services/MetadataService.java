package com.example.functionality_one.services;

import com.example.functionality_one.DTOs.FileMetadataDTO;
import com.example.functionality_one.entities.FileMetadata;
import com.example.functionality_one.repositories.MetadataJpaRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class MetadataService implements IMetadataService {


    @Override
    public String createFile(FileMetadataDTO fileMetadataDTO, MetadataJpaRepository Repository, Model model) {
        if (fileMetadataDTO==null){
            return "add";
        }
        FileMetadata fileMetadata = new FileMetadata(fileMetadataDTO.getFilename(),
                Integer.parseInt(fileMetadataDTO.getSize().split(" ")[0]),
                new ArrayList<String>(List.of(fileMetadataDTO.getFolders().split(", ")))
        );
        if (Repository.findByFilename(fileMetadata.getFilename()).isPresent()) {
            return readFile(fileMetadata.getFilename(),Repository, model);
        };
        FileMetadata savedFile = Repository.save(fileMetadata);
        return readFile(savedFile.getFilename(),Repository, model);
    }


    public String readFile(String filename, MetadataJpaRepository Repository, Model model) {
        if(filename==null){
            return "search";
        }
        return Repository.findByFilename(filename)
                .map(fileMetadata -> {
                    FileMetadataDTO file = new FileMetadataDTO(
                            fileMetadata.getFilename(),
                            fileMetadata.getSize(),
                            String.join(", ",fileMetadata.getFolders())
                    );
                    model.addAttribute("file",file);
                    return "get";
                }).orElse("redirect:/files");
    }

    @Override
    public String updateFile(String filename,FileMetadataDTO updatedFile,MetadataJpaRepository Repository, Model model) {
        return Repository.findByFilename(filename)
                .map(existingFile -> {
                    existingFile.setSize(Integer.parseInt(updatedFile.getSize().split(" ")[0]));
                    existingFile.setFolders(new ArrayList<>(List.of(updatedFile.getFolders())));
                    Repository.save(existingFile);
                    model.addAttribute("file", existingFile);
                    return "redirect:/files";
                })
                .orElse("edit");
    }

    @Override
    public String deleteFile(String filename,MetadataJpaRepository Repository, Model model) {
        if(filename==null){
            return "delete";
        }
        return Repository.findByFilename(filename)
                .map(existingFile -> {
                    Repository.delete(existingFile);
                    System.out.println("successfully deleted");
                    return "redirect:/files";
                })
                .orElse("redirect:/files");
    }




}
