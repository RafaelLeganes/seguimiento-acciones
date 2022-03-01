package com.seguimiento.acciones.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Accion {
	
	private LocalDate fecha;
	private BigDecimal valorAccionCierre;
	
	public Accion(LocalDate fecha, BigDecimal valorAccionCierre) {
		super();
		this.fecha = fecha;
		this.valorAccionCierre = valorAccionCierre;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getValorAccionCierre() {
		return valorAccionCierre;
	}

	public void setValorAccionCierre(BigDecimal valorAccionCierre) {
		this.valorAccionCierre = valorAccionCierre;
	}
	
	
}
