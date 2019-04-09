package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.beans.Usuario;

import com.example.demo.dto.UsuarioDTO;




public interface IUsuarioSERVICE {
	
	public List<UsuarioDTO> listarUsuarios();
	public void borrarUsuario(String id_usuario);
	public void addUsuario(Usuario usuario);
	public UsuarioDTO buscarPorId(String id_usuario);
	

}
