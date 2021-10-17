package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.model.Cor;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class CorVH implements IViewHelper {

	private Cor cor = new Cor();

	@Override
	public EntidadeDominio jsonToEntidade(String json) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			cor = mapper.readValue(json, Cor.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cor;
	}

}
