package br.com.ecommerceorquideas.strategy;

import java.util.List;

import br.com.ecommerceorquideas.domain.EntidadeDominio;

public interface IStrategy {
	public List<String> processar(EntidadeDominio entidade);
}
