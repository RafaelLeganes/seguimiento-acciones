package com.seguimiento.acciones.menu;


import java.util.Timer;
import com.seguimiento.acciones.facade.SeguimientoFacade;


public class Principal{

	public static void main(String[] args) {
		Timer t = new Timer();
		SeguimientoFacade s = new SeguimientoFacade();
		t.scheduleAtFixedRate(s, 0, 10000);
	}


}
