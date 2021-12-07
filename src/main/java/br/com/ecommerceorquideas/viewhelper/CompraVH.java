package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.model.Compra;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class CompraVH implements IViewHelper{

	private ObjectMapper jsonDataMapper; 
	private Compra compra = new Compra();

	public CompraVH(ObjectMapper jsonDataMapper) {
		this.jsonDataMapper = jsonDataMapper;
	}
	
	@Override
	public EntidadeDominio jsonToEntidade(String json) {
		
		try {
			compra = jsonDataMapper.readValue(json, Compra.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return compra;
	}

}
