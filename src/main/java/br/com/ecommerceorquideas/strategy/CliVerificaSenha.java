package br.com.ecommerceorquideas.strategy;

import java.util.ArrayList;
import java.util.List;

import br.com.ecommerceorquideas.model.Administrador;
import br.com.ecommerceorquideas.model.Cliente;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Login;
import br.com.ecommerceorquideas.model.LoginAdmin;

public class CliVerificaSenha implements IStrategy{
		
	@Override
	public List<String> processar(EntidadeDominio entidade) {
		String classeCliente = Cliente.class.getName();
		String classeLoginAdmin = LoginAdmin.class.getName();
		String classeAdministrador = Administrador.class.getName();
		String senha = "";
		String confirmaSenha = "";
		
		if(entidade.getClass().getName().equals(classeCliente)) {
			Cliente cliente = (Cliente) entidade;
			senha = cliente.getLogin().getSenha();
			confirmaSenha = cliente.getLogin().getSenhaConfirm();
		}else if(entidade.getClass().getName().equals(classeLoginAdmin)){
			LoginAdmin login = (LoginAdmin) entidade;
			senha = login.getSenha();
			confirmaSenha = login.getSenhaConfirm();
		}else if(entidade.getClass().getName().equals(classeAdministrador)){
			Administrador admin = (Administrador) entidade;
			senha = admin.getLogin().getSenha();
			confirmaSenha = admin.getLogin().getSenhaConfirm();
		}else {
			System.out.println(entidade);
			Login login = (Login) entidade;
			senha = login.getSenha();
			confirmaSenha = login.getSenhaConfirm();
		}
		
		String alfabeto[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		String charEsp[] = {"!", "@", "#", "$", "%", "&", "*"};
		int count = 0;
		
		List<String> mensagens = new ArrayList<String>();
		
		if(senha == null || confirmaSenha == null) {
			mensagens.add("Senha senha não pode ser nula");
			return mensagens;
		}
		if(senha.length() < 8)
			mensagens.add("Senha precisa conter no mínimo 8 caracteres");
		
		if(!senha.equals(confirmaSenha))
			mensagens.add("Você digitou senhas diferentes");
				
		for(String letra : alfabeto) {
			if(senha.contains(letra)) {
				count = 1;
				break;
			}				
		}
		if(count == 0) 
			mensagens.add("Senha precisa conter no mínimo 1 letra minúscula");
		count = 0;
		
			
		
		for(String letra : alfabeto) {
			if(senha.contains(letra.toUpperCase())) {
				count = 2;
				break;
			}				
		}
		if(count == 0)
			mensagens.add("Senha precisa conter no mínimo 1 letra maiúscula");
		count = 0;
		
		
		for(String caractere : charEsp) {
			if(senha.contains(caractere)) {
				count = 3;
				break;
			}				
		}
		if(count == 0)
			mensagens.add("Senha precisa conter no mínimo 1 caractere especial: !@#$%&*");
		count = 0;
	
		if(mensagens.size() == 0)
			return null;
		else
			return mensagens;
	}

}
