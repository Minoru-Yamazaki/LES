package br.com.ecommerceorquideas.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cupom extends EntidadeDominio{
	
	private String nome;
	private String descricao;
	private String tipoCupom;
	private Double valor;
	private Date validade;
	private Integer cliId;
	
	public Cupom() {}
	
	public Cupom(Integer id, String nome, String descricao, String tipoCupom, Double valor, Date validade, Integer cliId) {
		super(id);
		this.nome = nome;
		this.descricao = descricao;
		this.tipoCupom = tipoCupom;
		this.valor = valor;
		this.validade = validade;
		this.cliId = cliId;
	}
}
