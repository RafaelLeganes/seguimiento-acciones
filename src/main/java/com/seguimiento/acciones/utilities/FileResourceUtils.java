package com.seguimiento.acciones.utilities;

import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileResourceUtils {
	
	private static final Logger logger = LogManager.getLogger(FileResourceUtils.class);

	public InputStream getFileFromResourceAsStream(String fileName) {
		logger.debug("Llamada al metodo getFileFromResourceAsStream con parametro: {}",fileName);
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);
		
		if(inputStream == null) {
			logger.error("Archivo no encontrado! {}", fileName);
			throw new IllegalArgumentException("Archivo no encontrado! "+ fileName);
		} else {
			logger.debug("Devolucion del metodo getFileFromResourceAsStream con parametro: {}",inputStream.toString());
			return inputStream;
		}
	}
}
