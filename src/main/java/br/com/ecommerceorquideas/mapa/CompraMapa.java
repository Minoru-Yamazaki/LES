package br.com.ecommerceorquideas.mapa;

import java.util.HashMap;

import br.com.ecommerceorquideas.model.Compra;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class CompraMapa implements IMapa{

	@Override
	public HashMap<String, String> mapeia(EntidadeDominio entidade) {
		Compra pedido = (Compra) entidade;
		HashMap<String, String> map = new HashMap<>();
		
		if(pedido.getId() != null)
			map.put("id", pedido.getId().toString());
		if(pedido.getAtivo() != null)
			map.put("ativo", pedido.getAtivo().toString());
		if(pedido.getStatus() != null)
			map.put("status", pedido.getStatus());
		if(pedido.getData() != null)
			map.put("data", pedido.getData().toString());
		if(pedido.getValor() != null)
			map.put("valor", pedido.getValor().toString());		
		if(pedido.getCidade() != null)
			map.put("cidade", pedido.getCidade());
		if(pedido.getEstado() != null)
			map.put("estado", pedido.getEstado());
		if(pedido.getPais() != null)
			map.put("pais", pedido.getPais());
		if(pedido.getBairro() != null)
			map.put("bairro", pedido.getBairro());
		if(pedido.getTipoLogradouro() != null)
			map.put("tipo_logradouro", pedido.getTipoLogradouro());
		if(pedido.getLogradouro() != null)
			map.put("logradouro", pedido.getLogradouro());
		if(pedido.getNumero() != null)
			map.put("numero", pedido.getNumero());
		if(pedido.getComplemento() != null)
			map.put("complemento", pedido.getComplemento());
		if(pedido.getTipoResidencia() != null)
			map.put("tipo_residencia", pedido.getTipoResidencia());
		if(pedido.getCep() != null)
			map.put("cep", pedido.getCep());
		if(pedido.getCliId() != null)
			map.put("id", pedido.getCliId().toString());;
		
		return map;
	}

}
