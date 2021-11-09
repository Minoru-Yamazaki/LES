package br.com.ecommerceorquideas.mapa;

import java.util.HashMap;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Mensagem;

public class MensagemMapa implements IMapa{

	@Override
	public HashMap<String, String> mapeia(EntidadeDominio entidade) {
		Mensagem mensagem = (Mensagem) entidade;
		HashMap<String, String> map = new HashMap<>();
		
		if(mensagem.getId() != null)
			map.put("id", mensagem.getId().toString());		
		if(mensagem.getMensagem() != null)
			map.put("mensagem", mensagem.getMensagem());
		if(mensagem.getData() != null)
			map.put("data", mensagem.getData().toString());
		if(mensagem.getCliId() != null)
			map.put("cli_id", mensagem.getCliId().toString());
		
		return map;
	}

}
