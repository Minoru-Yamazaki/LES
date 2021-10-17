package br.com.ecommerceorquideas.command;

import br.com.ecommerceorquideas.model.EntidadeDominio;

public interface ICommand {

	Object execute(EntidadeDominio entidade);
}
