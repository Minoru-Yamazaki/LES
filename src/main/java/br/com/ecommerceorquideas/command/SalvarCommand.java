package br.com.ecommerceorquideas.command;

import br.com.ecommerceorquideas.model.EntidadeDominio;

public class SalvarCommand extends AbstractCommand{

	@Override
	public Object execute(EntidadeDominio entidade) {

		return fachada.salvar(entidade);
	}

}
