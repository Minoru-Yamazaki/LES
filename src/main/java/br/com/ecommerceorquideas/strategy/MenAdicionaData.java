package br.com.ecommerceorquideas.strategy;

import java.util.Date;
import java.util.List;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Mensagem;

public class MenAdicionaData implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Mensagem mensagem = (Mensagem) entidade;
		
		mensagem.setData(new Date());
		
		return null;
	}

}
