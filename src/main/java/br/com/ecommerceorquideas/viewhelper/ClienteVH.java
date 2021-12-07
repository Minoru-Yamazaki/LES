package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.model.Cliente;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class ClienteVH implements IViewHelper{

	private ObjectMapper jsonDataMapper;
	private Cliente cliente = new Cliente();
	
	public ClienteVH(ObjectMapper jsonDataMapper) {
		this.jsonDataMapper = jsonDataMapper;
	}
	
	@Override
	public EntidadeDominio jsonToEntidade(String json) {		
		
		try {
			cliente = jsonDataMapper.readValue(json, Cliente.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cliente;
	}
}
