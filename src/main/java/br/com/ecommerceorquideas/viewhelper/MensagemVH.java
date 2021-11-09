package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Mensagem;

public class MensagemVH implements IViewHelper{

	private Mensagem mensagem = new Mensagem();
	
	@Override
	public EntidadeDominio jsonToEntidade(String json) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			mensagem = mapper.readValue(json, Mensagem.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mensagem;
	}

}
