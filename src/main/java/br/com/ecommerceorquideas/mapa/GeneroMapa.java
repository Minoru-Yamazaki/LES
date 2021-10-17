package br.com.ecommerceorquideas.mapa;

import java.util.HashMap;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Genero;

public class GeneroMapa implements IMapa{

	@Override
	public HashMap<String, String> mapeia(EntidadeDominio entidade) {
		Genero genero = (Genero) entidade;
		HashMap<String, String> map = new HashMap<>();
		
		if(genero.getId() != null)
			map.put("id", genero.getId().toString());		
		if(genero.getGenero() != null)
			map.put("genero", genero.getGenero());
		if(genero.getAtivo() != null)
			map.put("ativo", genero.getAtivo().toString());
		
		return map;
	}

}
