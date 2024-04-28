package com.easySchedule.backend.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	AUTENTICACAO_INVALIDA("/autenticacao-invalida", "Autenticação Inválida");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title){
		this.uri = "https://easySchedule.net" + path;
		this.title = title;
	}
}
