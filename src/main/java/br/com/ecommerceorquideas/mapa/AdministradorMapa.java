package br.com.ecommerceorquideas.mapa;

import java.util.HashMap;

import br.com.ecommerceorquideas.model.Administrador;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class AdministradorMapa implements IMapa{

	@Override
	public HashMap<String, String> mapeia(EntidadeDominio entidade) {
		Administrador administrador = (Administrador) entidade;
		HashMap<String, String> map = new HashMap<>();
		
		if(administrador.getCpf() != null)
			map.put("cpf", administrador.getCpf());
		if(administrador.getId() != null)
			map.put("id", administrador.getId().toString());
		if(administrador.getNascimento() != null)
			map.put("data_nascimento", administrador.getNascimento().toString());
		if(administrador.getNome() != null)
			map.put("nome", administrador.getNome());
		if(administrador.getSexo() != null)
			map.put("sexo", administrador.getSexo());
		if(administrador.getTelefone() != null)
			map.put("telefone", administrador.getTelefone());
		if(administrador.getTipoTelefone() != null)
			map.put("tipo_telefone", administrador.getTipoTelefone());
		if(administrador.getPermissao() != null)
			map.put("permissao", administrador.getPermissao().toString());
		
		return map;
	}
}
