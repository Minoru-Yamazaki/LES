package br.com.ecommerceorquideas.strategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.dao.IDAO;
import br.com.ecommerceorquideas.dao.LoginAdminDAO;
import br.com.ecommerceorquideas.dao.LoginDAO;
import br.com.ecommerceorquideas.model.Administrador;
import br.com.ecommerceorquideas.model.Cliente;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class PesVerificaEmail implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		
		String classeAdministrador = Administrador.class.getName();
		String email;
		IDAO dao;
		
		if(entidade.getClass().getName().equals(classeAdministrador)) {
			Administrador administrador = (Administrador) entidade;
			email = administrador.getLogin().getEmail();
			dao = new LoginAdminDAO();
		}else {
			Cliente cliente = (Cliente) entidade;
			email = cliente.getLogin().getEmail();
			dao = new LoginDAO();
		}
				
		HashMap<String, String> map = new HashMap<>();
		map.put("email", email);
		
		List<Object> retorno = (List<Object>) dao.consultar(map);
		
		if(retorno.size() != 0) {
			return Arrays.asList("Email já cadastrado");
		}
		
		return null;
	}

}
