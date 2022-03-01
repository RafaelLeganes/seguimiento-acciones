package com.seguimiento.acciones.controller;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class SeguimientoAccionesController {
	
	public void leerFicheroCSV(String nombre, String url) {
		try(BufferedInputStream in = new BufferedInputStream(new URL(url).openStream()) ;
			FileOutputStream fileOutputStream = new FileOutputStream(nombre+".csv")) {
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
