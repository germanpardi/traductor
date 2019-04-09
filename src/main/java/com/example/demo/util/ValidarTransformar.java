package com.example.demo.util;

import java.util.regex.Pattern;

import com.example.demo.dto.DiccionarioDTO;

import java.util.regex.Matcher;

public class ValidarTransformar {
	private String mensaje; 
	private int errores;
	private int lineas;
	
	
	public ValidarTransformar() {
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

	
	public String validarFormato(String texto) {
		// separamos campos y validacmos cada uno
		System.out.println("UTIL - ValidarTransformar: validarFormato");

		
		
			String datos_validados = "";
			mensaje="";
			 
					
			System.out.println("texto = " + texto);
			if (texto!=null) {
				String[] separarLineas = texto.split("\r\n"); // fin de carro y salto de linea; tenemos una CELDA por cada
														// linea.
				System.out.println("examinaremos " + separarLineas.length + " lineas");
			
				for (int linea = 0; linea < separarLineas.length; linea++) {
					String[] campo = separarLineas[linea].split(";");
					System.out.println("Tenemos " + campo.length+ " campos para examinar");
					//	diccionarioDTO = new DiccionarioDTO(0, );
				
					if (campo.length < 5 & campo.length>0) {
						if  (campo.length!=1) {  
								
							mensaje= " >>ERROR: fila no tiene todos los campos";
							errores++;
							System.out.println("aumento error-1");
							datos_validados = datos_validados + separarLineas[linea]+mensaje+"\n";
							lineas++;
						}
					}else {
						datos_validados = datos_validados + validarFilaDiccionario(campo[0], campo[1], campo[2], campo[3], campo[4])+ ";"+mensaje+"\n";
						mensaje="";
						lineas++;
					}
				}
			}else {
				datos_validados= " >>ERROR: fichero sin datos;\n";
				errores++;
				System.out.println("aumento error-2");
			}		 
		
		
		
		
		return datos_validados;

	}

	public String validarFilaDiccionario(String espanol,String ingles,String tipo,String categoria,String num_uso) {
		System.out.println("UTIL - ValidarTransformar: validarFilaDiccionario");
		// Validamos cada campo

		String datos_validados = "";

			//this.diccionarioDTO.setId(0);
			espanol=quitarespaciosdobles(espanol);
			ingles=quitarespaciosdobles(ingles);
			categoria=validarcategoría(categoria);
			tipo=validartipo(tipo);
			int num_uso_int=validarnumuso(num_uso);
		

		datos_validados = espanol+ ";" + ingles + ";"
				+ tipo + ";" + categoria + ";" + num_uso_int;
				

		return datos_validados;

	}

	public String quitarespaciosdobles(String sentenciavalidadr) {
		System.out.println("UTIL - ValidarTransformar: quitarespaciosdobles");
		String datos_validados = sentenciavalidadr;		
		int escritos=0;
		boolean notratarla=false;
		
		sentenciavalidadr=sentenciavalidadr.toLowerCase().replaceAll("á","a");
		sentenciavalidadr=sentenciavalidadr.toLowerCase().replaceAll("é","e");
		sentenciavalidadr=sentenciavalidadr.toLowerCase().replaceAll("í","i");
		sentenciavalidadr=sentenciavalidadr.toLowerCase().replaceAll("ó","o");
		sentenciavalidadr=sentenciavalidadr.toLowerCase().replaceAll("ú","u");
		sentenciavalidadr=sentenciavalidadr.toLowerCase().replaceAll("ãº","u");
		sentenciavalidadr=sentenciavalidadr.toLowerCase().replaceAll("ã©","e");
		sentenciavalidadr=sentenciavalidadr.toLowerCase().replaceAll("ã¡","a");
		sentenciavalidadr=sentenciavalidadr.toLowerCase().replaceAll("�","o");
		
		
		if (sentenciavalidadr.equals("")) {
			mensaje= mensaje + " >>ERROR campo principal debe ir informado";
			errores++;
			System.out.println("aumento error-3");
			
		}else {
			datos_validados = "";
			String[] separarpalabras = sentenciavalidadr.split(" "); // analizamos los espacios para dejar solo uno

			for (int l = 0; l < separarpalabras.length; l++) {
				System.out.println("palabra: |" +separarpalabras[l] +"|" );
				if (separarpalabras[l].equals("")|separarpalabras[l].equals(" ")|separarpalabras[l]==null) {//no tratarla
					notratarla=true;
				}else {	
					
					if (escritos==0) {//la primera
						datos_validados=separarpalabras[l];
						escritos++;
					}else {
						datos_validados = datos_validados + " " + separarpalabras[l];
						escritos++;
					}
				}
				
				
			}
		}

		return datos_validados;
	}

	public String validarcategoría(String categoriavalidar) {
		System.out.println("UTIL - ValidarTransformar: validarcategoría");
		// lo suyo es añadir una tabla de categorias para validar los valores posibles.

		
		boolean valido = false;

		if (categoriavalidar.equals("") | categoriavalidar.equals("adj") | categoriavalidar.equals("nom")
				| categoriavalidar.equals("ver") | categoriavalidar.equals("apo")) {
			valido = true;
		}

		if (!valido) {
			mensaje = mensaje + " >>ERROR: categoria no catalogada: " + categoriavalidar ;
			errores++;
			System.out.println("aumento error-4");
		}
		return categoriavalidar;
	}

	public String validartipo(String tipovalidar) {
		System.out.println("UTIL - ValidarTransformar: validartipo");
		// lo suyo es añadir una tabla de categorias para validar los valores posibles.

		
		if (tipovalidar.equals("f") | tipovalidar.equals("F")) {
			return "f"; 
		}else if (tipovalidar.equals("p") | tipovalidar.equals("P")) {
			return "p";
		}else {
			mensaje = mensaje + " >>ERROR: tipo no catalogado: " + tipovalidar;
			errores++;
			System.out.println("aumento error-5");
			return tipovalidar;
		}

		
	}

	public int validarnumuso(String numusovalidar) {
		System.out.println("UTIL - ValidarTransformar: validarnumuso");
		// lo suyo es añadir una tabla de categorias para validar los valores posibles.

		int numrovalidado;
		
		if (numusovalidar.contentEquals("")) {
			numrovalidado=0;
		}
				
		try {
            Integer.parseInt(numusovalidar);
            if (Integer.parseInt(numusovalidar) >= 0) {
				numrovalidado=Integer.parseInt(numusovalidar);
			}else {
				numrovalidado=0;
			}
        } catch (NumberFormatException excepcion) {
        	numrovalidado=0;
        }
		
					
		return numrovalidado;

	}
	public String cargamasiva(String texto) {
		// TODO Auto-generated method stub
		
		System.out.println("UTIL - ValidarTransformar: cargamasiva");
		
		String salidaacumulada="";
			
		String datos_validados= validarFormato(texto);
		
		String[] lineacargar=texto.split("\r\n");
		
		for (int linea=0; linea < lineacargar.length; linea++) {
			String[] detectarLineaconError = texto.split(">>ERROR");
			if (detectarLineaconError.length>0) {
				//linea con error que no podemos cargar
				salidaacumulada=salidaacumulada+lineacargar[linea]+" >>-- NO CARGADA";
			}else {
				//no tiene error. vamos a cargarla
				String[] campo = lineacargar[linea].split(";");
				DiccionarioDTO diccionarioDTO=new DiccionarioDTO(0,campo[0],campo[1],campo[2],campo[3],Integer.parseInt(campo[4]));
				
				
				//>>>>>MILA servicio buscar si existe 
				//si exite
					salidaacumulada=salidaacumulada+lineacargar[linea]+" >>-- REGISTRO YA EXISTE. NO CARGADO.";
				//>>>>>MILA servicio alta 
					salidaacumulada=salidaacumulada+lineacargar[linea]+" >>-- ALTA correcta.";
					
				
			}
			
		}
		
		
		return salidaacumulada;

	}
	public String validarFormatoAportacion(String aportartexto) {
		//validamos las aportaciones 
		System.out.println("UTIL - ValidarTransformar: validarFormatoAportacion");
		

		String datos_validados = "";
		this.mensaje="";
		 
		this.errores=0;
				
		System.out.println("aportartexto = " + aportartexto);
		if (aportartexto!=null) {
			String[] separarLineas = aportartexto.split("\r\n"); // fin de carro y salto de linea; tenemos una CELDA por cada
													// linea.
			System.out.println("examinaremos " + separarLineas.length + " lineas");
		
			for (int linea = 0; linea < separarLineas.length; linea++) {
				
				datos_validados = datos_validados + quitarespaciosdobles(separarLineas[linea]) + ".";
			}
			
		}else {
			mensaje= " Introduzca datos, por favor.";
			errores++;
			System.out.println("aumento error");
		}		 
	
		return datos_validados;
	
	}
	
	public String averiguarTipo(String aportartexto) {
		System.out.println("UTIL - ValidarTransformar: averiguarTipo");
		
		String[] palabras = aportartexto.split(" ");
		String devolver="";
		
		if (palabras.length==1) {
			devolver= "p";
		}else {
			devolver= "f";
		}
		
		System.out.println("palabras.length: " + palabras.length);
		System.out.println("devolver: " + devolver);
		return devolver;
			
	}

}