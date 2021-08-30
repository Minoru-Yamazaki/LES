package br.com.ecommerceorquideas.mapa;

import java.util.HashMap;

import br.com.ecommerceorquideas.domain.EntidadeDominio;
import br.com.ecommerceorquideas.domain.Login;

public class LoginMapa implements IMapa{

	@Override
	public HashMap<String, String> mapeia(EntidadeDominio entidade) {
		HashMap<String, String> map = new HashMap<>();
		Login login = (Login) entidade;
		
		if(login.getId() != null)
			map.put("cli_id", login.getId().toString());
		if(login.getEmail() != null)
			map.put("email", login.getEmail());
		if(login.getSenha() != null)
			map.put("senha", login.getSenha());
				
		return map;
	}

}
