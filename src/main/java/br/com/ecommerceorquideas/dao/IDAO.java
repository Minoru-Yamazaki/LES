package br.com.ecommerceorquideas.dao;

import java.util.HashMap;

import br.com.ecommerceorquideas.domain.EntidadeDominio;

public interface IDAO {

	public Object salvar(EntidadeDominio entidade);
	
	public Object alterar(EntidadeDominio entidade);
	
	public Object excluir(Integer id);

	public Object consultar(HashMap<String, String> map);
	
}
