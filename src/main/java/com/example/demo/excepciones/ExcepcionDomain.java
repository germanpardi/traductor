package com.example.demo.excepciones;

public class ExcepcionDomain extends RuntimeException {
	
	private String mensaje="";
	
	public ExcepcionDomain (){}

	public ExcepcionDomain(String mensaje) {

		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "ExcepcionDomain [mensaje=" + mensaje + "]";
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}