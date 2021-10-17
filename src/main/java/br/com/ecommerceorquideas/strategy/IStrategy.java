package br.com.ecommerceorquideas.strategy;

import java.util.List;

import br.com.ecommerceorquideas.model.EntidadeDominio;

public interface IStrategy {
	public List<String> processar(EntidadeDominio entidade);
}
