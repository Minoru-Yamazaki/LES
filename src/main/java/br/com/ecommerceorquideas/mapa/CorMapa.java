package br.com.ecommerceorquideas.mapa;

import java.util.HashMap;

import br.com.ecommerceorquideas.domain.Cor;
import br.com.ecommerceorquideas.domain.EntidadeDominio;

public class CorMapa implements IMapa{

	@Override
	public HashMap<String, String> mapeia(EntidadeDominio entidade) {
		Cor cor = (Cor) entidade;
		HashMap<String, String> map = new HashMap<>();
		
		if(cor.getId() != null)
			map.put("id", cor.getId().toString());		
		if(cor.getCor() != null)
			map.put("cor", cor.getCor());
		if(cor.getAtivo() != null)
			map.put("ativo", cor.getAtivo().toString());
		
		return map;
	}

}
