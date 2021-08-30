package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.domain.EntidadeDominio;
import br.com.ecommerceorquideas.domain.Login;

public class LoginVH implements IViewHelper{

	private Login login = new Login();
	
	@Override
	public EntidadeDominio jsonToObject(String json) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			login = mapper.readValue(json, Login.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return login;
	}
}
