package br.com.ecommerceorquideas.mapa;

import java.util.HashMap;

import br.com.ecommerceorquideas.model.Cliente;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class ClienteMapa implements IMapa{

	@Override
	public HashMap<String, String> mapeia(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		HashMap<String, String> map = new HashMap<>();
		
		if(cliente.getCpf() != null)
			map.put("cpf", cliente.getCpf());
		if(cliente.getId() != null)
			map.put("id", cliente.getId().toString());
		if(cliente.getNascimento() != null)
			map.put("data_nascimento", cliente.getNascimento().toString());
		if(cliente.getNome() != null)
			map.put("nome", cliente.getNome());
		if(cliente.getSexo() != null)
			map.put("sexo", cliente.getSexo());
		if(cliente.getTelefone() != null)
			map.put("telefone", cliente.getTelefone());
		if(cliente.getTipoTelefone() != null)
			map.put("tipo_telefone", cliente.getTipoTelefone());
		if(cliente.getTotalGasto() != null)
			map.put("total_gasto", cliente.getTotalGasto().toString());
		if(cliente.getRanking() != null)
			map.put("ranking", cliente.getRanking().toString());
		
		return map;
	}

}
