package com.seguimiento.acciones.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Accion {
	
	private LocalDateTime fecha;
	private BigDecimal valorAccionCierre;
	private String nombre;
	
	public Accion(LocalDateTime fecha, BigDecimal valorAccionCierre, String nombre) {
		super();
		this.fecha = fecha;
		this.valorAccionCierre = valorAccionCierre;
		this.nombre = nombre;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getValorAccionCierre() {
		return valorAccionCierre;
	}

	public void setValorAccionCierre(BigDecimal valorAccionCierre) {
		this.valorAccionCierre = valorAccionCierre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
