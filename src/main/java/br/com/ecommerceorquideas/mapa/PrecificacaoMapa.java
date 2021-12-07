package br.com.ecommerceorquideas.mapa;

import java.util.HashMap;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Precificacao;

public class PrecificacaoMapa implements IMapa{

	@Override
	public HashMap<String, String> mapeia(EntidadeDominio entidade) {
		Precificacao precificacao = (Precificacao) entidade;
		HashMap<String, String> map = new HashMap<>();
		
		if(precificacao.getId() != null)
			map.put("id", precificacao.getId().toString());		
		if(precificacao.getPorcentagem() != null)
			map.put("porcentagem", precificacao.getPorcentagem().toString());
		
		return map;
	}

}
