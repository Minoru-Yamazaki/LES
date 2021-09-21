package br.com.ecommerceorquideas.warning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.ecommerceorquideas.domain.EntidadeDominio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aviso extends EntidadeDominio{

	private List<String> mensagens;
	
	public Aviso() {}
	public Aviso(String mensagem) {
		mensagens = Arrays.asList(mensagem);
	}
	
	public void addMensagem(String mensagem) {
		if(mensagens == null) {
			mensagens = new ArrayList<String>();
		}
		mensagens.add(mensagem);
	}
}
