package br.com.ecommerceorquideas.strategy;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.com.ecommerceorquideas.model.CupomAdmin;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class AdmVerificaData implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		CupomAdmin cupom = (CupomAdmin) entidade;
		Date data = new Date();
		
		if(cupom.getValidade().before(data)) {
			return Arrays.asList("A data informada é inválida");
		}
		return null;
	}

}
