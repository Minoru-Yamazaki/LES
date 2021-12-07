package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Precificacao;

public class PrecificacaoVH implements IViewHelper{

	private Precificacao precificacao = new Precificacao();
	
	@Override
	public EntidadeDominio jsonToEntidade(String json) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			precificacao = mapper.readValue(json, Precificacao.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return precificacao;
	}

}
