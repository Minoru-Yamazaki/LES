package br.com.ecommerceorquideas.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CupomAdmin extends EntidadeDominio{

	private String nome;
	private String codigo;
	private String descricao;
	private String tipoCupom;
	private Double valor;
	private Date validade;
	private Integer admId;
	
	public CupomAdmin() {}
	
	public CupomAdmin(Integer id, String nome, String codigo, String descricao, String tipoCupom, Double valor, Date validade, Integer cliId) {
		super(id);
		this.nome = nome;
		this.codigo = codigo;
		this.descricao = descricao;
		this.tipoCupom = tipoCupom;
		this.valor = valor;
		this.validade = validade;
		this.admId = cliId;
	}
}
