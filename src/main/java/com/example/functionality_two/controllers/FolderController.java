package com.example.functionality_two.controllers;

import com.example.functionality_two.DTOs.FolderDTO;
import com.example.functionality_two.repositories.FoldersJpaRepository;
import com.example.functionality_two.repositories.MetadataJpaRepository;
import com.example.functionality_two.services.FoldersService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/folders")
public class FolderController {
    final
    MetadataJpaRepository metadataRepository;
    final
    FoldersJpaRepository foldersRepository;
    FoldersService foldersService;

    public FolderController(MetadataJpaRepository metadataRepository, FoldersJpaRepository foldersRepository, FoldersService foldersService) {
        this.metadataRepository = metadataRepository;
        this.foldersRepository = foldersRepository;
        this.foldersService = foldersService;
    }

    @GetMapping("/{folderName}")
    public String getFileMetadata(@PathVariable String folderName, Model model){

        return foldersService.readFolder(folderName, model);
    }
    @RequestMapping("/search")
    public String getFileMetadata(Model model, @RequestParam(value = "searchName",required = false) String name){

        return foldersService.readFolder(name, model);
    }
    @GetMapping
    public String startPage(){
        return "folders/foldersmenu";
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String postFile(@RequestBody FolderDTO folderDTO, Model model) {
        return foldersService.createFolder(folderDTO, model);
    }
    @PostMapping("/add/new")
    public String postFromMenu(Model model,@RequestParam(value = "foldername",required = false) String name,
                               @RequestParam(value = "childfolders", required = false) String childFolders) {
        List<String> childList = new ArrayList<>();
        if(childFolders!=null) {
            childList.addAll(List.of(childFolders.split("(,)|(, )")));
        }
        FolderDTO folderDTO = new FolderDTO(name, childList);
        return foldersService.createFolder(folderDTO, model);
    }
    @RequestMapping("/add")
    public String addPage(){
        return "folders/add";
    }
    @PutMapping("/{folderName}")
    public String putFolder(@PathVariable String folderName, @RequestBody FolderDTO updatedFolder, Model model) {
        return foldersService.updateFolder(folderName, updatedFolder, model);
    }
    @DeleteMapping("/{folderName}")
    public String deleteFolder(@PathVariable String folderName, Model model) {
        return foldersService.deleteFolder(folderName, model);
    }
    @PostMapping()
    public String postFolder(@RequestBody FolderDTO newFolder, Model model){
        return foldersService.createFolder(newFolder, model);
    }
    @GetMapping("/delete")
    public String deleteMenu(){
        return "folders/delete";
    }
    @DeleteMapping("/delete")
    public String deleteFromMenu(Model model, @RequestParam(value = "searchName",required = false) String name){
        return foldersService.deleteFolder(name, model);
    }
    @GetMapping("/edit")
    public String editFile(){
        return "folders/edit";
    }
    @PutMapping("/edit")
    public String editFromMenu(Model model,@RequestParam(value = "foldername",required = false) String name,
                               @RequestParam(value = "childfolders", required = false) String childFolders) {
        List<String> childList = List.of(childFolders.split("(,)|(, )"));
        FolderDTO folderDTO = new FolderDTO(name, childList);
        return foldersService.updateFolder(name, folderDTO, model);
    }
}
