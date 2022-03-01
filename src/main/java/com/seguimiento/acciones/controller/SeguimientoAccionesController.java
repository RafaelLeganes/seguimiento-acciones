package com.seguimiento.acciones.controller;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.seguimiento.acciones.model.Accion;
import com.seguimiento.acciones.utilities.FileResourceUtils;

public class SeguimientoAccionesController {
	
	public void descargarFicheroCSV(String nombre, String url) {
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
	
	public List<Accion> leerFicheroCSV(String nombreFichero){
		List<Accion> listaAcciones = new ArrayList<Accion>();
		FileResourceUtils app = new FileResourceUtils();
		InputStream is = app.getFileFromResourceAsStream(nombreFichero);
		try(CSVReader reader = new CSVReader(new InputStreamReader(is, "UTF-8"),',')){
			String[] nextLine = null;
			reader.readNext();
			while((nextLine = reader.readNext()) != null) {
				LocalDate date = LocalDate.parse(nextLine[0]);
				Accion accion = new Accion(date, new BigDecimal(nextLine[4]));
				listaAcciones .add(accion);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaAcciones;
	}

}
