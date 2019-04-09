package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.beans.Usuario;
import com.example.demo.dao.UsuariosRepository;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.interfaces.IUsuarioSERVICE;


@Service
public class UsuarioSERVICE implements IUsuarioSERVICE {
	
	@Autowired
	UsuariosRepository usuariosRepository;

	@Override
	public List<UsuarioDTO> listarUsuarios() {
		List<UsuarioDTO> usuarios_salida_dto = new ArrayList<UsuarioDTO>();
		Iterable<Usuario> usuarios = usuariosRepository.findAll();
		for (Usuario usuario : usuarios) {
			UsuarioDTO UsuarioDTO = new UsuarioDTO(usuario.getIdUsuario(),usuario.getPassword());
			usuarios_salida_dto.add(UsuarioDTO);
		}
		return usuarios_salida_dto;
	}

	@Override
	public void borrarUsuario(String id_usuario) {
		// TODO Auto-generated method stub
		usuariosRepository.deleteById(id_usuario);
	}

	@Override
	public void addUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		usuariosRepository.save(usuario);

	}

	@Override
	public UsuarioDTO buscarPorId(String idUsuario) {
		Optional<Usuario> usuario = usuariosRepository.findById(idUsuario);
		if(usuario.isPresent()) {
			Usuario uaux = usuario.get();
			UsuarioDTO UsuarioDTO = new UsuarioDTO(uaux.getIdUsuario(),uaux.getPassword());
			return UsuarioDTO;
		}
		return null;
	}


}
