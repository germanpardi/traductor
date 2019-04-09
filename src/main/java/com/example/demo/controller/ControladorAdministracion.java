package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.beans.Diccionario;
import com.example.demo.dto.DiccionarioDTO;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.interfaces.IDiccionarioSERVICE;
import com.example.demo.interfaces.IUsuarioSERVICE;

@Controller
public class ControladorAdministracion {
	
	@Autowired
	IDiccionarioSERVICE dservice;
	
	@Autowired
	IUsuarioSERVICE uservice;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		
		return "index";
	}
	@RequestMapping("/administrar")
	public String administrar(HttpServletRequest request) {
		
		return "indexusuario";
	}
	
	
	
	@RequestMapping("/indexusuario")
	public String indexusuario(HttpServletRequest request) {
		
		return "indexusuario";
		
	}
	@RequestMapping("/opcionesadministrar")
	public String opcionesadministrar(HttpServletRequest request) {
		String siguientepagina ="";
		String idUsuario= request.getParameter("id_usuario");
		String password = request.getParameter("password");
		UsuarioDTO aux = uservice.buscarPorId(idUsuario);
		
		if(aux==null) {
			siguientepagina = "indexusuario";
			
			request.setAttribute("mensaje_usuario", "El usuario no existe");
			return siguientepagina;
		}
		if ((idUsuario.equals(aux.getIdUsuario()))&&(password.equals(aux.getPassword()))) {
			siguientepagina= "administrar";
			
			request.setAttribute("idUsuario", idUsuario);
			
			return siguientepagina;
		}else {
			siguientepagina = "indexusuario";
			
			request.setAttribute("mensaje_password", "Password incorrecta");
			return siguientepagina;
		}
			
	}
	@RequestMapping("/buscarpalabras")
	public String buscarpalabras(HttpServletRequest request) {
		
		String tipo= "p";
		List<DiccionarioDTO> diccionarioDTO = dservice.listarByTipo(tipo);
		request.setAttribute("diccionarioDTO", diccionarioDTO);
	
		return "administrar";
		
	}
	
	@RequestMapping("/buscarfrases")
	public String buscarfrases(HttpServletRequest request) {
		
		String tipo= "f";
		List<DiccionarioDTO> diccionarioDTO = dservice.listarByTipo(tipo);
		request.setAttribute("diccionarioDTO", diccionarioDTO);
	
		return "administrar";
	}
	
	@RequestMapping("/editar")
	public String editar(HttpServletRequest request) {
		
		String id= request.getParameter("id");
		DiccionarioDTO diccionarioDTO = dservice.buscarDiccionario(Integer.parseInt(id));
		request.setAttribute("diccionarioDTO", diccionarioDTO);
	
		return "editardiccionario";
	}
	@RequestMapping("/modificar")
	public String modificar(HttpServletRequest request) {
		String mensajeM ="";
		String id= request.getParameter("id");
		String espanol = request.getParameter("espanol");
		String ingles = request.getParameter("ingles");
		String tipo = request.getParameter("tipo");
		String categoria = request.getParameter("categoria");
		String num_uso = (String) request.getParameter("num_uso");
		System.out.println(num_uso);
		System.out.println(categoria);
		System.out.println(id);
		
		DiccionarioDTO diccionarioDTO = dservice.buscarDiccionario(Integer.parseInt(id));
		diccionarioDTO.setEspanol(espanol);
		diccionarioDTO.setIngles(ingles);
		diccionarioDTO.setTipo(tipo);
		diccionarioDTO.setCategoria(categoria);
		diccionarioDTO.setNum_uso(Integer.parseInt(num_uso));
		
		
		Diccionario diccionario = new Diccionario();
		
		
		diccionario.setId(diccionarioDTO.getId());
		diccionario.setEspanol(diccionarioDTO.getEspanol());
     	diccionario.setIngles(diccionarioDTO.getIngles());
		diccionario.setTipo(diccionarioDTO.getTipo());
		diccionario.setCategoria(diccionarioDTO.getCategoria());
		diccionario.setNum_uso(diccionarioDTO.getNum_uso());
		System.out.println(diccionario);
		
		dservice.altaDiccionario(diccionario);
		mensajeM = "Se ha modificado correctamente";
		request.setAttribute("diccionarioDTO", diccionarioDTO);
		request.setAttribute("mensajeM", mensajeM);
		
		return "editardiccionario";
	}
	
	@RequestMapping("/borrar")
	public String borrar(HttpServletRequest request) {
		
		String id= request.getParameter("id");
	
		DiccionarioDTO aux = dservice.buscarDiccionario(Integer.parseInt(id));
		
		String tipo = aux.getTipo();
		dservice.borrarDiccionario(Integer.parseInt(id));
		List<DiccionarioDTO> diccionarioDTO = dservice.listarByTipo(tipo);
		request.setAttribute("diccionarioDTO", diccionarioDTO);
	
		return "administrar";
	}
	@RequestMapping("/buscar/{textobuscar}")     // para devolver en json y sacando solo 1 parametro
	//public @ResponseBody ArrayList<User> devolverusuarios(@PathVariable String textobuscar) {		//<<<para probar con @PathVariable !!sin codigo entre parentesis!!!!
	public @ResponseBody List<DiccionarioDTO> devolverdiccionarios(@RequestParam("textobuscar") String textobuscar) {		//<<<AJAX cuidado
		textobuscar= textobuscar+"%";
		System.out.println("controlador - devolverusuarios: " + textobuscar);
		
		
		
		List<DiccionarioDTO> diccionarioDTO= dservice.buscarTexto(textobuscar); 
		
//		if (diccionarioDTO != null) {
//			for (DiccionarioDTO diccionarioDTO2 : diccionarioDTO) {
//				System.out.println(diccionarioDTO2);
//			}
//		}
		return diccionarioDTO; //la list. 
	}
	
	
	
		
}
	



