package br.com.ecommerceorquideas.viewhelper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.model.Analise;
import br.com.ecommerceorquideas.model.EntidadeDominio;

public class AnaliseVH implements IViewHelper{

	private ObjectMapper jsonDataMapper; 
	private Analise analise = new Analise();

	public AnaliseVH(ObjectMapper jsonDataMapper) {
		this.jsonDataMapper = jsonDataMapper;
	}
	
	@Override
	public EntidadeDominio jsonToEntidade(String json) {
		
		try {
			analise = jsonDataMapper.readValue(json, Analise.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return analise;
	}
}
