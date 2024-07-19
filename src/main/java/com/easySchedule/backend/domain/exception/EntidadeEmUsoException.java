package com.easySchedule.backend.domain.exception;

public class EntidadeEmUsoException extends NegocioException {
	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String msg) {
		super(msg);
	}
	
	public EntidadeEmUsoException(Object obj, Long id) {
		super(getMessage(obj, id));
	}

	private static String getMessage(Object obj, Long id) {
		return String.format("A entidade %s de id %d não pode ser deletada, pois está sendo utilizada por outra entidade.", getClassName(obj), id);
	}
}