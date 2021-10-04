package br.com.ecommerceorquideas.strategy;

import java.util.Date;
import java.util.List;

import br.com.ecommerceorquideas.domain.EntidadeDominio;
import br.com.ecommerceorquideas.domain.Compra;

public class PedInseriData implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Compra pedido = (Compra) entidade;
		
		if(pedido.getData() == null) {
			pedido.setData(new Date());
		}
		
		return null;
	}

}
