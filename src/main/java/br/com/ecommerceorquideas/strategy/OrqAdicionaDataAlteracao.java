package br.com.ecommerceorquideas.strategy;

import java.util.Date;
import java.util.List;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Orquidea;

public class OrqAdicionaDataAlteracao implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Orquidea orquidea = (Orquidea) entidade;
		
		orquidea.setDataHora(new Date());
		
		return null;
	}

}
