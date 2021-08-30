package br.com.ecommerceorquideas.mapa;

import java.util.HashMap;

import br.com.ecommerceorquideas.domain.Endereco;
import br.com.ecommerceorquideas.domain.EntidadeDominio;

public class EnderecoMapa implements IMapa{

	@Override
	public HashMap<String, String> mapeia(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		HashMap<String, String> map = new HashMap<>();
		
		if(endereco.getId() != null)
			map.put("id", endereco.getId().toString());
		if(endereco.getCidade() != null)
			map.put("cidade", endereco.getCidade());
		if(endereco.getEstado() != null)
			map.put("estado", endereco.getEstado());
		if(endereco.getPais() != null)
			map.put("pais", endereco.getPais());
		if(endereco.getBairro() != null)
			map.put("bairro", endereco.getBairro());
		if(endereco.getTipoLogradouro() != null)
			map.put("tipo_logradouro", endereco.getTipoLogradouro());
		if(endereco.getLogradouro() != null)
			map.put("logradouro", endereco.getLogradouro());
		if(endereco.getNumero() != null)
			map.put("numero", endereco.getNumero());
		if(endereco.getComplemento() != null)
			map.put("complemento", endereco.getComplemento());
		if(endereco.getTipoEndereco() != null)
			map.put("tipo_endereco", endereco.getTipoEndereco());
		if(endereco.getIdCliente() != null)
			map.put("cli_id", endereco.getIdCliente().toString());
				
		return map;
	}

}
