package br.com.ecommerceorquideas.strategy;

import java.util.Arrays;
import java.util.List;

import br.com.ecommerceorquideas.model.Cliente;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class CliVerificaData implements IStrategy {

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;

		if (cliente.getNascimento() == null) {
			return Arrays.asList("Necessário informar a data de nascimento");
		}
		return null;
	}
}
