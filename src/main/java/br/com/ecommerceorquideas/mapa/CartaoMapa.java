package br.com.ecommerceorquideas.mapa;

import java.util.HashMap;

import br.com.ecommerceorquideas.model.Cartao;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class CartaoMapa implements IMapa{

	@Override
	public HashMap<String, String> mapeia(EntidadeDominio entidade) {
		Cartao cartao = (Cartao) entidade;
		HashMap<String, String> map = new HashMap<>();
		
		if(cartao.getId() != null)
			map.put("id", cartao.getId().toString());		
		if(cartao.getNumero() != null)
			map.put("numero", cartao.getNumero());
		if(cartao.getNomeImpresso() != null)
			map.put("nome_impresso", cartao.getNomeImpresso());
		if(cartao.getCodigoSeguranca() != null)
			map.put("cvv", cartao.getCodigoSeguranca());
		if(cartao.getIdCliente() != null)
			map.put("cli_id", cartao.getIdCliente().toString());
		if(cartao.getBandeira() != null)
			map.put("bandeira", cartao.getBandeira());
		
		return map;
	}



}
