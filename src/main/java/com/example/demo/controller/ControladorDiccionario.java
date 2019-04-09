package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.dto.DiccionarioDTO;
import com.example.demo.interfaces.IDiccionarioSERVICE;
import com.example.demo.interfaces.IUsuarioSERVICE;
import com.example.demo.util.ValidarTransformar;

@Controller
@RequestMapping("/")
public class ControladorDiccionario {
	
	String diccionario=""; //Inicializo el diccionario como no seleccionado
	String salida="";//Inicializo vacia la salida, para ir "traduciendo" palabra por palabra
	
	Hashtable<String, List<String>> espanolInglesPalabra = new Hashtable<String, List<String>>();
	Hashtable<String, List<String>> espanolInglesFrase = new Hashtable<String, List<String>>();
	Hashtable<String, List<String>> inglesEspanolPalabra = new Hashtable<String, List<String>>();
	Hashtable<String, List<String>> inglesEspanolFrase = new Hashtable<String, List<String>>();	
	
	@Autowired
	IDiccionarioSERVICE diccionarioService;
	
	/*
	 * Tarea final:
	 * Para los nombres acabados en "S"(EJ: mundo -- mundos(esto en palabras)), comprobarlos y cambiar el singular dejando la S(lo haces igual, solo compriebas antes de mirar el diccionario
	 * comprobando que no sea una s) (cuidado con los verbos, por ejemplo ves, que no lo haga, si lo hago dos veces(en el))
	 */
	
	@RequestMapping("/")
	public String inicio(HttpServletRequest request) {
		
			
			List<DiccionarioDTO> listaPalabras = diccionarioService.listarByTipo("p");
	
			List<DiccionarioDTO> listaFrases = diccionarioService.listarByTipo("f");
			
			
			for (DiccionarioDTO diccionarioDTO : listaFrases) {
				espanolInglesFrase.put(diccionarioDTO.getEspanol().toLowerCase(), diccionarioService.listarIngles(diccionarioDTO.getEspanol().toLowerCase()));
				inglesEspanolFrase.put(diccionarioDTO.getIngles().toLowerCase(), diccionarioService.listarEspanol(diccionarioDTO.getIngles().toLowerCase()));	
			}
			
			
			for (DiccionarioDTO diccionarioDTO : listaPalabras) {
				espanolInglesPalabra.put(diccionarioDTO.getEspanol().toLowerCase(), diccionarioService.listarIngles(diccionarioDTO.getEspanol().toLowerCase()));
				inglesEspanolPalabra.put(diccionarioDTO.getIngles().toLowerCase(), diccionarioService.listarEspanol(diccionarioDTO.getIngles().toLowerCase()));	
			}
		
		

		return "index";
	}
	
	@RequestMapping("/index") //Si pongo esta ruta, quiero que siempre me carge de nuevo los diccionarios(es decir si se produce algun cambio llamo aqui, sino arriba)
	public String index(HttpServletRequest request) {
		List<DiccionarioDTO> listaPalabras = diccionarioService.listarByTipo("p");

		List<DiccionarioDTO> listaFrases = diccionarioService.listarByTipo("f");
		
		
		for (DiccionarioDTO diccionarioDTO : listaFrases) {
			espanolInglesFrase.put(diccionarioDTO.getEspanol().toLowerCase(), diccionarioService.listarIngles(diccionarioDTO.getEspanol().toLowerCase()));
			inglesEspanolFrase.put(diccionarioDTO.getIngles().toLowerCase(), diccionarioService.listarEspanol(diccionarioDTO.getIngles().toLowerCase()));	
		}
		
		
		for (DiccionarioDTO diccionarioDTO : listaPalabras) {
			espanolInglesPalabra.put(diccionarioDTO.getEspanol().toLowerCase(), diccionarioService.listarIngles(diccionarioDTO.getEspanol().toLowerCase()));
			inglesEspanolPalabra.put(diccionarioDTO.getIngles().toLowerCase(), diccionarioService.listarEspanol(diccionarioDTO.getIngles().toLowerCase()));	
		}
		

		return "index";
	}
	
	@RequestMapping("/traducir")
	public String traducir(HttpServletRequest request) {
		if(espanolInglesPalabra.size()<1) {
			return index(request);
		}
		
		String inputText=request.getParameter("inputText");
		//Volvemos a escribir la palabra que escribio el usuario
		request.setAttribute("inputText", inputText);
		ValidarTransformar vt = new ValidarTransformar();
		
		inputText=vt.quitarespaciosdobles(inputText);
		inputText = inputText.toLowerCase();
		
		salida=inputText;
		
		salida = salida + " q"; 
		
		
			//Compruebo frase, si: estoy buscando, en español, (debe estar en español ya que lo compruebo cuando existe una unica palabra) y estoy buscando. 
			//pd: Una vez dentro me encontraria todas las frases que coincidan dentro del array del String
			if(diccionario.equals("espanol")) {
				//Separo cada frase dentro del String
				String [] frases = salida.split(",|;|\\.");
				//System.out.println(frases.length);
				for(int i=0;i<frases.length;i++) {
					//Ahora realizo un Split por " " para devolver cada palabra de la frase
					String [] palabras = frases[i].split(" ");
					//Establezco dos marcadores, uno de inicio y otro de fin
					int inicio=0;//Primera palabra dentro del String
					int fin=palabras.length-1;//Ultima palabra dentro del String
					int retorno=1;//Conforme mas iteraciones quiero que el retorno de fin, sea mayor, me creo un contador fuera.
					while(true) {
						//Pongo la condicion de salida
						if((fin-inicio)<2&&fin==palabras.length-1) {break;}
						String cadenaTexto="";
						for(int k=inicio;k<=fin;k++) {
							cadenaTexto= cadenaTexto + " " + palabras[k];
						}
						System.out.println("Cadena buscada " + cadenaTexto );
						
						if(this.comprobarFraseEspañol(request, cadenaTexto)) {
							//Si encuentra la frase, la traduccira, y quiero que cierre esta frase
							break;
						}else {
							//Si no ha pasado, quiero que si fin no esta al final, se mueva hacia la derecha, si esta al final que acorte 1 la diferencia y empiece desde el inicio
							if(fin<palabras.length-1) {
								inicio ++;
								fin++;
							}else {
								//Restablezco el inicio
								inicio=0;
								//El fin de la comparacion sera un String mas pequeño que el anterior.
								fin = (palabras.length-1)-retorno;
								//El retorno se hace mas grande
								retorno++;								
							}
						}
					}
				
				}
				
			}
			
			
			if(diccionario.equals("ingles")) {
				//Separo cada frase dentro del String(primero con espacios hola, nombre) y despues sin espacios hola,nombre
				String [] frases = salida.split(",|;|\\.");
				System.out.println("Entro");
				for(int i=0;i<frases.length;i++) {
					
					String [] palabras = frases[i].split(" ");
					
					int inicio=0;
					int fin=palabras.length-1;
					int retorno=1;
					while(true) {
						
						if((fin-inicio)<2&&fin==palabras.length-1) {break;}
						String cadenaTexto="";
						System.out.println(inicio + " " + fin);
						for(int k=inicio;k<=fin;k++) {
							cadenaTexto= cadenaTexto + " " + palabras[k];
						}
						System.out.println("Cadena buscada " + cadenaTexto );
						//this.comprobarFraseIngles(request, cadenaTexto);
						if(this.comprobarFraseIngles(request, cadenaTexto)) {
							//Si encuentra la frase, la traduccira, y quiero que cierre esta frase
							break;
						}else {
							//Si no ha pasado, quiero que si fin no esta al final, se mueva hacia la derecha, si esta al final que acorte 1 la diferencia y empiece desde el inicio
							if(fin<palabras.length-1) {
								System.out.println("Entro if");
								inicio ++;
								fin++;
							}else {

								System.out.println("Entro else");
								//Restablezco el inicio
								inicio=0;
								//El fin de la comparacion sera un String mas pequeño que el anterior.
								fin = (palabras.length-1)-retorno;
								//El retorno se hace mas grande
								retorno++;								
							}
						}
					}
				
				}
				
			}
		
		String [] palabras = salida.split(" |,|;|\\.");
			
		for (int i=0;i<palabras.length;i++) {
			//Si el array es 1, empieza a selecionar el diccionario, una vez establecido, no se cambia hasta la siguiente ejecucion del boton(que pulse traduccir)
			if(diccionario.equals("espanol")||diccionario.equals("")) {
				//Seleecionar diccionario
				if(this.comprobarPalabraEspañol(request, palabras[i])) {
					//Si devuelve true, estamos buscando "español" para traduccir
					if(diccionario.equals("")) {
					diccionario="espanol";
					//Una vez encontrado el diccionario, establezco la frase de nuevo para traduccirla
					salida=inputText;
					 return traducir(request);
					}
					//cerramos busqueda
					
				}else {//Si no la he encontrado, realizo otra busqueda, pero solo si el ultimo caracter es "s" para buscar su posible singular(solo si no he encontrado el resultado)
					//pd: ves, por ejemplo, primero buscaves, y al encontrarlo, no viene aqui a encontrar nada, por ejemplo si pone mundos, si lo hara.
					if(palabras[i].length()>0){
						if(palabras[i].charAt(palabras[i].length()-1)=='s') {
							this.comprobarPalabraEspañol(request, palabras[i].substring(0, palabras[i].length()-1));
						}
					}
				}
				
			}
		}
		
		
		
		
		for (int i=0;i<palabras.length;i++) {
			//Si el array es 1, empieza a selecionar el diccionario, una vez establecido, no se cambia hasta la siguiente ejecucion del boton(que pulse traduccir)
			if(diccionario.equals("ingles")||diccionario.equals("")) {
				//Seleecionar diccionario
				if(this.comprobarPalabraIngles(request, palabras[i])) {
					if(diccionario.equals("")) {
						diccionario="ingles";
						//Una vez encontrado el diccionario, establezco la frase de nuevo para traduccirla
						salida=inputText;
						return traducir(request);
					}
					//Cierre busqueda
					
				}else {
					if(palabras[i].length()>0){
						if(palabras[i].charAt(palabras[i].length()-1)=='s') {
							this.comprobarPalabraIngles(request, palabras[i].substring(0, palabras[i].length()-1));
						}
					}
				}
			}
			
		}
		//Quito la ultima letra que la paso para que me lea la frase al final
		salida = salida.substring(0, salida.length()-1);
		
		//Ahora le digo al usuario que palabras no he podido traduccir(las que se repitan en la entrada y la salida, no habra sido traduccidas, asi que las contateno en un String y lo paso)
		String [] arrayTextoEntrada = inputText.split(" |, |\\. ");
		String palabrasNoEncontradas = "Haga su aportacion en las palabras no encontradas: ";
		for (String cadenaBuscada : arrayTextoEntrada) {
//			System.out.println(cadenaBuscada + " " + salida.indexOf(cadenaBuscada));
//			System.out.println(salida);
			if(salida.indexOf(cadenaBuscada)!=-1) {//Si es diferente a -1, lo ha encontrado, por lo tanto no lo habra rtraduccido
				palabrasNoEncontradas = palabrasNoEncontradas + cadenaBuscada + ", ";
			}
		}
		palabrasNoEncontradas=palabrasNoEncontradas.substring(0, palabrasNoEncontradas.length()-2);
		palabrasNoEncontradas += ".";
		//Paso las palabras no encontradas, si contienen alguna palabra
		if(!palabrasNoEncontradas.equals("Haga su aportacion en las palabras no encontradas.")) {
		request.setAttribute("mensaje", palabrasNoEncontradas);
		}
		//Paso la salida como atributo
		request.setAttribute("outputText", salida);
		//Reseteo el diccionario(de donde cojo las palabras y la salida)
		salida="";
		diccionario="";
		
		return "index";
	}
	
	
	public boolean comprobarFraseEspañol(HttpServletRequest request, String texto) {
		while(texto.charAt(0)==' ') {//Si el primer/segundo/tercero caracter es un espacio, que lo quite
			texto = texto.substring(1);
		}
		List<String> listaFrasesEspanolIngles=null;
		if(espanolInglesFrase.containsKey(texto)) {
			listaFrasesEspanolIngles=espanolInglesFrase.get(texto);
			//Si entra no es nulo, por lo tanto rescata valor
			this.cambiar(texto, listaFrasesEspanolIngles.get(0));
			return true;
		}
		
		
		return false;
		
	}
	
	
	public boolean comprobarFraseIngles(HttpServletRequest request, String texto) {
		while(texto.charAt(0)==' ') {//Si el primer/segundo/tercero caracter es un espacio, que lo quite
			texto = texto.substring(1);
		}
		List<String> listaFrasesInglesEspanol=null;
		if(inglesEspanolFrase.containsKey(texto)) {
			System.out.println("Entro comprobar frase ingles dentro del if");
			listaFrasesInglesEspanol=inglesEspanolFrase.get(texto);
			//Si entra no es nulo, por lo tanto rescata valor
			this.cambiar(texto, listaFrasesInglesEspanol.get(0));
			return true;
		}
		return false;
	}
	
	public boolean comprobarPalabraEspañol(HttpServletRequest request, String texto) {
		List<String> listaPalabrasEspanolIngles=null;
		
		if(espanolInglesPalabra.containsKey(texto)) {
			listaPalabrasEspanolIngles=espanolInglesPalabra.get(texto);
			//Si entra no es nulo, por lo tanto rescata valor
			this.cambiar(texto, listaPalabrasEspanolIngles.get(0));
			return true;
		}
		return false;
		
		
	}
	
	public boolean comprobarPalabraIngles(HttpServletRequest request, String texto) {
		List<String> listaPalabrasInglesEspanol=null;
		if(inglesEspanolPalabra.containsKey(texto)) {
			listaPalabrasInglesEspanol=inglesEspanolPalabra.get(texto);
			//Si entra no es nulo, por lo tanto rescata valor
			this.cambiar(texto, listaPalabrasInglesEspanol.get(0));
			return true;
		}
		
		return false;
		
		
	}
	
	
	//Metodo que me cambia las coincidencias, una vez encontradas, dentro del String salida(tanto para frases como para palabras) por las pasadas por parametro
	public void cambiar(String textoSinTraduccir, String textoTraduccido) {
		//Pongo busco el patron dentro del String y lo sustituyo por el traducido(si no encuentro no entro aqui y no lo cambia)
		//No tengo que tener en cuenta los signos de puntuacion
	    salida = salida.replaceAll(textoSinTraduccir, textoTraduccido);
	}
	
	
	
	
	
	
	
	
}
