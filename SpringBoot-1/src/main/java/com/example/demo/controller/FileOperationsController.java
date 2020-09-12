package com.example.demo.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.FileStorageException;

/**
 * @author Venkatraman Athmanathan
 *
 */
@RestController
@RequestMapping("/api")
public class FileOperationsController {
	
	/*
	 * logger Variable is used to print method logs at begining and end of the class
	 */
	private static final Logger logger = LoggerFactory.getLogger(FileOperationsController.class);
	
	/**
	 * downLoadFile Method is used to download file when it is accessed.
	 * @return responceEntity
	 * @throws IOException
	 */
	@RequestMapping(value ="/downLoadFile",method = RequestMethod.GET)
	public ResponseEntity<Resource> downLoadFile() throws IOException{
		logger.info("downLoadFile Method Starts");
		long contextLength =Long.MIN_VALUE;
		String fileName;
		ResponseEntity<Resource> responceEntity = null;
		File file = new
				File("C:/Users/ADMIN/Documents/details.pdf");
		Resource resource = new UrlResource(file.toURI());
		contextLength = resource.contentLength();
		fileName = resource.getFilename();
		if(file.exists() ) {
			responceEntity = ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(contextLength).
					header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"",fileName)).body(resource); 
		}
		else {
			throw new FileStorageException("File Does't Exists");
		}
		logger.info("downLoadFile Method Ends");
		return responceEntity;

	}
	
	/**
	 * copyFile method is used to Copy File in location when it is accessed.
	 * @return COPIED
	 * @throws IOException
	 */
	@RequestMapping(value = "/copyFile",method = RequestMethod.POST)
	public String copyFile() throws IOException {
		logger.info("copyFile Method Starts");
		File filePath = new File("C:/Users/ADMIN/Documents/details.pdf");
		Path newPath = Paths.get("C:/Users/ADMIN/Downloads/");
		if(filePath.exists()) {
			FileUtils.copyFileToDirectory(filePath, newPath.toFile());
		}else {
			throw new FileStorageException("File Not Found Exception");
		}
		logger.info("copyFile Method Ends");
		return "COPIED";
	}
	
	/**
	 * deleteFile Method is used to delete a file when it is accessed.
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value ="/deleteFile" ,method = RequestMethod.DELETE)
	public String deleteFile() throws IOException {
		logger.info("deleteFile Method Starts");
		File fileName = new File("C:/Users/ADMIN/Downloads/details.pdf");
		if(fileName.exists()) {
			Files.delete(fileName.toPath());
		}else {
			throw new FileStorageException("File Not Present");
		}
		logger.info("deleteFile Method Ends");
		return "DELETED";
	}

}
