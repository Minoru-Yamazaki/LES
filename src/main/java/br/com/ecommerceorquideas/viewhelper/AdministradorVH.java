package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.model.Administrador;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class AdministradorVH implements IViewHelper{

	private Administrador administrador = new Administrador();
	
	@Override
	public EntidadeDominio jsonToEntidade(String json) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			administrador = mapper.readValue(json, Administrador.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return administrador;
	}
}
