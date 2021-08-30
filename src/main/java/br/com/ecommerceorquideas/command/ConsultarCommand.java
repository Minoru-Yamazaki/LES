package br.com.ecommerceorquideas.command;

import br.com.ecommerceorquideas.domain.EntidadeDominio;

public class ConsultarCommand extends AbstractCommand{

	@Override
	public Object execute(EntidadeDominio entidade) {
		
		return fachada.consultar(entidade);
	}

}
