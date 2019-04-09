package com.example.demo.dto;

public class DiccionarioDTO {
	
		
	public DiccionarioDTO(int id, String espanol, String ingles, String tipo, String categoria, int num_uso) {
		super();
		this.id = id;
		this.espanol = espanol;
		this.ingles = ingles;
		this.tipo = tipo;
		this.categoria = categoria;
		this.num_uso = num_uso;
	}
	

	public DiccionarioDTO() {
		super();
	}


	private int id;
		
	private String espanol;
		
	private String ingles;
		
	private String tipo;
		
	private String categoria;
		
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



