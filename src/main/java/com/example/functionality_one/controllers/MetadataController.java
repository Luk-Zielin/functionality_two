package com.example.functionality_one.controllers;

import com.example.functionality_one.DTOs.FileMetadataDTO;
import com.example.functionality_one.repositories.MetadataJpaRepository;
import com.example.functionality_one.services.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/files")

public class MetadataController {
    @Autowired
    MetadataJpaRepository repository;
    MetadataService metadataService;
    public MetadataController(){
        metadataService = new MetadataService();
    }

    @GetMapping("/{filename}")
    public String getFileMetadata(@PathVariable String filename, Model model){
        System.out.println("jestem 1"); //todo usuń
        return metadataService.readFile(filename, repository, model);
    }
    @RequestMapping("/search")
    public String getFileMetadata(Model model, @RequestParam(value = "searchName",required = false) String name){
        System.out.println("jestem 1"); //todo usuń
        System.out.println(name);
        return metadataService.readFile(name, repository, model);
    }
    @RequestMapping
    public String startPage(){
        return "files";
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String postFile(@RequestBody FileMetadataDTO fileMetadataDTO, Model model) {
        System.out.println("jestem"); // todo usuń
        return metadataService.createFile(fileMetadataDTO, repository, model);
    }
    @PostMapping("/add/new")
    public String postFromMenu(Model model,@RequestParam(value = "filename",required = false) String name,
                               @RequestParam(value = "size",required = false) long size,
                               @RequestParam(value = "folders",required = false) String folders){
        FileMetadataDTO fileMetadataDTO = new FileMetadataDTO(name, size, folders);

        return metadataService.createFile(fileMetadataDTO, repository,model);
    }
    @RequestMapping("/add")
    public String addPage(Model model){
        return "add";
    }
    @PutMapping("/{filename}")
    public String putFile(@PathVariable String filename, @RequestBody FileMetadataDTO updatedFile, Model model) {
        return metadataService.updateFile(filename, updatedFile, repository, model);
    }
    @DeleteMapping("/{filename}")
    public String deleteFile(@PathVariable String filename, Model model) {
        return metadataService.deleteFile(filename, repository, model);
    }
    @GetMapping("/delete")
    public String deleteMenu(){
        return "delete";
    }
    @DeleteMapping("/delete")
    public String deleteFromMenu(Model model, @RequestParam(value = "searchName",required = false) String name){
        System.out.println("jestem delete");
        return metadataService.deleteFile(name, repository, model);
    }
    @GetMapping("/edit")
    public String editFile(){
        return "edit";
    }
    @PostMapping("/edit")
    public String editFromMenu(Model model,@RequestParam(value = "filename",required = false) String name,
                               @RequestParam(value = "size",required = false) long size,
                               @RequestParam(value = "folders",required = false) String folders){
        FileMetadataDTO fileMetadataDTO = new FileMetadataDTO(name, size, folders);
        return metadataService.updateFile(name, fileMetadataDTO, repository, model);
    }
}
