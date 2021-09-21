package br.com.ecommerceorquideas.strategy.cliente;

import java.util.Arrays;
import java.util.List;

import br.com.ecommerceorquideas.domain.Cliente;
import br.com.ecommerceorquideas.domain.EntidadeDominio;
import br.com.ecommerceorquideas.strategy.IStrategy;

public class VerificaPreencSexo implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		if(cliente.getSexo() == null) {
			return Arrays.asList("Necessário selecionar uma das opções 'sexo'");
		}
		return null;
	}

}
