package br.com.ecommerceorquideas.facade;

import br.com.ecommerceorquideas.model.EntidadeDominio;

public interface IFacade {
	
	public Object salvar(EntidadeDominio entidade);
	public Object excluir(EntidadeDominio entidade);
	public Object alterar(EntidadeDominio entidade);
	public Object consultar(EntidadeDominio entidade);

}
