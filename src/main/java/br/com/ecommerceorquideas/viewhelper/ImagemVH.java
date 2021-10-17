package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Imagem;

public class ImagemVH implements IViewHelper {

	private Imagem imagem = new Imagem();

	@Override
	public EntidadeDominio jsonToEntidade(String json) {
		
		ObjectMapper mapper = new ObjectMapper();

		try {
			imagem = mapper.readValue(json, Imagem.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imagem;
	}

}
