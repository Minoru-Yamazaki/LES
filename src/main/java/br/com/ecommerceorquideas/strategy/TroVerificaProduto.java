package br.com.ecommerceorquideas.strategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.dao.IDAO;
import br.com.ecommerceorquideas.dao.TrocaDAO;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Troca;

public class TroVerificaProduto implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Troca troca = (Troca) entidade;
		IDAO dao = new TrocaDAO();
		HashMap<String, String> map = new HashMap<>();
		
		map.put("pro_id", troca.getProId().toString());
		map.put("com_id", troca.getComId().toString());
		
		List<EntidadeDominio> objetos = (List<EntidadeDominio>) dao.consultar(map);
		
		for(EntidadeDominio objeto : objetos) {
			if(objeto.getId() != null)
				return Arrays.asList("Item em troca");
		}
		
		
		return null;
	}
}
