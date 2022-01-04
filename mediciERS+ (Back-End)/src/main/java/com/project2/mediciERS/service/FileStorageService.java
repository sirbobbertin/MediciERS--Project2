package com.project2.mediciERS.service;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.project2.mediciERS.dao.FileRepositoryDao;
import com.project2.mediciERS.entity.File;

@Service
public class FileStorageService {

	private final FileRepositoryDao fileRepository;
	
	@Autowired
	public FileStorageService(FileRepositoryDao fileRepository) {
		this.fileRepository = fileRepository;
	}

	public void save(MultipartFile file, int rbid) throws IOException {
        File fileEntity = new File();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setType(file.getContentType());
        fileEntity.setRbId(rbid);
        fileEntity.setData(file.getBytes());
        fileRepository.save(fileEntity);
        
	}

	public File getFile(String Id) {
		return fileRepository.findById(Id).get();
	}

	public Stream<File> getAllFiles() {
		return fileRepository.findAll().stream();
	}
	
	@Transactional
	public Optional<File> getFile2(int rbId) {
		return fileRepository.findByRbId(rbId);
	}

}