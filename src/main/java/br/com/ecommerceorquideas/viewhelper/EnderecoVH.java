package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.domain.Endereco;
import br.com.ecommerceorquideas.domain.EntidadeDominio;

public class EnderecoVH implements IViewHelper{

	private Endereco endereco = new Endereco();
	
	@Override
	public EntidadeDominio jsonToObject(String json) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			endereco = mapper.readValue(json, Endereco.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return endereco;
	}
}
