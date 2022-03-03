package com.seguimiento.acciones.utilities;

import java.io.InputStream;


public class FileResourceUtils {
	

	public InputStream getFileFromResourceAsStream(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);
		
		if(inputStream == null) {
			throw new IllegalArgumentException("Archivo no encontrado! "+ fileName);
		} else {
			return inputStream;
		}
	}
}
