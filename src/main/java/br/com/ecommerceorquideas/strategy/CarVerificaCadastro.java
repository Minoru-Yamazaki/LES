package br.com.ecommerceorquideas.strategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.dao.CartaoDAO;
import br.com.ecommerceorquideas.dao.IDAO;
import br.com.ecommerceorquideas.model.Cartao;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class CarVerificaCadastro implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Cartao cartao = (Cartao) entidade;
		
		String apelido = cartao.getApelido();
		String numero = cartao.getNumero();
		String idCliente = cartao.getIdCliente().toString();
		
		IDAO dao = new CartaoDAO();
		HashMap<String, String> map = new HashMap<>();
		map.put("cli_id", idCliente);
		
		List<Cartao> cartoes = (List<Cartao>) dao.consultar(map);
		
		for(Cartao card : cartoes) {
			if(card.getApelido().equals(apelido)) {
				return Arrays.asList("Apelido já cadastrado");
			}
			if(card.getNumero().equals(numero)) {
				return Arrays.asList("Cartão já cadastrado");
			}
		}
		
		return null;
	}

}
