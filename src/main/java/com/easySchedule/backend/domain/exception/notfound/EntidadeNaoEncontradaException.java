package com.easySchedule.backend.domain.exception.notfound;

import com.easySchedule.backend.domain.exception.NegocioException;

public abstract class EntidadeNaoEncontradaException extends NegocioException {
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String msg) {
		super(msg);
	}

}