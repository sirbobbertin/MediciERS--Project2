package com.project2.mediciERS.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project2.mediciERS.entity.File;
import com.project2.mediciERS.message.ResponseFile;
import com.project2.mediciERS.service.FileStorageService;

@Controller
@CrossOrigin
public class FileController {

	private final FileStorageService storageService;

	@Autowired
  public FileController(FileStorageService storageService) {
	  this.storageService = storageService;
  }

	// UPLOAD A FILE
	@PostMapping("upload/{rbid}")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("rbid") int rbid) {
		String message = "";
		try {
			storageService.save(file, rbid);

			return ResponseEntity.status(HttpStatus.OK)
					.body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
		}

	}

	// GET ALL FILES
		@GetMapping("/files")
		public ResponseEntity<List<ResponseFile>> getListFiles() {
			List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
				String fileDownloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
						.path(dbFile.getId()).toUriString();

				return new ResponseFile(dbFile.getId(), dbFile.getName(), fileDownloadURL, dbFile.getType(), dbFile.getData().length, dbFile.getRbId());
			}).collect(Collectors.toList());

			return ResponseEntity.status(HttpStatus.OK).body(files);
		}

	// GET A FILE BY IMAGE ID
	@GetMapping(value = "/files/{id}", produces = MediaType.IMAGE_GIF_VALUE)
	public ResponseEntity<byte[]> getFile(@PathVariable String id) {
		File dbFile = storageService.getFile(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getName() + "\"")
				.body(dbFile.getData());
	}
	
	// GET A FILE BY REIMBURSEMENT ID
	@GetMapping(value = "files/reimbursement/{rbid}", produces = MediaType.IMAGE_GIF_VALUE)
	public ResponseEntity<byte[]> getFile2(@PathVariable("rbid") int rbId) {
	
		Optional <File> dbFile = storageService.getFile2(rbId);
		
		if (!dbFile.isPresent()) {
			return ResponseEntity.notFound()
								 .build();
		}
		
		File fileEntity = dbFile.get();
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
				.body(fileEntity.getData());
	}
	
}
