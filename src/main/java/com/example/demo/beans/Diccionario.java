package com.example.demo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="diccionario")
public class Diccionario {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="espanol")
	private String espanol;
	
	@Column(name="ingles")
	private String ingles;
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="categoria")
	private String categoria;
	
	@Column(name="num_uso")
	private int num_uso;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEspanol() {
		return espanol;
	}

	public void setEspanol(String espanol) {
		this.espanol = espanol;
	}

	public String getIngles() {
		return ingles;
	}

	public void setIngles(String ingles) {
		this.ingles = ingles;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	
	public int getNum_uso() {
		return num_uso;
	}

	public void setNum_uso(int num_uso) {
		this.num_uso = num_uso;
	}

	@Override
	public String toString() {
		return "Diccionario [id=" + id + ", espanol=" + espanol + ", ingles=" + ingles + ", tipo=" + tipo
				+ ", categoria=" + categoria + ", num_uso=" + num_uso + "]";
	}
	
	
	

}
