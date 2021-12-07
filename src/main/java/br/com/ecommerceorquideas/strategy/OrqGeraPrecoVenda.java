package br.com.ecommerceorquideas.strategy;

import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.dao.IDAO;
import br.com.ecommerceorquideas.dao.PrecificacaoDAO;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Orquidea;
import br.com.ecommerceorquideas.model.Precificacao;

public class OrqGeraPrecoVenda implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Orquidea orquidea = (Orquidea) entidade;
		
		HashMap<String, String> map = new HashMap<>();
		map.put("id", orquidea.getIdPrecificacao().toString());
		
		IDAO dao;
		dao = new PrecificacaoDAO();
		List<Precificacao> precificacoes =  (List<Precificacao>) dao.consultar(map);
		
		int porcentagem = precificacoes.get(0).getPorcentagem();
		double valorCusto = orquidea.getValorCusto();
		
		orquidea.setValorVenda(calculaPrecoVenda(valorCusto, porcentagem));
		
		return null;
	}
	
	public double calculaPrecoVenda(double valorCusto, int porcentagem) {
		return valorCusto + (valorCusto * (porcentagem / 100));
	}

}
