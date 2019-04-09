package com.example.demo.util;

public class ValidadorAportar {
	
	public String quitarEspacios(String texto) {
		String [] cadena=texto.split(" ");
		String salida="";
		for(int i=0;i<cadena.length;i++) {
			if(cadena[i].contains(" ")) {
			cadena[i]=cadena[i].replace(" ", "");
			i--;
			}else {
				salida = salida + cadena[i]	+ " ";
			}
		}
	
	return salida;
	}
}
