package br.com.ecommerceorquideas.strategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.dao.OrquideaDAO;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Orquidea;

public class OrqVerificaStatus implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Orquidea orquidea = (Orquidea) entidade;
		
		HashMap<String, String> map = new HashMap<>();
		map.put("id", orquidea.getId().toString());
		
		OrquideaDAO dao = new OrquideaDAO();
		List<Orquidea> orquideas = (List<Orquidea>) dao.consultar(map);
		
		Orquidea dbOrquidea = orquideas.get(0);
		
		if(dbOrquidea.getAtivo() != orquidea.getAtivo()) {
			System.out.println("Entrou");
			//verifica se campo da justificativa foi preenchido
			if(orquidea.getAtivo() == 1) {
				orquidea.setJustificativaInativacao("");
				if(orquidea.getJustificativaAtivacao().isEmpty()) {
					return Arrays.asList("É necessário uma justificativa para a ativar orquídea");
				}
			}
			
			if(orquidea.getAtivo() == 0) {
				orquidea.setJustificativaAtivacao("");
				if(orquidea.getJustificativaInativacao().isEmpty()) {
					return Arrays.asList("É necessário uma justificativa para a inativar orquídea");
				}
			}
		}
		
		return null;
	}

}
