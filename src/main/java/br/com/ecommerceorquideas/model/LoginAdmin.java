package br.com.ecommerceorquideas.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginAdmin extends EntidadeDominio{

	private String email;
	private String senha;
	private String senhaConfirm;
	
	public LoginAdmin(Integer id, String email) {
		super(id);
		this.email = email;
	}
}
