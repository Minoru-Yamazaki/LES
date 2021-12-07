package br.com.ecommerceorquideas.strategy;

import java.util.Arrays;
import java.util.List;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Orquidea;

public class OrqVerificaQtde implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Orquidea orquidea = (Orquidea) entidade;
		
		if(orquidea.getAtivo() == 1 && orquidea.getQuantidade() <= 0) {
			return Arrays.asList("Quantidade deve ser no mínimo 1");
		}
		return null;
	}

}
