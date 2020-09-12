package com.example.demo.exception;

import java.io.IOException;
/**
 * 
 * @author Venkatraman Athmanathan
 *
 */
public class FileStorageException extends IOException {


	private static final long serialVersionUID = 1L;
	/**
	 * FileStorageException Constructor is used to call super class constructor 
	 * @param string
	 */
	public FileStorageException(String string) {
		super(string);
	}

}
