package com.example.functionality_two.repositories;

import com.example.functionality_two.entities.Folder;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoldersJpaRepository extends JpaRepository<Folder,String> {
    Optional<Folder> findByName(String filename) throws EntityNotFoundException;
}
