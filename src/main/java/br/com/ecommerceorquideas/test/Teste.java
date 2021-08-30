package br.com.ecommerceorquideas.test;

import java.util.HashMap;

public class Teste {

	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<>(); 
		map.put("sexo", "maculino");
		 
		String senha = "Gimi!57363";
		String confirSenha = "Gimi!57363";
		
		boolean teste = senha.equals(confirSenha);
		System.out.println(teste);

		
	}
	
	public static String verificaSenha(String senha) {
		String alfabeto[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		String charEsp[] = {"!", "@", "#", "$", "%", "&", "*"};
		int count = 0;
		
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
