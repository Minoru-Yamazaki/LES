package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Produto;

public class ProdutoVH implements IViewHelper{

	private Produto produto = new Produto();
	
	@Override
	public EntidadeDominio jsonToEntidade(String json) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			produto = mapper.readValue(json, Produto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return produto;
	}

}
