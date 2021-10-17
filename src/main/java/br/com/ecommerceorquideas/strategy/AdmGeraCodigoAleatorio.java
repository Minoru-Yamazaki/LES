package br.com.ecommerceorquideas.strategy;

import java.util.List;

import br.com.ecommerceorquideas.model.CupomAdmin;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.util.GeraCodigo;

public class AdmGeraCodigoAleatorio implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		CupomAdmin cupom = (CupomAdmin) entidade;
		
		cupom.setCodigo(GeraCodigo.gerar());
		
		return null;
	}

}
