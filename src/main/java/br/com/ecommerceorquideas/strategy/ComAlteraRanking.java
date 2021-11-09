package br.com.ecommerceorquideas.strategy;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.dao.ClienteDAO;
import br.com.ecommerceorquideas.dao.IDAO;
import br.com.ecommerceorquideas.model.CartoesCompra;
import br.com.ecommerceorquideas.model.Cliente;
import br.com.ecommerceorquideas.model.Compra;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class ComAlteraRanking implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Compra compra = (Compra) entidade;
		
		if(compra.getStatus().equals("Entregue")) {
			String clienteId = compra.getCliId().toString();
			IDAO dao = new ClienteDAO();
			
			HashMap<String, String> map = new HashMap<>();
			map.put("id", clienteId);
			List<Cliente> cliente = (List<Cliente>) dao.consultar(map);
			
			double totalGasto = cliente.get(0).getTotalGasto();
			totalGasto += calculaGastoCartoes(compra);
			
			cliente.get(0).setTotalGasto(totalGasto); // atualiza total gasto
			cliente.get(0).setRanking(calculaRanking(totalGasto)); //atualiza ranking
			
			dao.alterar(cliente.get(0));// salva as alterações no BD
		}
		return null;
	}
	
	private double calculaGastoCartoes(Compra compra) {
		List<CartoesCompra> cartoes = compra.getCartoes();
		double total = 0.0;
		
		for(CartoesCompra cartao : cartoes) {
			total += cartao.getTotal();
		}
		
		return total;
	}
	
	private int calculaRanking(double valor) {
		
		if(valor <= 250 && valor > 0) {
			return 1;
		}
		if(valor <= 500 && valor > 250) {
			return 2;
		}
		if(valor <= 1000 && valor > 500) {
			return 3;
		}
		if(valor <= 5000 && valor > 1000) {
			return 4;
		}
		if(valor > 5000) {
			return 5;
		}	
		return 0;
	}

}

