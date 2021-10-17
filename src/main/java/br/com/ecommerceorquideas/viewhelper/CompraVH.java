package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.model.Compra;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class CompraVH implements IViewHelper{

	private Compra pedido = new Compra();
	
	@Override
	public EntidadeDominio jsonToEntidade(String json) {

		ObjectMapper mapper = new ObjectMapper();
		
		try {
			pedido = mapper.readValue(json, Compra.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pedido;
	}

}
