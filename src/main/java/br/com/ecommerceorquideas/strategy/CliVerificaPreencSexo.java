package br.com.ecommerceorquideas.strategy;

import java.util.Arrays;
import java.util.List;

import br.com.ecommerceorquideas.domain.Cliente;
import br.com.ecommerceorquideas.domain.EntidadeDominio;

public class CliVerificaPreencSexo implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		if(cliente.getSexo() == null) {
			return Arrays.asList("Necessário selecionar uma das opções 'sexo'");
		}
		return null;
	}

}
