package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.LoginAdmin;

public class LoginAdminVH implements IViewHelper{

	private LoginAdmin login = new LoginAdmin();
	
	@Override
	public EntidadeDominio jsonToEntidade(String json) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			login = mapper.readValue(json, LoginAdmin.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return login;
	}
}
