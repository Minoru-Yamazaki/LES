package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.domain.Cartao;
import br.com.ecommerceorquideas.domain.EntidadeDominio;

public class CartaoVH implements IViewHelper {

	private Cartao cartao = new Cartao();

	@Override
	public EntidadeDominio jsonToEntidade(String json) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			cartao = mapper.readValue(json, Cartao.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cartao;
	}
}
