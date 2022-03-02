package com.seguimiento.acciones.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Accion {
	
	private LocalDateTime fecha;
	private BigDecimal valorAccionCierre;
	
	public Accion(LocalDateTime fecha, BigDecimal valorAccionCierre) {
		super();
		this.fecha = fecha;
		this.valorAccionCierre = valorAccionCierre;
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
	
	
}
