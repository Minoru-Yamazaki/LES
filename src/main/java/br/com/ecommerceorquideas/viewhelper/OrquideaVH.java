package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.domain.EntidadeDominio;
import br.com.ecommerceorquideas.domain.Orquidea;

public class OrquideaVH implements IViewHelper{

	private Orquidea orquidea = new Orquidea();
	
	@Override
	public EntidadeDominio jsonToEntidade(String json) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			orquidea = mapper.readValue(json, Orquidea.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return orquidea;
	}

}
