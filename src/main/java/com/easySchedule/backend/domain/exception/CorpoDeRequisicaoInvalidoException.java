package com.easySchedule.backend.domain.exception;

import java.util.HashMap;
import java.util.Map;

public class CorpoDeRequisicaoInvalidoException extends NegocioException {
	private static final long serialVersionUID = 1L;
	
	public CorpoDeRequisicaoInvalidoException(String msg) {
		super(tratarMensagem(msg));
	}
	
	private static String tratarMensagem(String msgBase) {
		Map<String, String> atributoEntidade = getEntidadeParametro(msgBase);
		
		return String.format("O corpo da sua requisição está inválido. O atributo '%s' da entidade '%s' não pode ser null.",
				atributoEntidade.get("atributo"),
				atributoEntidade.get("entidade")).toString();
	}
	
	private static Map<String, String> getEntidadeParametro(String msgBase) {
		Map<String, String> atributoEntidade = new HashMap<>();
		msgBase = msgBase.split(":")[1].trim();
		String[] dados = msgBase.split("\\.");
		
		atributoEntidade.put("atributo", dados[dados.length - 1]);
		atributoEntidade.put("entidade", dados[dados.length - 2]);
		
		return atributoEntidade;
	}
	
}
