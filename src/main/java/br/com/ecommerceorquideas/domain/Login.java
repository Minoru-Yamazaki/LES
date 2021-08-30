package br.com.ecommerceorquideas.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Login extends EntidadeDominio{

	private String email;
	private String senha;
	private String senhaConfirm;
	
	public Login(Integer id) {
		super(id);
	}
}
