package com.project2.mediciERS.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project2.mediciERS.entity.File;

@Repository
public interface FileRepositoryDao extends JpaRepository<File, String> {
	
	Optional<File> findByRbId(int rbId);

}
