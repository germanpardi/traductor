package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.beans.Diccionario;

public interface DiccionarioRepository extends CrudRepository<Diccionario, Integer> {
	
	List<Diccionario> findByTipo(String tipo);
	List<Diccionario> findByEspanol(String ingles);
	List<Diccionario> findByIngles(String espanol);
	List<Diccionario> findByCategoria(String categoria);
	@Query(value="SELECT id FROM traductor.diccionario where espanol=:espanol and ingles =:ingles",nativeQuery=true)
	String findByEspanolIngles(@Param("espanol") String espanol, @Param("ingles") String ingles);
	
	@Query(value="SELECT * FROM diccionario WHERE espanol LIKE :textobuscar OR ingles LIKE :textobuscar", nativeQuery=true)
	public List<Diccionario> findtextobucado(@Param("textobuscar") String textobuscar);
	
	@Query(value="SELECT max(id) FROM traductor.diccionario;",nativeQuery=true)
	int findMAXId();  
}
