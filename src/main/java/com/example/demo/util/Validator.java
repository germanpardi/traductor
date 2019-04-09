package com.example.demo.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validator {

	public static boolean validarNIF(String nif) {

		boolean correcto = false;

		Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");

		Matcher matcher = pattern.matcher(nif);

		if (matcher.matches()) {

			String letra = matcher.group(2);

			String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

			int index = Integer.parseInt(matcher.group(1));

			index = index % 23;

			String reference = letras.substring(index, index + 1);

			if (reference.equalsIgnoreCase(letra)) {

				correcto = true;

			} else {

				correcto = false;

			}

		} else {

			correcto = false;

		}

		return correcto;

	}

	public static boolean validarMail(String email) {

		// Patr�n para validar el email
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		// El email a validar
		// String email = "info@programacionextrema.com";

		Matcher mather = pattern.matcher(email);

		if (mather.find() == true) {
			return true;
			// System.out.println("El email ingresado es v�lido.");
		} else {
			return false;
			// System.out.println("El email ingresado es inv�lido.");
		}
	}

	public static boolean isNumeric(String numero) {
		int resultado;

		try {
			resultado = Integer.parseInt(numero);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean is_Letter(char ch) {
		ch = Character.toUpperCase(ch);
		return (ch >= 'A' && ch <= 'Z');
	}

	public static boolean is_Numeric(char ch) {

		return (ch >= '0' && ch <= '9');
	}

	public static boolean validadorPassword(String password) {
		final int PASSWORD_LENGTH = 8;

		if (password.length() < PASSWORD_LENGTH)
			return false;

		int charCount = 0;
		int numCount = 0;
		for (int i = 0; i < password.length(); i++) {

			char ch = password.charAt(i);

			if (is_Numeric(ch))
				numCount++;
			else if (is_Letter(ch))
				charCount++;
			else
				return false;
		}

		return (charCount >= 2 && numCount >= 2);
	}

}
