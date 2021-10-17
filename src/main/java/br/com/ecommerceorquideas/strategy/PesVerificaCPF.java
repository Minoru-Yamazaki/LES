package br.com.ecommerceorquideas.strategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.dao.AdministradorDAO;
import br.com.ecommerceorquideas.dao.ClienteDAO;
import br.com.ecommerceorquideas.dao.IDAO;
import br.com.ecommerceorquideas.model.Administrador;
import br.com.ecommerceorquideas.model.Cliente;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class PesVerificaCPF implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {

		String classeAdministrador = Administrador.class.getName();
		String cpf;
		Integer id;
		IDAO dao;
		
		if(entidade.getClass().getName().equals(classeAdministrador)) {
			Administrador administrador = (Administrador) entidade;
			cpf = administrador.getCpf();
			id = administrador.getId();
			dao = new AdministradorDAO();
		}else {
			Cliente cliente = (Cliente) entidade;
			cpf = cliente.getCpf();
			id = cliente.getId();
			dao = new ClienteDAO();
		}		
		
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
