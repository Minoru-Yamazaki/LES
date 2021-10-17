package br.com.ecommerceorquideas.dao;

import java.sql.SQLException;
import java.util.HashMap;

import br.com.ecommerceorquideas.model.EntidadeDominio;

public interface IDAO {

	public Object salvar(EntidadeDominio entidade) throws SQLException;
	
	public Object alterar(EntidadeDominio entidade);
	
	public Object excluir(Integer id);

	public Object consultar(HashMap<String, String> map);
	
}
