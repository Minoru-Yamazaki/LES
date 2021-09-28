package br.com.ecommerceorquideas.strategy.pedido;

import java.util.Date;
import java.util.List;

import br.com.ecommerceorquideas.domain.EntidadeDominio;
import br.com.ecommerceorquideas.domain.Compra;
import br.com.ecommerceorquideas.strategy.IStrategy;

public class InseriData implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Compra pedido = (Compra) entidade;
		
		if(pedido.getData() == null) {
			pedido.setData(new Date());
		}
		
		return null;
	}

}
