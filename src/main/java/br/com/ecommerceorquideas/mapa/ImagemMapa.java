package br.com.ecommerceorquideas.mapa;

import java.util.HashMap;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Imagem;

public class ImagemMapa implements IMapa{

	@Override
	public HashMap<String, String> mapeia(EntidadeDominio entidade) {
		Imagem imagem = (Imagem) entidade;
		HashMap<String, String> map = new HashMap<>();
		
		if(imagem.getId() != null)
			map.put("id", imagem.getId().toString());		
		if(imagem.getFoto() != null)
			map.put("foto", imagem.getFoto());
		if(imagem.getIdOrquidea() != null)
			map.put("orq_id", imagem.getIdOrquidea().toString());
		
		return map;
	}

}
