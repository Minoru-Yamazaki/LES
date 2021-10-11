package br.com.ecommerceorquideas.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Administrador extends EntidadeDominio {
	
	private String cpf;
	private String nome;
	private String sexo;
	private Date nascimento;
	private String tipoTelefone;
	private String telefone;
	private Integer permissao;
	private LoginAdmin login;
	private List<CupomAdmin> cupons;
	
	public Administrador(Integer id, String cpf, String nome, String sexo, Date nascimento,
			String telefone, String tipoTelefone, Integer permissao) {
		super(id);
		this.cpf = cpf;
		this.nome = nome;
		this.sexo = sexo;
		this.nascimento = nascimento;
		this.tipoTelefone = tipoTelefone;
		this.telefone = telefone;
		this.permissao = permissao;
	}
	
}
