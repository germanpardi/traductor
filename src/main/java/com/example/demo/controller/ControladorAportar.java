package com.example.demo.controller;




import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.beans.Diccionario;
import com.example.demo.interfaces.IDiccionarioSERVICE;
import com.example.demo.util.ValidarTransformar;


@Controller				//--->> lo mas importante y lo que indica que es el controlador.
@RequestMapping("/")
public class ControladorAportar {
//Controlador general
	
	@Autowired
    private IDiccionarioSERVICE diccionarioservice;
		
	@RequestMapping("/aportar")	     //   http://localhost:8080/aportar
	public String aportar (HttpServletRequest request) {
		boolean seguir=true;
		System.out.println("ControladoAportar - aportar");
		
		String aportarEsp=request.getParameter("aportarEsp"); 
		String aportarIng=request.getParameter("aportarIng");
		
		
		ValidarTransformar vt=new ValidarTransformar();
		aportarEsp=vt.quitarespaciosdobles(aportarEsp);
		aportarIng=vt.quitarespaciosdobles(aportarIng);
		
		
		if(aportarEsp.equals("")|aportarEsp==null) {
			seguir=false;
			request.setAttribute("mensaje", "Campo vacio. Por favor escriba aportacion"); 
		}
		if(aportarIng.equals("")|aportarIng==null) {
			seguir=false;
		}
			
		if (seguir) {
						
			Diccionario diccionario=new Diccionario();
				
			diccionario.setId(0);
			diccionario.setEspanol(aportarEsp);
			diccionario.setIngles(aportarIng);
			
			System.out.println("Vamos a averuiguar tipo de frase aportarEsp: |" + aportarEsp+"|");
			String tipoEsp=vt.averiguarTipo(aportarEsp);
			System.out.println("tipoEsp" + tipoEsp);
			
			System.out.println("Vamos a averuiguar tipo de frase aportarIng: |" + aportarIng+"|");
			String tipoIng=vt.averiguarTipo(aportarIng);
			System.out.println("tipoIng" + tipoIng);
			
			if (tipoEsp.equals("f")|tipoIng.equals("f")) {
				diccionario.setTipo("f");
			}else{
				diccionario.setTipo("p");
			}
			
			diccionario.setCategoria("apo");
			diccionario.setNum_uso(1); 
			
			String id=diccionarioservice.encontarparejaespanolingles(diccionario.getEspanol(), diccionario.getIngles());
			System.out.println("ACCESO A TABLA DICCIONARIO. id DEVUELTO: " + id);
			int numid=Integer.parseInt(id);
		
			if(numid<0) { //es que NO EXISTE, podemos darla de alta. Vienen el valor -1.
				diccionarioservice.altaDiccionario(diccionario);
				request.setAttribute("mensaje", "Alta realizada. Gracias por su aportacion."); 
			
			}else {
				request.setAttribute("mensaje", "La aportacion ya existe. "); 
			}
		}else {
			request.setAttribute("mensaje", "Campo vacio. Por favor escriba aportacion"); 
		}
		
		request.setAttribute("aportarEsp", aportarEsp);
		request.setAttribute("aportarIng", aportarIng);
		
		
		return "index";
	}    //   http://localhost:8080/file/cargamasiva
	
		
	
}
