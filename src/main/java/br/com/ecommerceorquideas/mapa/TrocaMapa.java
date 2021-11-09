package br.com.ecommerceorquideas.mapa;

import java.util.HashMap;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Troca;

public class TrocaMapa implements IMapa{

	@Override
	public HashMap<String, String> mapeia(EntidadeDominio entidade) {
		Troca troca = (Troca) entidade;
		HashMap<String, String> map = new HashMap<>();
		
		if(troca.getId() != null)
			map.put("id", troca.getId().toString());		
		if(troca.getProId()	 != null)
			map.put("pro_id", troca.getProId().toString());
		if(troca.getQuantidade() != null)
			map.put("quantidade", troca.getQuantidade().toString());
		if(troca.getQtdeMax() != null)
			map.put("qtde_max", troca.getQtdeMax().toString());
		if(troca.getPreco() != null)
			map.put("preco", troca.getPreco().toString());
		if(troca.getComId() != null)
			map.put("com_id", troca.getComId().toString());
		
		return map;
	}
}
