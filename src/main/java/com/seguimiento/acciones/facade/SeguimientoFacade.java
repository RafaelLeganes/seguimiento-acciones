package com.seguimiento.acciones.facade;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.seguimiento.acciones.controller.SeguimientoAccionesController;
import com.seguimiento.acciones.model.Accion;
import com.seguimiento.acciones.utilities.FileResourceUtils;

@Configuration
@EnableAsync
@EnableScheduling
@Component
public class SeguimientoFacade {

	private static boolean done =false;
	
	public static boolean isDone() {
		return done;
	}
	
	public synchronized void pararPrincipal() {
		while(!SeguimientoFacade.isDone()) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	
	@Scheduled(fixedRate =10000)
	public void obtenerResultados() throws Exception{
		FileResourceUtils app = new FileResourceUtils();
		InputStream is = app.getFileFromResourceAsStream("configuration.properties");
		Properties appProps = new Properties();
		ExecutorService executor = null;
		try {
			appProps.load(is);
			Set<String> listapropiedades = appProps.stringPropertyNames();
			int cantidadAcciones = listapropiedades.size();
			executor = Executors.newFixedThreadPool(cantidadAcciones);
			Collection<Callable<Accion>> callables = new ArrayList<>();
			for (Iterator<String> it = listapropiedades.iterator(); it.hasNext();) {
				String value = (String) it.next();
				String url = appProps.getProperty(value);
				callables.add(() -> procesarFichero(value, url));
			}
			List<Future<Accion>> result = executor.invokeAll(callables);
			for (Future<Accion> f : result) {
				Accion acc = f.get();
					System.out.println("Nombre Accion:"+acc.getNombre()+" Fecha:"+acc.getFecha() + " Valor Cierre:" +acc.getValorAccionCierre());
			}
		} catch (IOException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new Exception("No se pudo obtener resultados! ");
		}

		executor.shutdown();
		System.out.println("Te veo en 10 segundos");
	}
	
	private static Accion procesarFichero(String nombre, String url) throws Exception{
		Accion accion = null;
		SeguimientoAccionesController controller = new SeguimientoAccionesController();
		controller.descargarFicheroCSV(nombre, url);
		accion = controller.leerFicheroCSV(nombre + ".csv");
		return accion;
	}
}
