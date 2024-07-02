package com.easySchedule.backend.domain.exception.emuso;

import com.easySchedule.backend.domain.exception.NegocioException;

public abstract class EntidadeEmUsoException extends NegocioException {
	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String msg) {
		super(msg);
	}

}