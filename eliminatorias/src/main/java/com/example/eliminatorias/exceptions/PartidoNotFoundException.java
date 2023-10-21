package com.example.eliminatorias.exceptions;

public class PartidoNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PartidoNotFoundException(String message) {
        super(message);
    }
}
