package br.com.ecommerceorquideas.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuponsCompra extends EntidadeDominio{
	
	private String nome;
	private String descricao;
	private String tipoCupom;
	private Double valor;
	private Integer idCupom;
	private Integer idCompra;
	
	public CuponsCompra() {}
	
	public CuponsCompra(Integer id, String nome, String descricao, String tipoCupom, Double valor, Integer idCupom, Integer idCompra) {
		super(id);
		this.nome = nome;
		this.descricao = descricao;
		this.tipoCupom = tipoCupom;
		this.valor = valor;
		this.idCupom = idCupom;
		this.idCompra = idCompra;
	}

}
