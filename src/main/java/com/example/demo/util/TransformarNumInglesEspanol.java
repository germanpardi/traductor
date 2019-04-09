package com.example.demo.util;

import java.util.regex.Pattern;

import com.example.demo.dto.DiccionarioDTO;

import java.util.regex.Matcher;

public class TransformarNumInglesEspanol {
	private String mensaje; 
	private int errores;
	private int lineas;
	
	
	public TransformarNumInglesEspanol() {
		this.errores=0;
		this.mensaje="";
		this.lineas=0;
		
	}
	public int getLineas() {
		return lineas;
	}
	public void setLineas(int lineas) {
		this.lineas = lineas;
	}
	
	public int getErrores() {
		return errores;
	}
	public void setErrores(int errores) {
		this.errores = errores;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	
	public String transformaranuestroformato(String texto) {
		// separamos campos y validacmos cada uno
		System.out.println("UTIL - ValidarTransformar: validarFormato");
		
			String datos_transformados = "";
			mensaje="";
			boolean correcto=false;
			texto=texto.toLowerCase().replaceAll("á","a");
			texto=texto.toLowerCase().replaceAll("é","e");
			texto=texto.toLowerCase().replaceAll("í","i");
			texto=texto.toLowerCase().replaceAll("ó","o");
			texto=texto.toLowerCase().replaceAll("ú","u");
			texto=texto.toLowerCase().replaceAll("ãº","u");
			texto=texto.toLowerCase().replaceAll("ã©","e");
			texto=texto.toLowerCase().replaceAll("ã¡","a");
			texto=texto.toLowerCase().replaceAll("�","o");
			
			
			
			
			
			System.out.println("texto = " + texto);
			if (texto!=null) {
				String[] separarLineas = texto.split("\r\n"); // fin de carro y salto de linea; tenemos una CELDA por cada
														// linea.
				System.out.println("examinaremos " + separarLineas.length + " lineas");
				
				
				for (int linea = 0; linea < separarLineas.length; linea++) {
					System.out.println("FRASE EXAMINAR CAMPOS: " + separarLineas[linea]);
					String[] campo = separarLineas[linea].split("\t");
					System.out.println("Tenemos " + campo.length+ " campos para examinar: " + "|"+campo[0]+ "|"+campo[1]+ "|"+campo[2]);
					//	diccionarioDTO = new DiccionarioDTO(0, );
				
					if (campo.length < 3 & campo.length>0) { //estan los campso exactos que queremos
						if  (campo.length==1) {  
							correcto=true; //es la ultima linea
						}else {	
							mensaje= " >>ERROR: fila no tiene todos los campos";
							errores++;
							System.out.println("aumento error-1");
							datos_transformados = datos_transformados + separarLineas[linea]+mensaje+"\n";
							lineas++;
						}
					}else {
						// FORMATO DE SALIDA: (espanol - ingles- tipo - categoria - num_uso)
						String[] duplicarlineas = campo[2].split("/");
						for (int linea2 = 0; linea2 < duplicarlineas.length; linea2++) {
							String concatenar=duplicarlineas[linea2]+ ";"+campo[1]+ ";p;;0;\n";
							datos_transformados = datos_transformados +concatenar ;
							System.out.println("Tranformado a: " +  concatenar);	
							
						}
						
						//datos_transformados = datos_transformados + campo[2]+ ";"+campo[1]+ ";p;;0;\n";
						//
						mensaje="";
						lineas++;
					}
				}
			}else {
				datos_transformados= " >>ERROR: fichero sin datos;\n";
				errores++;
				System.out.println("aumento error-2");
			}		 
		
		
		
		
		return datos_transformados;

	}


}