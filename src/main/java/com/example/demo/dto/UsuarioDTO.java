package com.example.demo.dto;


import java.util.List;

import com.example.demo.excepciones.ExcepcionDomain;


public class UsuarioDTO {
	
	private String idUsuario;
	private String password;
	
	
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UsuarioDTO(String idUsuario, String password) {
		super();
		this.idUsuario = idUsuario;
		this.password = password;
	}
	public UsuarioDTO() {
		super();
	}	

}
