package com.seguimiento.acciones.menu;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.seguimiento.acciones.controller.SeguimientoAccionesController;
import com.seguimiento.acciones.model.Accion;
import com.seguimiento.acciones.utilities.FileResourceUtils;

public class Principal extends TimerTask{

	public static void main(String[] args) {
		Timer t = new Timer();
		Principal p = new Principal();
		t.scheduleAtFixedRate(p, 0, 10000);
	}


	@Override
	public void run() {
		FileResourceUtils app = new FileResourceUtils();
		InputStream is = app.getFileFromResourceAsStream("configuration.properties");
		Properties appProps = new Properties();
		ExecutorService executor = null;

		try {
			appProps.load(is);
			Set<String> listapropiedades = appProps.stringPropertyNames();
			int cantidadAcciones = listapropiedades.size();
			executor = Executors.newFixedThreadPool(cantidadAcciones);
			Collection<Callable<String>> callables = new ArrayList<>();
			for (Iterator<String> it = listapropiedades.iterator(); it.hasNext();) {
				String value = (String) it.next();
				String url = appProps.getProperty(value);
				callables.add(() -> procesarFichero(value, url));
			}
			List<Future<String>> result = executor.invokeAll(callables);
			for (Future<String> f : result) {
				System.out.println(f.get());
			}
		} catch (IOException | InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		executor.shutdown();
		System.out.println("Te veo en 10 segundos");
		
	}
	
	private static String procesarFichero(String nombre, String url) {
		List<Accion> listaAcciones = new ArrayList<Accion>();
		String msg = null;
		SeguimientoAccionesController controller = new SeguimientoAccionesController();
		controller.descargarFicheroCSV(nombre, url);
		listaAcciones = controller.leerFicheroCSV(nombre + ".csv");
		msg = "Acciones de " + nombre + " con Fecha:" + listaAcciones.get(listaAcciones.size() - 1).getFecha()
				+ " Valor cierre:" + listaAcciones.get(listaAcciones.size() - 1).getValorAccionCierre();
		return msg;
	}

}
