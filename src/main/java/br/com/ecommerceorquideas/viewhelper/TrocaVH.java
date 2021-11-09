package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Troca;

public class TrocaVH implements IViewHelper{
	
	private Troca troca = new Troca();

	@Override
	public EntidadeDominio jsonToEntidade(String json) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			troca = mapper.readValue(json, Troca.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return troca;
	}

}
