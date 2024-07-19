package com.easySchedule.backend.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioException {
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public EntidadeNaoEncontradaException(Object obj, Long id) {
		super(getMessage(obj, id));
	}

	private static String getMessage(Object obj, Long id) {
		return String.format("Não existe um cadastro de %s com id %d.", getClassName(obj), id);
	}
}