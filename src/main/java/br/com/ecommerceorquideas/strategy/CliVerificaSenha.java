package br.com.ecommerceorquideas.strategy;

import br.com.ecommerceorquideas.domain.Cliente;
import br.com.ecommerceorquideas.domain.EntidadeDominio;

public class CliVerificaSenha implements IStrategy{
		
	@Override
	public String processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		String alfabeto[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		String charEsp[] = {"!", "@", "#", "$", "%", "&", "*"};
		int count = 0;
		String senha = cliente.getLogin().getSenha();
		String confirmaSenha = cliente.getLogin().getSenhaConfirm();
		
		if(!senha.equals(confirmaSenha)) {
			return "Você digitou senhas diferentes";
		}
		
		for(String letra : alfabeto) {
			if(senha.contains(letra)) {
				count++;
				break;
			}				
		}
		for(String letra : alfabeto) {
			if(senha.contains(letra.toLowerCase())) {
				count++;
				break;
			}				
		}
		for(String caractere : charEsp) {
			if(!senha.contains(caractere)) {
				count++;
				break;
			}				
		}
		if(senha.length() < 8 || count != 3 || senha == null) {
			return "Senha precisa conter:\nmínimo 8 caracteres\n1 letra minúscula\n1 letra maiúscula";
		}
		return null;
	}

}
