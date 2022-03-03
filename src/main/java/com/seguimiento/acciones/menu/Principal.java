package com.seguimiento.acciones.menu;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.seguimiento.acciones.facade.SeguimientoFacade;



public class Principal{

	
	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationContext = new AnnotationConfigApplicationContext();
		annotationContext.scan("com.seguimiento.acciones.facade");
		annotationContext.refresh();
		SeguimientoFacade seguimiento = null;
		try {
			seguimiento = annotationContext.getBean(SeguimientoFacade.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		seguimiento.pararPrincipal();
		annotationContext.close();
	}
}
