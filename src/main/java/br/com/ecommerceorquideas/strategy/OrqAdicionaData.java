package br.com.ecommerceorquideas.strategy;

import java.util.Date;
import java.util.List;

import br.com.ecommerceorquideas.model.Orquidea;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class OrqAdicionaData implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Orquidea orquidea = (Orquidea) entidade;
		
		if(orquidea.getDataHora() == null) {
			orquidea.setDataHora(new Date());
		}
		
		return null;
	}

}
