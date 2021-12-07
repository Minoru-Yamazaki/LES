package br.com.ecommerceorquideas.strategy;

import java.util.ArrayList;
import java.util.List;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Orquidea;

public class OrqVerificaCamposNulos implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Orquidea orquidea = (Orquidea) entidade;
		List<String> mensagens = new ArrayList<String>();
		
		if(orquidea.getValorCusto() == null) {
			mensagens.add("Campo valor de custo não pode ser nulo!");
		}
		if(orquidea.getNome() == null) {
			mensagens.add("Campo nome não pode ser nulo!");
		}
		if(orquidea.getNome() == null) {
			mensagens.add("Campo nome não pode ser nulo!");
		}
		if(orquidea.getQuantidade() == null) {
			mensagens.add("Campo quantidade não pode ser nulo!");
		}
		if(orquidea.getCor() == null) {
			mensagens.add("Campo cor não pode ser nulo!");
		}
		if(orquidea.getGenero() == null) {
			mensagens.add("Campo gênero não pode ser nulo!");
		}
		if(orquidea.getTipo() == null) {
			mensagens.add("Campo tipo não pode ser nulo!");
		}
		
		
		if(mensagens.size() != 0) {
			return mensagens;
		}
		return null;
	}

}
