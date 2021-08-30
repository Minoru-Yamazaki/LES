package br.com.ecommerceorquideas.command;

import br.com.ecommerceorquideas.domain.EntidadeDominio;

public interface ICommand {

	Object execute(EntidadeDominio entidade);
}
