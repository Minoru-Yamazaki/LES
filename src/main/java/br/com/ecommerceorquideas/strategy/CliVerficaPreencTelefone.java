package br.com.ecommerceorquideas.strategy;

import java.util.Arrays;
import java.util.List;

import br.com.ecommerceorquideas.model.Cliente;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class CliVerficaPreencTelefone implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
Cliente cliente = (Cliente) entidade;
		
		if(cliente.getTipoTelefone() == null) {
			return Arrays.asList("Necessário selecionar uma das opções 'Tipo do telefone'");
		}
		return null;
	}

}
