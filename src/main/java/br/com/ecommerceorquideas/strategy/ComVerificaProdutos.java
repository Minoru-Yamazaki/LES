package br.com.ecommerceorquideas.strategy;

import java.util.Arrays;
import java.util.List;

import br.com.ecommerceorquideas.model.Compra;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class ComVerificaProdutos implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Compra compra = (Compra) entidade;
		
		if(compra.getProdutos().size() == 0) {
			return Arrays.asList("Nenhum produto selecionado");
		}
		return null;
	}

}
