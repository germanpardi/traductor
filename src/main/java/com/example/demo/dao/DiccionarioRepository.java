package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.beans.Diccionario;

public interface DiccionarioRepository extends CrudRepository<Diccionario, Integer> {
	
	List<Diccionario> findByTipo(String tipo);
	List<Diccionario> findByEspanol(String espanol);
	List<Diccionario> findByIngles(String ingles);
	
	@Query(value="SELECT * FROM diccionario WHERE espanol LIKE :textobuscar OR ingles LIKE :textobuscar", nativeQuery=true)
	public List<Diccionario> findtextobucado(@Param("textobuscar") String textobuscar);
}
