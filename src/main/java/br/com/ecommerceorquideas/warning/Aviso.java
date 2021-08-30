package br.com.ecommerceorquideas.warning;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Aviso {

@Getter
@Setter
	private List<String> mensagens;
	
	public void addMensagem(String mensagem) {
		if(mensagens == null) {
			mensagens = new ArrayList<String>();
		}
		mensagens.add(mensagem);
	}
}
