package br.com.ecommerceorquideas.strategy;

import br.com.ecommerceorquideas.domain.EntidadeDominio;

public interface IStrategy {
	public String processar(EntidadeDominio entidade);
}
