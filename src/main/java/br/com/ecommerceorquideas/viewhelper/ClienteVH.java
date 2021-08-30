package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.domain.Cliente;
import br.com.ecommerceorquideas.domain.EntidadeDominio;

public class ClienteVH implements IViewHelper{

	private Cliente cliente = new Cliente();
	
	@Override
	public EntidadeDominio jsonToObject(String json) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			cliente = mapper.readValue(json, Cliente.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cliente;
	}
}
