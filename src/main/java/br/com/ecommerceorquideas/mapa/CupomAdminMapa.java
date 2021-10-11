package br.com.ecommerceorquideas.mapa;

import java.util.HashMap;

import br.com.ecommerceorquideas.model.CupomAdmin;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class CupomAdminMapa implements IMapa{

	@Override
	public HashMap<String, String> mapeia(EntidadeDominio entidade) {
		CupomAdmin cupom = (CupomAdmin) entidade;
		HashMap<String, String> map = new HashMap<>();
		
		if(cupom.getId() != null)
			map.put("id", cupom.getId().toString());		
		if(cupom.getNome() != null)
			map.put("nome", cupom.getNome());
		if(cupom.getCodigo() != null)
			map.put("codigo", cupom.getCodigo());
		if(cupom.getDescricao() != null)
			map.put("descricao", cupom.getDescricao());
		if(cupom.getTipoCupom() != null)
			map.put("tipo_cupom", cupom.getTipoCupom());
		if(cupom.getValor() != null)
			map.put("valor", cupom.getValor().toString());
		if(cupom.getValidade() != null)
			map.put("validade", cupom.getValidade().toString());
		if(cupom.getAdmId() != null)
			map.put("adm_id", cupom.getAdmId().toString());
		
		return map;
	}
}
