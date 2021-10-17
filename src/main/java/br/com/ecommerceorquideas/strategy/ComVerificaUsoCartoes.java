package br.com.ecommerceorquideas.strategy;

import java.util.Arrays;
import java.util.List;

import br.com.ecommerceorquideas.model.CartoesCompra;
import br.com.ecommerceorquideas.model.Compra;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class ComVerificaUsoCartoes implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Compra compra = (Compra) entidade;
		
		List<CartoesCompra> cartoes = compra.getCartoes();
		
		if(cartoes.size() > 1 && !valorEhCorreto(cartoes)) {
			return Arrays.asList("Valor mínimo de R$ 10 no cartão de crédito");
		}
		
		return null;
	}
	
	private boolean valorEhCorreto(List<CartoesCompra> cartoes) {
		
		for(CartoesCompra cartao : cartoes) {
			if(cartao.getTotal() < 10) {
				return false;
			}
		}
		return true;
	}

}
