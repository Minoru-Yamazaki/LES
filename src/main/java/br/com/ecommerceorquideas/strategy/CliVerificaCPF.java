package br.com.ecommerceorquideas.strategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.dao.ClienteDAO;
import br.com.ecommerceorquideas.dao.IDAO;
import br.com.ecommerceorquideas.domain.Cliente;
import br.com.ecommerceorquideas.domain.EntidadeDominio;

public class CliVerificaCPF implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		String cpf = cliente.getCpf();
		Integer id = cliente.getId();
		
		IDAO dao = new ClienteDAO();
		HashMap<String, String> map = new HashMap<>();
		map.put("cpf", cpf);
		
		if(id != null) {
			map.put("id!", id.toString());
		}
		
		List<Object> retorno = (List<Object>) dao.consultar(map);
		
		if(retorno.size() != 0) {
			return Arrays.asList("CPF já cadastrado");
		}
		
		return null;
	}


}
