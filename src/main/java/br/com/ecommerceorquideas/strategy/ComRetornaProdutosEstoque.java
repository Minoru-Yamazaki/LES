package br.com.ecommerceorquideas.strategy;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.dao.OrquideaDAO;
import br.com.ecommerceorquideas.model.Compra;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Orquidea;
import br.com.ecommerceorquideas.model.Troca;

public class ComRetornaProdutosEstoque implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Compra compra = (Compra) entidade;
		
		if(compra.getStatus().equals("Troca efetuada")) {
			List<Troca> trocas = compra.getTrocas();
			
			for(Troca troca : trocas) {
				
				if(troca.getRetornarEstoque()) {
					OrquideaDAO dao = new OrquideaDAO();
					HashMap<String, String> map = new HashMap<>();
					map.put("id", troca.getProId().toString());
					
					List<Orquidea> orquidea = (List<Orquidea>) dao.consultar(map);
					
					//Atualiza qtde e ativo da orquidea
					if(orquidea.get(0).getAtivo() == 0) {
						orquidea.get(0).setAtivo(1);
						orquidea.get(0).setQuantidade(troca.getQuantidade());
					}else {
						int qtde = orquidea.get(0).getQuantidade();
						orquidea.get(0).setQuantidade(qtde + troca.getQuantidade());
					}
					
					dao.alterar(orquidea.get(0));
				}
			}
		}
		return null;
	}
}
