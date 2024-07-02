package com.easySchedule.backend.domain.exception.emuso;

import com.easySchedule.backend.domain.exception.notfound.EntidadeNaoEncontradaException;

public class EscolaEmUsoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public EscolaEmUsoException(String msg) {
		super(msg);
	}
	
	public EscolaEmUsoException(Long id) {
		this(String.format("A escola de código %d não pode ser deletada, pois está em uso por outra entidade.", id));
	}
	
}
