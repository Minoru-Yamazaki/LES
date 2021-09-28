package br.com.ecommerceorquideas.mapa;

import java.util.HashMap;

import br.com.ecommerceorquideas.domain.Cupom;
import br.com.ecommerceorquideas.domain.EntidadeDominio;

public class CupomMapa implements IMapa{

	@Override
	public HashMap<String, String> mapeia(EntidadeDominio entidade) {
		Cupom cupom = (Cupom) entidade;
		HashMap<String, String> map = new HashMap<>();
		
		if(cupom.getId() != null)
			map.put("id", cupom.getId().toString());		
		if(cupom.getNome() != null)
			map.put("nome", cupom.getNome());
		if(cupom.getDescricao() != null)
			map.put("descricao", cupom.getDescricao());
		if(cupom.getTipoCupom() != null)
			map.put("tipo_cupom", cupom.getTipoCupom());
		if(cupom.getValor() != null)
			map.put("valor", cupom.getValor().toString());
		if(cupom.getValidade() != null)
			map.put("validade", cupom.getValidade().toString());
		if(cupom.getCliId() != null)
			map.put("cli_id", cupom.getCliId().toString());
		
		return map;
	}

}
