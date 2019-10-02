package br.gov.spusc.escriba.exception;

import org.openqa.selenium.WebElement;

public class ElementoNaoClicavelException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private WebElement elemento;

	public ElementoNaoClicavelException(WebElement elemento) {
		this.elemento = elemento;
	}
	
	public String getMessage() {
		String message = super.getMessage();
		if(elemento != null) {
			message += elemento.toString();
		}
		return message;
	}

}
