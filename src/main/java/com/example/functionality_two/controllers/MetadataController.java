package com.example.functionality_two.controllers;

import com.example.functionality_two.DTOs.FileMetadataDTO;
import com.example.functionality_two.repositories.FoldersJpaRepository;
import com.example.functionality_two.repositories.MetadataJpaRepository;
import com.example.functionality_two.services.MetadataService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/files")
public class MetadataController {
    final
    MetadataJpaRepository metadataRepository;
    final
    FoldersJpaRepository foldersRepository;
    MetadataService metadataService;
    public MetadataController(FoldersJpaRepository foldersRepository, MetadataJpaRepository metadataRepository){
        this.foldersRepository = foldersRepository;
        this.metadataRepository = metadataRepository;
        metadataService = new MetadataService(metadataRepository, foldersRepository);
    }

    @GetMapping("/{filename}")
    public String getFileMetadata(@PathVariable String filename, Model model){
        return metadataService.readFile(filename, model);
    }
    @RequestMapping("/search")
    public String getFileMetadata(Model model, @RequestParam(value = "searchName",required = false) String name){
        return metadataService.readFile(name, model);
    }
    @GetMapping
    public String startPage(){
        return "files/filesmenu";
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String postFile(@RequestBody FileMetadataDTO fileMetadataDTO, Model model) {
        return metadataService.createFile(fileMetadataDTO, model);
    }
    @PostMapping("/add/new")
    public String postFromMenu(Model model,@RequestParam(value = "filename",required = false) String name,
                               @RequestParam(value = "size",required = false) long size,
                               @RequestParam(value = "folders",required = false) String folders){
        FileMetadataDTO fileMetadataDTO = new FileMetadataDTO(name, size, folders);

        return metadataService.createFile(fileMetadataDTO, model);
    }
    @RequestMapping("/add")
    public String addPage(){
        return "files/add";
    }
    @PutMapping("/{filename}")
    public String putFile(@PathVariable String filename, @RequestBody FileMetadataDTO updatedFile, Model model) {
        return metadataService.updateFile(filename, updatedFile, model);
    }
    @DeleteMapping("/{filename}")
    public String deleteFile(@PathVariable String filename, Model model) {
        return metadataService.deleteFile(filename, model);
    }
    @GetMapping("/delete")
    public String deleteMenu(){
        return "files/delete";
    }
    @DeleteMapping("/delete")
    public String deleteFromMenu(Model model, @RequestParam(value = "searchName",required = false) String name){
        return metadataService.deleteFile(name, model);
    }
    @GetMapping("/edit")
    public String editFile(){
        return "files/edit";
    }
    @PutMapping("/edit")
    public String editFromMenu(Model model,@RequestParam(value = "filename",required = false) String name,
                               @RequestParam(value = "size",required = false) long size,
                               @RequestParam(value = "folders",required = false) String folders){
        FileMetadataDTO fileMetadataDTO = new FileMetadataDTO(name, size, folders);
        return metadataService.updateFile(name, fileMetadataDTO, model);
    }
}
