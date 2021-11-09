package br.com.ecommerceorquideas.model;

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
	private Double totalGasto;
	private Integer ranking;
	
	private List<Cartao> cartoes;
	private List<Endereco> enderecos;
	private List<Cupom> cupons;
	private List<Compra> pedidos;
	private Login login;
	
	
	public Cliente(Integer id, String cpf, String nome, String sexo, Date nascimento,
			String telefone, String tipoTelefone, Double totalGasto, Integer ranking) {
		super(id);
		this.cpf = cpf;
		this.nome = nome;
		this.sexo = sexo;
		this.nascimento = nascimento;
		this.tipoTelefone = tipoTelefone;
		this.telefone = telefone;
		this.totalGasto = totalGasto;
		this.ranking = ranking;
	}
}
