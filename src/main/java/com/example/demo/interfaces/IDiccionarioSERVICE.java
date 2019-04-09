package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.beans.Diccionario;
import com.example.demo.dto.DiccionarioDTO;

public interface IDiccionarioSERVICE {
	
	public List<String> listarEspanol(String espanol);
	public List<String> listarIngles(String ingles);
	public List<DiccionarioDTO> listarDiccionarios();
	public List<DiccionarioDTO> listarByTipo(String tipo);
	public void altaDiccionario(Diccionario dic);
	public void borrarDiccionario(int id);
	public DiccionarioDTO buscarDiccionario(int id);
	public List<DiccionarioDTO> buscarTexto(String textobuscar);
	public List<DiccionarioDTO> listarAportes(String categoria);
	String encontarparejaespanolingles(String espanol, String ingles);
	void altaDiccionario(List<Diccionario> listadiccionario);
	int maximoid();
}
