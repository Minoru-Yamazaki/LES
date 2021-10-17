package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.model.Cliente;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class ClienteVH implements IViewHelper{

	private Cliente cliente = new Cliente();
	
	@Override
	public EntidadeDominio jsonToEntidade(String json) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			cliente = mapper.readValue(json, Cliente.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cliente;
	}
}
