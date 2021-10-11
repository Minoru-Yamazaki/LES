package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.model.CupomAdmin;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class CupomAdminVH implements IViewHelper{
	
	private CupomAdmin cupom = new CupomAdmin();
	@Override
	public EntidadeDominio jsonToEntidade(String json) {

		ObjectMapper mapper = new ObjectMapper();
		
		try {
			cupom = mapper.readValue(json, CupomAdmin.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cupom;
	}
}
