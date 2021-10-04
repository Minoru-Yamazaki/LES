package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.domain.EntidadeDominio;
import br.com.ecommerceorquideas.domain.Genero;

public class GeneroVH implements IViewHelper{

	private Genero genero = new Genero();
	
	@Override
	public EntidadeDominio jsonToEntidade(String json) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			genero = mapper.readValue(json, Genero.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return genero;
	}

}
