package br.com.ecommerceorquideas.mapa;

import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.LoginAdmin;

public class LoginAdminMapa implements IMapa{

	@Override
	public HashMap<String, String> mapeia(EntidadeDominio entidade) {
		HashMap<String, String> map = new HashMap<>();
		LoginAdmin login = (LoginAdmin) entidade;
		
		if(login.getId() != null) {
			map.put("adm_id", login.getId().toString());
		}
		if(login.getEmail() != null) {
			map.put("email", login.getEmail());
		}
		if(login.getSenha() != null) {
			String senhaCodificada = new Base64().encodeToString(login.getSenha().getBytes());
			map.put("senha", senhaCodificada);
		}	
				
		return map;
	}
}
