package com.seguimiento.acciones.controller;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import com.opencsv.CSVReader;
import com.seguimiento.acciones.model.Accion;


public class SeguimientoAccionesController {
	
	public void descargarFicheroCSV(String nombre, String url) {
		try(BufferedInputStream in = new BufferedInputStream(new URL(url).openStream()) ;
			FileOutputStream fileOutputStream = new FileOutputStream("C:/Users/Administrador.LIFERAY/Desktop/examen/"+nombre+".csv")) {
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
	
	public Accion leerFicheroCSV(String nombreFichero){
		Accion accion = null;
		try(CSVReader reader = new CSVReader(new FileReader("C:/Users/Administrador.LIFERAY/Desktop/examen/"+nombreFichero),',')){
			String[] nextLine = null;
			reader.readNext();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime ahora = LocalDateTime.now();
			String hora= String.valueOf(ahora.getHour());
			if(hora.length()==1) {
				hora = 0+hora;
			}
			String minutos = String.valueOf(ahora.getMinute());
			if(minutos.length()==1) {
				minutos = 0+minutos;
			}
			while((nextLine = reader.readNext()) != null) {
				LocalDateTime date = LocalDateTime.parse(nextLine[0]+" "+hora+":"+minutos,formatter);
				accion = new Accion(date, new BigDecimal(nextLine[4]), nombreFichero.replace(".csv", ""));

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return accion;
	}

}
