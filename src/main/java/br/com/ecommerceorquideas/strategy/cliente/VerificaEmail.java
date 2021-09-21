package br.com.ecommerceorquideas.strategy.cliente;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.dao.IDAO;
import br.com.ecommerceorquideas.dao.LoginDAO;
import br.com.ecommerceorquideas.domain.Cliente;
import br.com.ecommerceorquideas.domain.EntidadeDominio;
import br.com.ecommerceorquideas.strategy.IStrategy;

public class VerificaEmail implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		String email = cliente.getLogin().getEmail();
		
		IDAO dao = new LoginDAO();
		HashMap<String, String> map = new HashMap<>();
		
		map.put("email", email);
		
		List<Object> retorno = (List<Object>) dao.consultar(map);
		
		if(retorno.size() != 0) {
			return Arrays.asList("Email já cadastrado");
		}
		
		return null;
	}

}
