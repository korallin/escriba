package br.gov.economia.seddm.spu.escriba.exception;

public class ElementoNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ElementoNaoEncontradoException(String message) {
		super("ElementoNaoEncontradoException: " + message);
		
	}

}
