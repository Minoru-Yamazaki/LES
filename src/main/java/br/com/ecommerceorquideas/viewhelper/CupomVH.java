package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.model.Cupom;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class CupomVH implements IViewHelper{

	private Cupom cupom = new Cupom();
	@Override
	public EntidadeDominio jsonToEntidade(String json) {

		ObjectMapper mapper = new ObjectMapper();
		
		try {
			cupom = mapper.readValue(json, Cupom.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cupom;
	}

}
