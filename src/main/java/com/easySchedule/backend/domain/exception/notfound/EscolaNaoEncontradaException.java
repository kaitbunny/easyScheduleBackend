package com.easySchedule.backend.domain.exception.notfound;

public class EscolaNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public EscolaNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public EscolaNaoEncontradaException(Long id) {
		this(String.format("Não existe um cadastro de escola com código %d.", id));
	}
	
}
