package br.com.ecommerceorquideas.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CupomCompra extends EntidadeDominio{
	
	private String nome;
	private String descricao;
	private String tipoCupom;
	private Double valor;
	private Date validade;
	private Integer idCupom;
	private Integer idCompra;
	
	public CupomCompra() {}
	
	public CupomCompra(Integer id, String nome, String descricao, String tipoCupom, Double valor, Integer idCupom, Integer idCompra) {
		super(id);
		this.nome = nome;
		this.descricao = descricao;
		this.tipoCupom = tipoCupom;
		this.valor = valor;
		this.idCupom = idCupom;
		this.idCompra = idCompra;
	}

}
