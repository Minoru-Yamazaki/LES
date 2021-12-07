package br.com.ecommerceorquideas.strategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.dao.IDAO;
import br.com.ecommerceorquideas.dao.OrquideaDAO;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Orquidea;

public class OrqVerificaCadastro implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Orquidea orquidea = (Orquidea) entidade;
		
		HashMap<String, String> map = new HashMap<>();
		map.put("nome", orquidea.getNome());
		
		IDAO dao = new OrquideaDAO();
		List<Orquidea> orquideas = (List<Orquidea>) dao.consultar(map);
		
		System.out.println(orquideas.size());
		
		if(orquideas.size() > 0) {
			return Arrays.asList("Orquídea já cadastrada");
		}
		
		return null;
	}

}
