package br.com.ecommerceorquideas.domain;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Cliente extends EntidadeDominio{
	
	private String cpf;
	private String nome;
	private String sexo;
	private Date nascimento;
	private String tipoTelefone;
	private String telefone;
	private List<Cartao> cartoes;
	private List<Endereco> enderecos;
	private Login login;
	
	public Cliente(Integer id, String cpf, String nome, String sexo, Date nascimento,
			String telefone, String tipoTelefone) {
		super(id);
		this.cpf = cpf;
		this.nome = nome;
		this.sexo = sexo;
		this.nascimento = nascimento;
		this.tipoTelefone = tipoTelefone;
		this.telefone = telefone;
	}
}
